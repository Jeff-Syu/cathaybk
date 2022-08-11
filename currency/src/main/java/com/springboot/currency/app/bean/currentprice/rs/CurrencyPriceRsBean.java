package com.springboot.currency.app.bean.currentprice.rs;

import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyPriceRsBean {

	//更新時間
	@JsonProperty("update_time")
	private String updateTime;
	
	//幣別相關資訊
	@JsonProperty("currency_info")
	private Map<String, CurrencyInfoRsBean> currencyInfoMap = new TreeMap<String, CurrencyInfoRsBean>();

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Map<String, CurrencyInfoRsBean> getCurrencyInfoMap() {
		return currencyInfoMap;
	}

	public void setCurrencyInfoMap(Map<String, CurrencyInfoRsBean> currencyInfoMap) {
		this.currencyInfoMap = currencyInfoMap;
	}
}
