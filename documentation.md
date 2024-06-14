# Documentation

## StockDataSystem `interface`
### 功能：
> 存取所有資料

### 方法：
> * 取得Map<股票名稱, 該股票資訊>
> ```java
> public abstract Map<String, Stock> getStocksMap();
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
