package project.System;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

public class DataSystem implements StockDataSystem {

    private HashMap< String, HashMap< String, StockMarketInfoRecorder >> marketInfoRecorderMap = null;
    private HistoryRecord historyRecord = null;
    private KeyAndID keyAndID = null;

    private HashMap<String, StockBuyingSetting> buyingSettings = null;
    private HashMap<String, Stock> stocksMap = null;

    private boolean isLogin = false;

    public DataSystem() {

        stocksMap = new HashMap<>();
        buyingSettings = new HashMap<>();
        historyRecord = new HistoryRecord();
        marketInfoRecorderMap = new HashMap<>();
        isLogin = false;
    }

    /**
     * 設定是否完成登入
     * @param isLogin : 是否登入
     */
    public void setLogin(boolean isLogin) {

        this.isLogin = isLogin;
    }

    /**
     * 檢查是否登入
     * @return 是否登入
     */
    public boolean checkIsLogin() {

        return isLogin;
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
                
                stocksMap.get(stockName).setStockPrice(stockPrice);
            }
            else {
                Stock stock = new Stock(stockName, stockPrice, 0);
                stocksMap.put(stockName, stock);
            }
        }
        else {
            Stock stock = new Stock(stockName, stockPrice, 0);
            stocksMap.put(stockName, stock);
        }
        System.out.println(stockName + "'s price has been uploaded");
        System.out.println(stocksMap.get(stockName).getStockPrice());
        System.out.println("stockCount: " + stocksMap.get(stockName).getStockCount() + "\n");
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

    public boolean checkBuyingSettingIsSet(String stockName) {
        
        if (buyingSettings.containsKey(stockName)) {

            return buyingSettings.get(stockName).getIsSet();
        }
        else {
            System.out.println(stockName + "has no buyingSetting");
            return false;
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
                buyingSettings.get(stockName).setIsSet(false);
                return buyingSettings.get(stockName);
            }
            else {
                System.out.println("buyingSettings has no info of " + stockName);
                return null;
            }
        }
        else {
            System.out.println(buyingSettings.size());
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
            stockName, bidPrice, offerStep, bidStep, stockCount, true
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
        System.out.println("buyingSettings' size: " + buyingSettings.size());
        System.out.println("bidPrice: " + buyingSettings.get(stockName).getBidPrice());
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

        return (List<Deal>)historyRecord.getRecord(stockName);
    }

    /**
     * 取得所有有歷史交易紀錄的股票集合
     * @return 所有有歷史交易紀錄的股票集合
     */
    public Set<String> getStockHasHistoryRecord() {

        if (historyRecord.getRecords() != null){

            return historyRecord.getRecords().keySet();
        }
        else {
            System.out.println("history records has no content");
            return null;
        }
    }

    /**
     * 取得總盈虧
     * @return 總盈虧
     */
    public double getTotalProfitAndLoss() {

        return historyRecord.getTotalProfitAndLoss();
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
        //System.out.println("addDeal2HistoryRecord : " + stockName + " " + stockCount + " " + profitAndLoss + " " + year + " " + month + " " + date);
        Deal deal = new Deal(stockName, stockCount, profitAndLoss, year, month, date);
        HashMap< String, ArrayList<Deal> > records = historyRecord.getRecords();

        if (records.size() > 0) {

            if (records.containsKey(stockName)) {

                records.get(stockName).add(deal);
            }
            else {
                ArrayList<Deal> deals = new ArrayList<>();
                deals.add(deal);
                records.put(stockName, deals);
            }
        }
        else {
            ArrayList<Deal> deals = new ArrayList<>();
            deals.add(deal);
            records.put(stockName, deals);
        }

        if (stocksMap.containsKey(stockName)) {

            int currentStockCount = stocksMap.get(stockName).getStockCount();
            currentStockCount += stockCount;
            stocksMap.get(stockName).setStockCount(currentStockCount);
        }
        else {
            Stock stock = new Stock(stockName, deal.getStockPrice(), stockCount);
            stocksMap.put(stockName, stock);
        }


        // System.out.println("\n********************************************");
        // System.out.println("deal about " + deal.getStockName() + " has been recorded");
        // System.out.println("deal profitAndLoss: " + deal.getProfitAndLoss());
        // System.out.println("stockCount: " + deal.getStockCount());
        // System.out.println("date: " + deal.getDateAndTime());
        // System.out.println("********************************************\n");
    }

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
    public void addStockMarketInfo2Recorder(
        String stockName, String mode,
        double highPrice, double lowPrice, double openingPrice, double closingPrice,
        int year, int month, int date
    ) {

        System.out.println("mode: " + mode);
        if (marketInfoRecorderMap.size() > 0) {

            if (marketInfoRecorderMap.containsKey(stockName)) {

                if (marketInfoRecorderMap.get(stockName).containsKey(mode)) {

                    marketInfoRecorderMap.get(stockName).get(mode).addMarketInfo2Recorder(
                        stockName, mode, highPrice, lowPrice, openingPrice, closingPrice, year, month, date
                    );
                }
                else {
                    StockMarketInfoRecorder stockMarketInfoRecorder = new StockMarketInfoRecorder(stockName, mode);

                    stockMarketInfoRecorder.addMarketInfo2Recorder(
                        stockName, mode, highPrice, lowPrice, openingPrice, closingPrice, year, month, date
                    );
                    marketInfoRecorderMap.get(stockName).put(mode, stockMarketInfoRecorder);
                }
            }
            else {
                StockMarketInfoRecorder stockMarketInfoRecorder = new StockMarketInfoRecorder(stockName, mode);
                
                stockMarketInfoRecorder.addMarketInfo2Recorder(
                    stockName, mode, highPrice, lowPrice, openingPrice, closingPrice, year, month, date
                );
                HashMap<String, StockMarketInfoRecorder> map = new HashMap<>();
                map.put(mode, stockMarketInfoRecorder);
                marketInfoRecorderMap.put(stockName, map);
            }
        }
        else {
            StockMarketInfoRecorder stockMarketInfoRecorder = new StockMarketInfoRecorder(stockName, mode);

            stockMarketInfoRecorder.addMarketInfo2Recorder(
                stockName, mode, highPrice, lowPrice, openingPrice, closingPrice, year, month, date
            );
            HashMap<String, StockMarketInfoRecorder> map = new HashMap<>();
            map.put(mode, stockMarketInfoRecorder);
            marketInfoRecorderMap.put(stockName, map);
        }
    }

    /**
     * 取得對特定股票在市場上的資訊的觀察紀錄陣列recorder (List<MarketInfo>)
     * @param stockName : 股票名稱
     * @param mode : 單一觀察的時間段長度 (mode: 每日"DAY", 每周"WEEK", 每月"MON")(預設為"DAY")
     * @return 觀察紀錄陣列recorder (List<MarketInfo>)
     */
    public List<MarketInfo> getRecorder(String stockName, String mode) {

        if (marketInfoRecorderMap.containsKey(stockName)) {

            if (marketInfoRecorderMap.get(stockName).containsKey(mode)) {

                return marketInfoRecorderMap.get(stockName).get(mode).getRecorder();
            }
            else {
                System.out.println("recorder about " + stockName + " with mode: " + "\"" + mode + "\"not exist");
                return null;
            }
        }
        else {
            System.out.println("recorders about " + stockName + " not exist");
            return null;
        }
    }
}
