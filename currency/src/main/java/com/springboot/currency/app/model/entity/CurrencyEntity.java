package com.springboot.currency.app.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CURRENCY")
public class CurrencyEntity {
	
	@Id
	@Column(name="CURRENCY_CODE")
	private String currencyCode; //幣別代號
	
	@Column(name="CURRENCY_NAME")
	private String currencyName; //幣別名稱

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}
}

