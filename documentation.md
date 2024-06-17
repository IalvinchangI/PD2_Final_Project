# System Documentation

## StockDataSystem `interface`
### 功能 :
> 存取所有資料

### 方法 :
> * 設定是否完成登入
> ```java
> public void setLogin(boolean isLogin);
> ```

> * 檢查是否登入
> ```java
> public boolean checkIsLogin();
> ```

> * 取得Map<股票名稱, 該股票資訊>
> ```java
> public Map<String, Stock> getStocksMap();
> ```

> * 取得有買賣設定的股票集合
> ```java
> public Set<String> getStockNamesHasBuyingSetting()
> ``` 

> * 取得特定股票資料
> ```java
> public Stock getStock(String stockName)
> ```

> * 將一種股票新增近stocksMap中
> ```java
> public void addStock2StocksMap(String stockName, double stockPrice)
> ```

> * 取得設有買賣設定的股票集合, Set<股票名稱>
> ```java
> public Set<String> getStockNamesHasBuyingSetting()
> ```

> * 取得某股票的買賣設定
> ```java
> public StockBuyingSetting getBuyingSetting(String stockName)
> ```

> * 儲存某股票的買賣設定
> ```java
> public void saveBuyingSetting(String stockName, double bidPrice, double offerStep, double bidStep, int stockCount)
> ```

> * 設定使用者的Secret Key, KeyID
> ```java
> public void setKeyAndID(String secretKey, String KeyID);
> ```

> * 取得使用者的key&ID
> ```java
> public KeyAndID getKeyAndID()
> ```

> * 取得特定股票的歷史交易紀錄
> ```java
> public List<Deal> getHistoryRecord(String stockName);
> ```

> * 取得總盈虧
> ```java
> public double getTotalProfitAndLoss();
> ```

> * 將一筆交易記錄到History Record中
> ```java
> public void addDeal2HistoryRecord(String stockName, int stockCount, double profitAndLoss, int year, int month, int date);
> ```

> * 將對特定股票的單次觀察存入紀錄陣列recorder (List<MarketInfo>)中
> ```java
> public void addStockMarketInfo2Recorder(
>   String stockName, String mode,
>   double highPrice, double lowPrice, double openingPrice, double closingPrice,
>   int year, int month, int date
> )
> ```

> * 取得對特定股票在市場上的資訊的觀察紀錄陣列recorder (List<MarketInfo>)
> ```java
> public List<MarketInfo> getRecorder(String stockName, String mode);
> ```


## Stock `class`
### 方法 :
> * 建構式
> ```java
> public Stock(String stockName, double stockPrice, int stockCount);
> ```

> * 取得股票名稱
> ```java
> public String getStockName();
> ```

> * 取得股價
> ```java
> public double getStockPrice();
> ```

> * 取得持有的股數
> ```java
> public int getStockCount();
> ```


## StockBuyingSetting `class`
### 方法 :
> * 建構式
> ```java
> public StockBuyingSetting(
>    String stockName, double bidPrice, double offerStep, double stockCount
> );
> ```

> * 取得股票名稱
> ```java
> public String getStockName();
> ```

> * 取得目標買價
> ```java
> public double getBidPrice();
> ```

> * 取得上漲多少錢時賣出
> ```java
> public double getOfferStep();
> ```

> * 取得下跌多少錢時買入
> ```java
> public double getBidStep();
> ```

> * 取得該股票交易的股數
> ```java
> public int getStockCount();
> ```

> * 設定股票名稱
> ```java
> public void setStockName(String stockName);
> ```

> * 設定目標買價
> ```java
> public void setBidPrice(double bidPrice);
> ```

> * 設定上漲多少錢時賣出
> ```java
> public void setOfferStep(double offerStep);
> ```

> * 設定下跌多少錢時買入
> ```java
> public void setBidStep(double bidPrice);
> ```

> * 設定交易的股數
> ```java
> public void setStockCount(int stockCount);
> ```


## KeyAndID `class`
### 方法 :
> * 建構式
> ```java
> public KeyAndID(String secretKey, String keyID);
> ```

> * 取得使用者的Key ID
> ```java
> public String getKeyID();
> ```

> * 取得使用者的Secret Key
> ```java
> public String getsecretKey();
> ```

> * 設定使用者的Secret Key
> ```java
> public void setsecretKey(String secretKey);
> ```

> * 設定使用者的Key ID
> ```java
> public void setKeyID(String KeyID);
> ```


## HistoryRecord `class`
### 方法 :
> * 將一筆交易存入歷史紀錄的陣列
> ```java
> public void addADeal(Deal deal);
> ```

> * 取得特定股票的所有歷史交易紀錄
> ```java
> public List<Deal> getRecord(String stockName);
> ```

> * 取得(所有歷史交易)累積的盈虧
> ```java
> public double getProfitAndLoss();
> ```


## MarketInfo `class`
### 方法 : 
> * 取得股票名稱
> ```java
> public String getStockName();
> ```

> * 取得單一觀察的時間長度 (mode: 每日"DAY", 每周"WEEK", 每月"MON")
> ```java
> public String getMode();
> ```

> * 取得觀察的時間
> * (mode:時間) "DAY":YYYY-MM-DD, "WEEK":(起始的)YYYY-MM-DD, "MON":(起始的)YYYY-MM-DD
> ```java
> public String getDate();
> ```

> * 取得開盤價
> ```java
> public double getOpeningPrice();
> ```

> * 取得收盤價
> ```java
> public double getClosingPrice();
> ```

> * 取得最高價
> ```java
> public double getHighPrice();
> ```

> * 取得最低價
> ```java
> public double getLowPrice();
> ```


## StockMarketInfoRecorder `class`
### 方法 :
> * 建構式
> * mode : 單一觀察時間段的長度 (mode: 每日"DAY", 每周"WEEK", 每月"MON")(預設為"DAY")
> ```java
> public StockMarketInfoRecorder(String stockName, String mode);
> ```

> * 將單一時間段中，該股票的市場資訊放入紀錄陣列recorder (List<MarketInfo>)中
> * highPrice : 最高價
> * lowPrice : 最低價
> * openingPrice : 開盤價
> * closingPrice : 收盤價
> ```java
> public void addMarketInfo2Recorder(
>   String stockName, String mode,
>   double highPrice, double lowPrice, 
>   double openingPrice, double closingPrice,
>   int year, int month, int date
> )
> ```

> * 取得被紀錄的股票名稱
> ```java
> public String getStockName();
> ```

> * 取得單一觀察的時間段長度 (mode: 每日"DAY", 每周"WEEK", 每月"MON")(預設為"DAY")
> ```java
> public String getMode();
> ```
