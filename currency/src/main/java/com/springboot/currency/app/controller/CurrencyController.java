package com.springboot.currency.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.currency.app.service.CurrencyService;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
		
    @Autowired
    CurrencyService currencyService;
	
	//1. 幣別DB維護功能。
    //查詢
	@GetMapping("/queryCurrency")
	public String queryCurrency() {
		
		return currencyService.queryCurrency();
	}
	
	//新增
	@GetMapping("/insertCurrencyData")
	public String insertCurrencyData(String code, String name) {
				
		return currencyService.insertCurrencyData(code, name);	
	}
	
	//修改
	@GetMapping("/updateCurrencyData")
	public String updateCurrencyData(String code, String name) {
		
		return currencyService.updateCurrencyData(code, name);
	}
	
	//刪除
	@GetMapping("/deleteCurrencyData")
	public String deleteCurrencyData(String code) {
		
		return currencyService.deleteCurrencyData(code);
	}

	//2. 呼叫coindesk的API。
	@GetMapping("/callCoindeskApi")
	public String getCoindeskApi() {

		return currencyService.callCoindeskApi();
	}
	
	//3. 呼叫coindesk的API，並進行資料轉換，組成新API。
	@GetMapping("/getNewCoindeskApi")
	public String getNewCoindeskApi() {

		return currencyService.getNewCoindeskApi();
	}

}
