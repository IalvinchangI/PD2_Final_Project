# Documentation

## WebCrawler `class`
### 功能：
> 存取股票資訊

### 方法：
> * 設定存取資料的地方以及下載 API_KEY_IDㄝ, API_SECRET_KEY
> ```java
> public static void downloadStockDataSystem(StockDataSystem stockDataSystem)
> ```

> * 測試是否開市
> ```java
> public static boolean checkMarketOpen()
> ```

> * 測試使用者輸入是否正確
> ```java
> public static boolean check_Key_ID()
> ``` 

> * 存取股票時價
> ```java
> public static void stockPriceProcessing()
> ```

> * 存取30天股價資訊
> ```java
> public static void stockDataProcessing()
> ```

> * 存取歷史紀錄
> ```java
> public static void historyTradingProcessing()
> ```