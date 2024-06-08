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
>  public void saveDeal(String stockName, int stockCount, double profitAndLoss, Date dateAndTime)
> ```java

> * 儲存買賣設定
>   ```java
>   public void saveBuyingSetting(String stockName, double sellPrice, double buyPrice, double step)
>   ```


<br>

## Stock
### 功能：
> 

### 方法：
> * 
> ```java
> 
> ```

<br>

## HistoryRecord
### 功能：
> 

### 方法：
> * 
> ```java
> 
> ```

<br>

## Deal
### 功能：
> 

### 方法：
> * 
> ```java
> 
> ```

<br>

## KeyAndID
### 功能：
> 存 Alpaca API 的帳號、金鑰

### 方法：
> * 
> ```java
> 
> ```
