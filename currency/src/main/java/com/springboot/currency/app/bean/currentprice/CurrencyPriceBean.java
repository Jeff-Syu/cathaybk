package com.springboot.currency.app.bean.currentprice;

import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springboot.currency.app.bean.currentprice.rs.CurrencyInfoRsBean;

public class CurrencyPriceBean {

	public CurrencyPriceBean() {
		
	}
	
	@JsonProperty("time")
	private TimeBean timeBean;
	
	private String disclaimer;
	
	private String chartName;
	
	@JsonProperty("bpi")
	private Map<String, CurrencyInfoBean> bpiMap = new TreeMap<String, CurrencyInfoBean>();
	
	public TimeBean getTimeBean() {
		return timeBean;
	}

	public void setTimeBean(TimeBean timeBean) {
		this.timeBean = timeBean;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
	}

	public Map<String, CurrencyInfoBean> getBpiMap() {
		return bpiMap;
	}

	public void setBpiMap(Map<String, CurrencyInfoBean> bpiMap) {
		this.bpiMap = bpiMap;
	}
}
