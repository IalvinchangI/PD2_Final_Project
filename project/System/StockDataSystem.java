package project.System;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface StockDataSystem {
    /**
     * 取得Map<股票名稱, 該股票資訊>
     * @return Map<股票名稱, 該股票資訊>
     */
    public abstract Map<String, Stock> getStocksMap();

    /**
     * 取得特定股票資料
     * @param stockName : 股票名稱
     * @return 特定股票資料
     */
    public abstract Stock getStock(String stockName);

    /**
     * 將一種股票新增進stocksMap中
     * @param stockName : 股票名稱
     * @param stockPrice : 股價
     */
    public abstract void addStock2StocksMap(String stockName, double stockPrice);

    /**
     * 取得設有買賣設定的股票集合, Set<股票名稱>
     * @return 買賣設定的股票集合, Set<股票名稱>
     */
    public abstract Set<String> getStockNamesHasBuyingSetting();

    public abstract boolean checkBuyingSettingIsSet(String stockName);

    /**
     * 取得某股票的買賣設定
     * @param stockName : 該股票名稱
     * @return 該股票的買賣設定
     */
    public abstract StockBuyingSetting getBuyingSetting(String stockName);

    /**
     * 儲存某股票的買賣設定
     * @param stockName : 該股票名稱
     * @param bidPrice : 目標買價
     * @param offerStep : 上漲多少錢賣出
     * @param bidStep : 下降多少錢買入
     */
    public abstract void saveBuyingSetting(
        String stockName, double bidPrice, double offerStep, double bidStep, int stockCount
    );

    /**
     * 設定使用者的Secret Key, KeyID
     */
    public abstract void setKeyAndID(String secretKey, String KeyID);

    /**
     * 取得使用者的Key和ID
     * @return 使用者的Key和ID
     */
    public abstract KeyAndID getKeyAndID();

    /**
     * 取得特定股票歷史交易紀錄
     * @return 該股票的歷史交易紀錄
     */
    public abstract List<Deal> getHistoryRecord(String stockName);

    /**
     * 將一筆交易紀錄到History Record中
     * @param stockName :股票名稱
     * @param stockCount : 交易股數
     * @param profitAndLoss : 交易盈虧
     * @param year : 年份
     * @param month : 月份
     * @param date : 日期
     */
    public abstract void addDeal2HistoryRecord(
        String stockName, int stockCount, double profitAndLoss, int year, int month, int date
    );

    /**
     * 將對特定股票的單次觀察存入紀錄陣列recorder (List<MarketInfo>)中
     * @param stockName : 股票名稱
     * @param mode : 單一觀察的時間段長度 (mode: 每日"DAY", 每周"WEEK", 每月"MON")(預設為"DAY")
     * @param highPrice : 最高價
     * @param lowPrice : 最低價
     * @param openingPrice : 開盤價
     * @param closingPrice : 收盤價
     * @param year : 年份
     * @param month : 月份
     * @param date : 日期
     */
    public abstract void addStockMarketInfo2Recorder(
        String stockName, String mode,
        double highPrice, double lowPrice, double openingPrice, double closingPrice,
        int year, int month, int date
    );

    /**
     * 取得對特定股票在市場上的資訊的觀察紀錄陣列recorder (List<MarketInfo>)
     * @param stockName : 股票名稱
     * @param mode : 單一觀察的時間段長度 (mode: 每日"DAY", 每周"WEEK", 每月"MON")(預設為"DAY")
     * @return 觀察紀錄陣列recorder (List<MarketInfo>)
     */
    public abstract List<MarketInfo> getRecorder(String stockName, String mode);
}
