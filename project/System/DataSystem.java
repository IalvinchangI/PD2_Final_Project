package project.System;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class DataSystem implements StockDataSystem {

    private StockMarketInfoRecorder stockMarketInfoRecorder = null;
    private HistoryRecord historyRecord = null;
    private KeyAndID keyAndID = null;
    private StockBuyingSetting stockBuyingSetting = null;

    private HashMap<String, StockBuyingSetting> buyingSettings = null;
    private HashMap<String, Stock> stocksMap = null;

    public DataSystem() {

        stocksMap = new HashMap<>();
        buyingSettings = new HashMap<>();
        historyRecord = new HistoryRecord();
    }

    /**
     * 取得所有股票資料
     * @return 所有股票資料，用Map<stockName, stockinfo>
     */
    public Map<String, Stock> getStocksMap() {

        if (stocksMap.size() > 0) {
            return (Map<String, Stock>) stocksMap;
        }
        else {
            System.out.println("stocksMap has no content");
            return null;
        }
    }

    /**
     * 取得特定股票資料
     * @param stockName : 股票名稱
     * @return 該股票資料
     */
    public Stock getStock(String stockName) {

        assert stockName != null && stockName.length() > 0 : "stockName is null or \"\"";
        if (stocksMap.size() > 0) {

            if (stocksMap.containsKey(stockName)) {
                return stocksMap.get(stockName);
            }
            else {
                System.out.println("stocksMap has no info of " + stockName);
                return null;
            }
        }
        else {
            System.out.println("stocksMap has no content");
            return null;
        }
    }
    
    /**
     * 將一種股票加進stocksMap中
     * @param stockName : 股票名稱
     * @param stockPrice : 股價
     */
    public void addStock2StocksMap(String stockName, double stockPrice) {

        if (stocksMap.size() > 0) {

            if (stocksMap.containsKey(stockName)) {
                
                Stock stock = stocksMap.get(stockName);
                int currentStockCount = stock.getStockCount();

                stock.setStockCount(currentStockCount + 1);
                stock.setStockPrice(stockPrice);
                stocksMap.remove(stockName);

                stocksMap.put(stockName, stock);
            }
            else {
                Stock stock = new Stock(stockName, stockPrice, 1);
                stocksMap.put(stockName, stock);
            }
        }
        else {
            Stock stock = new Stock(stockName, stockPrice, 1);
            stocksMap.put(stockName, stock);
        }
    }

    /**
     * 取得設有買賣設定的股票集合, Set<股票名稱>
     * @return 買賣設定的股票集合, Set<股票名稱>
     */
    public Set<String> getStockNamesHasBuyingSetting() {

        if (buyingSettings.size() > 0) {
            return buyingSettings.keySet();
        }
        else {
            System.out.println("stockHasBuyingSetting has no content");
            return null;
        }
    }

    /**
     * 取得特定股票的買賣設定
     * @param stockName : 股票名稱
     * @return 該股票的買賣設定
     */
    public StockBuyingSetting getBuyingSetting(String stockName) {

        if (buyingSettings.size() > 0) {
            
            if (buyingSettings.containsKey(stockName)) {
                return buyingSettings.get(stockName);
            }
            else {
                System.out.println("buyingSettings has no info of " + stockName);
                return null;
            }
        }
        else {
            System.out.println("buyingSettings has no content");
            return null;
        }
    }

    /**
     * 儲存某股票的買賣設定
     * @param stockName : 該股票名稱
     * @param bidPrice : 目標買價
     * @param offerStep : 上漲多少錢賣出
     * @param bidStep : 下降多少錢買入
     */
    public void saveBuyingSetting(
        String stockName, double bidPrice, double offerStep, double bidStep, int stockCount
    ) {

        StockBuyingSetting buyingSetting = new StockBuyingSetting(
            stockName, bidPrice, offerStep, bidStep, stockCount
        );

        if (buyingSettings.size() > 0) {

            if (buyingSettings.containsKey(stockName)) {

                buyingSettings.remove(stockName);
                buyingSettings.put(stockName, buyingSetting);
            }
            else {
                buyingSettings.put(stockName, buyingSetting);
            }
        }
        else {
            buyingSettings.put(stockName, buyingSetting);
        }
    }

    /**
     * 設定使用者的Secret Key, KeyID
     * @param secretKey
     * @param KeyID
     */
    public void setKeyAndID(String secretKey, String KeyID) {

        keyAndID = new KeyAndID(secretKey, KeyID);
    }

    /**
     * 取得使用者的Secret Key, KeyID
     * @return 使用者的Secret Key, KeyID
     */
    public KeyAndID getKeyAndID() {

        assert keyAndID != null : "keyAndID is null, may not be set yet";
        return keyAndID;
    }

    /**
     * 取得特定股票的歷史交易紀錄
     * @return 該股票的歷史交易紀錄
     */
    public List<Deal> getHistoryRecord(String stockName) {

        return (List<Deal>)historyRecord.getRecords(stockName);
    }

    /**
     * 將一筆交易紀錄到History Record中
     * @param stockName :股票名稱
     * @param stockCount : 交易股數
     * @param profitAndLoss : 交易盈虧
     * @param year : 年份
     * @param month : 月份
     * @param date : 日期
     */
    public void addDeal2HistoryRecord(
        String stockName, int stockCount, double profitAndLoss, int year, int month, int date
    ) {

        Deal deal = new Deal(stockName, stockCount, profitAndLoss, year, month, date);

        if (historyRecord.record.size() > 0) {

            if (historyRecord.record.containsKey(stockName)) {

                historyRecord.record.get(stockName).add(deal);
            }
            else {
                ArrayList<Deal> deals = new ArrayList<>();
                deals.add(deal);
                historyRecord.record.put(stockName, deals);
            }
        }
        else {
            ArrayList<Deal> deals = new ArrayList<>();
            deals.add(deal);
            historyRecord.record.put(stockName, deals);
        }
    }
}
