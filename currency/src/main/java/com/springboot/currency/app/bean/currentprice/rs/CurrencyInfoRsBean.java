package com.springboot.currency.app.bean.currentprice.rs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyInfoRsBean {
	//幣別中文名稱
	@JsonProperty("name")
	private String currencyName;
	//幣別
	@JsonProperty("code")
	private String currencyCode;
	
	//匯率
	@JsonProperty("rate_float")
	private float rateFloat;

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public float getRateFloat() {
		return rateFloat;
	}

	public void setRateFloat(float rateFloat) {
		this.rateFloat = rateFloat;
	}
}
