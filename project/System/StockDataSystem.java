package project.System;

import java.util.Map;
import java.util.Set;

public interface StockDataSystem {
    /**
     * 取得Map<股票名稱, 該股票資訊>
     * @return Map<股票名稱, 該股票資訊>
     */
    public abstract Map<String, Stock> getStocks();

    /**
     * 取得設有買賣設定的股票集合, Set<股票名稱>
     * @return
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
     * @param sellPrice : 目標賣價
     * @param buyPrice : 目標買價
     * @param step : 買賣價錢間隔
     */
    public abstract void saveBuyingSetting(String stockName, double sellPrice, double buyPrice, double step);

    /**
     * 取得使用者的Key和ID
     * @return 使用者的Key和ID
     */
    public abstract KeyAndID getKeyAndID();

    /**
     * 取得歷史紀錄
     * @return 歷史紀錄
     */
    public abstract HistoryRecord getHistoryRecord();
}
