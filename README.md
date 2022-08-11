# cathaybk
國泰世華JAVA engineer線上作業

一、
實體檔案存放路徑如下
cathaybk/currency/db/ddl/CURRENCY.sql

DDL內容如下

DROP TABLE IF EXISTS CURRENCY;

CREATE TABLE CURRENCY COMMENT '幣別'(
  CURRENCY_CODE VARCHAR(3) PRIMARY KEY COMMENT '幣別代號',
  CURRENCY_NAME VARCHAR(20) NOT NULL COMMENT '幣別名稱'
);

二、
測試URL

查詢
http://localhost:9080/currency/queryCurrency

新增
http://localhost:9080/currency/insertCurrencyData?code=TWD&name=臺幣

修改
http://localhost:9080/currency/updateCurrencyData?code=TWD&name=臺幣1

刪除
http://localhost:9080/currency/deleteCurrencyData?code=TWD

CoindeskApi
http://localhost:9080/currency/callCoindeskApi

New API
http://localhost:9080/currency/getNewCoindeskApi

三、
單元測試
CurrencyControllerTests
