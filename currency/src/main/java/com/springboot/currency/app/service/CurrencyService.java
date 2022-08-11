package com.springboot.currency.app.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.springboot.currency.app.bean.currentprice.CurrencyInfoBean;
import com.springboot.currency.app.bean.currentprice.CurrencyPriceBean;
import com.springboot.currency.app.bean.currentprice.rs.CurrencyInfoRsBean;
import com.springboot.currency.app.bean.currentprice.rs.CurrencyPriceRsBean;
import com.springboot.currency.app.model.dao.CurrencyDao;
import com.springboot.currency.app.model.entity.CurrencyEntity;
import com.springboot.currency.app.utils.JsonUtils;

@Service
public class CurrencyService {

    private RestTemplate restTemplate;
	
    public CurrencyService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
	
	@Autowired
	private CurrencyDao currencyDao;
	
	public String queryCurrency() {
		List<CurrencyEntity> currencyEntitys= new ArrayList<CurrencyEntity>();
		currencyEntitys = currencyDao.findAll();
		
		return JsonUtils.getJson(currencyEntitys);
	}
	
	
	public String callCoindeskApi() {
		
		//呼叫coindesk的API
		String jsonStr = restTemplate.getForObject("https://api.coindesk.com/v1/bpi/currentprice.json", String.class);      
						
        return jsonStr;
	}
	
	public String getNewCoindeskApi() {
			
		try {
			//呼叫coindesk的API
			String jsonStr = this.callCoindeskApi();
			CurrencyPriceBean currencyPriceBean = JsonUtils.getObject(jsonStr, CurrencyPriceBean.class);
			
			//呼叫coindesk的API，並進行資料轉換，組成新API。
			CurrencyPriceRsBean currencyPriceRsBean = new CurrencyPriceRsBean();
			//A. 更新時間（時間格式範例：1990/01/01 00:00:00）
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			Date updatedISO = sdf.parse(currencyPriceBean.getTimeBean().getUpdatedISO());
	
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			currencyPriceRsBean.setUpdateTime(sdf2.format(updatedISO));
					
			for(Entry<String, CurrencyInfoBean> entry: currencyPriceBean.getBpiMap().entrySet()) {
				String key = entry.getKey();
				CurrencyInfoBean currencyInfoBean = entry.getValue();
			
				//B. 幣別相關資訊（幣別，幣別中文名稱，以及匯率）。
				CurrencyEntity entity = currencyDao.findByCurrencyCode(key);
				if(entity != null) {
					CurrencyInfoRsBean currencyInfoRsBean = new CurrencyInfoRsBean();
					currencyInfoRsBean.setCurrencyCode(key);
					currencyInfoRsBean.setCurrencyName(entity.getCurrencyName());
					currencyInfoRsBean.setRateFloat(currencyInfoBean.getRateFloat());
					
					currencyPriceRsBean.getCurrencyInfoMap().put(key, currencyInfoRsBean);
				}
			}
						
	        return JsonUtils.getJson(currencyPriceRsBean);
		} catch (ParseException e) {
			System.out.println("日期轉換發生錯誤:" + e);
		}
		
		return "";
	}
	
	
	public String insertCurrencyData(String code, String name) {
		
		try {
			CurrencyEntity currencyEntity = currencyDao.findByCurrencyCode(code);
			
			if(currencyEntity == null) {
				currencyEntity = new CurrencyEntity();
				currencyEntity.setCurrencyCode(code);
				currencyEntity.setCurrencyName(name);
				currencyDao.save(currencyEntity);
				
				return "幣別:"+ code +"資料新增完成";
			}else {
				return "幣別:"+ code + "資料已存在";
			}	
		} catch (Exception e) {
			System.out.println("幣別資料新增發生錯誤:" + e);	
			return "幣別資料新增發生錯誤";
		}
	}
	
	
	public String updateCurrencyData(String code, String name) {
		
		try {			
			CurrencyEntity currencyEntity = currencyDao.findByCurrencyCode(code);
			if(currencyEntity != null)	{
				
				currencyEntity.setCurrencyName(name);
				currencyDao.save(currencyEntity);
				
				return JsonUtils.getJson(currencyEntity);	
			}else {
				return "幣別:"+ code + "資料不存在";
			}
			
		} catch (Exception e) {
			System.out.println("幣別資料更新發生錯誤:" + e);	
			return "幣別資料更新發生錯誤";
		}
	}
	

	public String deleteCurrencyData(String code) {
		
		try {			
			CurrencyEntity currencyEntity = currencyDao.findByCurrencyCode(code);
			if(currencyEntity != null)	{
				currencyDao.delete(currencyEntity);
				
				return "幣別:"+ code +"資料刪除完成";
			}else {
				return "幣別:"+ code + "資料不存在";
			}
			
		} catch (Exception e) {
			System.out.println("幣別資料刪除發生錯誤:" + e);	
			return "幣別資料刪除發生錯誤";
		}
	}
	
}
