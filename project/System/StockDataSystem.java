package project.System;

import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

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
     * 取得使用者的Key和ID
     * @return 使用者的Key和ID
     */
    public abstract KeyAndID getKeyAndID();

    /**
     * 設定使用者的Secret Key, KeyID
     */
    public abstract void setKeyAndID(String secretKey, String KeyID);

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
}
