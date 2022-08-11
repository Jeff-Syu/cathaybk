package com.springboot.currency.app.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureMockMvc
@SpringBootTest
class CurrencyControllerTests {

    @Autowired
    private MockMvc mockMvc;
    
    //1. 測試呼叫查詢幣別對應表資料API，並顯示其內容。
	@Test
	void queryCurrency() throws Exception{
		
        mockMvc.perform(get("/currency/queryCurrency"))
        .andExpect(status().isOk())
        .andExpect(content().string("[{\"currencyCode\":\"USD\",\"currencyName\":\"美金\"},{\"currencyCode\":\"GBP\",\"currencyName\":\"英鎊\"},{\"currencyCode\":\"EUR\",\"currencyName\":\"歐元\"}]"))
        .andDo(print());
	}
	
	//2. 測試呼叫新增幣別對應表資料API。
	@Test
	@Transactional
	@Rollback(true)
	void insertCurrencyData() throws Exception{
		
        mockMvc.perform(get("/currency/insertCurrencyData?code=TWD&name=臺幣"))
        .andExpect(status().isOk())
        .andExpect(content().string("幣別:TWD資料新增完成"))
        .andDo(print());
	}
	
	//3. 測試呼叫更新幣別對應表資料API，並顯示其內容。
	@Test
	@Transactional
	@Rollback(true)
	void updateCurrencyData() throws Exception{
		
        mockMvc.perform(get("/currency/updateCurrencyData?code=USD&name=美金1"))
        .andExpect(status().isOk())
        .andExpect(content().string("{\"currencyCode\":\"USD\",\"currencyName\":\"美金1\"}"))
        .andDo(print());
	}
		
	//4. 測試呼叫刪除幣別對應表資料API。
	@Test
	@Transactional
	@Rollback(true)
	void deleteCurrencyData() throws Exception{
		
        mockMvc.perform(get("/currency/deleteCurrencyData?code=USD"))
        .andExpect(status().isOk())
        .andExpect(content().string("幣別:USD資料刪除完成"))
        .andDo(print());
	}
	
	//5. 測試呼叫coindesk API，並顯示其內容。
	@Test
	void callCoindeskApi() throws Exception{
		
        mockMvc.perform(get("/currency/callCoindeskApi"))
        .andExpect(status().isOk())
        .andDo(print());
	}
	
	//6. 測試呼叫資料轉換的API，並顯示其內容。	
	@Test
	void getNewCoindeskApi() throws Exception{
		
        mockMvc.perform(get("/currency/getNewCoindeskApi"))
        .andExpect(status().isOk())
        .andDo(print());
	}
}
