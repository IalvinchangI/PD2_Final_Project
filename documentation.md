# Documentation

## StockDataSystem `interface`
### 功能：
> 存取所有資料

### 方法：
> * 取得某股票的買賣設定
> ```java
> public StockBuyingSetting getBuyingSetting(String stockName)
> ```

> * 取得使用者的key&ID
> ```java
> public KeyAndID getKeyAndID()
> ```

> * 取得歷史紀錄
> ```java
> public HistoryRecord getHistoryRecord()
> ```

> * 儲存使用者的Key&ID
> ```java
> public void setKeyAndID(String key, String ID)
> ```

> * 儲存交易資料
> ```java
> public void saveDeal(String stockName, int stockCount, double profitAndLoss, Date dateAndTime)
> ```

> * 儲存買賣設定
> ```java
> public void saveBuyingSetting(String stockName, double sellPrice, double buyPrice, double step)
> ```

<br>

## Stock
### 功能：
> 存有股票名稱、股價

### 方法：
> * 取得該股票的名稱
> ```java
> public String getStockName()
> ```

> *取得該股票的股價
> ```java
> public double getStockPrice()
> ```

<br>

## HistoryRecord
### 功能：
> 存有歷史交易的紀錄和盈虧

### 方法：
> * 取得歷史交易紀錄
> ```java
> public Deal[] getRecords()
> ```

> * 取得盈虧
> ```java
> public double getProfitAndLoss()
> ```  

<br>

## Deal
### 功能：
> 紀錄單筆交易的內容

### 方法：
> * 取得該交易股票的名稱
> ```java
> public String getStockName()
> ```

> * 取得時間
> ```java
> public Date getDateAndTime()
> ```

> * 取得盈虧
> ```java
> public double getProfitAndLoss()
> ```

> * 取得該次交易的交易股票數量
> ```java
> public int getStockCount()
> ```

> * 寫入該次交易的股票名稱
> ```java
> public void setStockName(String stockName)
> ```

> * 寫入該次交易的時間
> ```java
> public void setDateAndTime(int yeat, int month, int date, int hour, int minute, int second)
> ```

> * 寫入該次交易的盈虧
> ```java
> public void setProfitAndLoss(double profitAndLoss)
> ```

> * 寫入該次交易交易的股票數目
> ```java
> public void setStockCount(int stockCount)
> ``` 


<br>

## KeyAndID
### 功能：
> 存 Alpaca API 的帳號、金鑰

### 方法：
> * 取得API的key
> ```java
> public String getAPIKey()
> ```

> * 取得API的ID
> ```java
> public String getAPIID()

> * 寫入API的key
> ```java
> public void setAPIKey(String APIKey)
> ```

> * 寫入API的ID
> ```java
> public void setAPIID(String ID)
> ```  