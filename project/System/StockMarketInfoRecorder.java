package project.System;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class StockMarketInfoRecorder {

    private String stockName = null;
    private ArrayList< MarketInfo > recorder = null;
    private MarketInfo marketInfo = null;
    private String mode = null;

    /**
     * 建立特定股票在市場上的資訊
     * @param stockName : 股票名稱
     * @param period : 紀錄週期(日:"DAY", 周:"WEEK", 月:"MON")
     * @param highPrice : 最高價
     * @param lowPrice : 最低價
     * @param openingPrice : 開盤價
     * @param closingPrice : 收盤價
     * @param year : 年份
     * @param month : 月份
     * @param date : 日期
     */
    public StockMarketInfoRecorder(String stockName, String period) {
        
        recorder = new ArrayList<>();

        assert stockName != null && stockName.length() > 0 : "Invalid stockName";
        this.stockName = stockName;

        assert period != null && period.length() > 0 : "Invalid period";
        assert period.equals("DAY") || period.equals("WEEK") || period.equals("MON")
         : "only DAY, WEEK, or MON modes can be chosen";

        mode = period;
    }

    public void addMarketInfo2Recorder(
        String stockName, String period, 
        double highPrice, double lowPrice, double openingPrice, double closingPrice, 
        int year, int month, int date
    ) {
       
        MarketInfo marketInfo = new MarketInfo(
            stockName, openingPrice, closingPrice, highPrice, lowPrice, year, month, date
        );

        recorder.add(marketInfo);
    }

    /**
     * 取得被紀錄的股票名稱
     * @return 被紀錄的股票名稱
     */
    public String getStockName() {
        
        assert stockName != null && stockName.length() > 0 : "No stock name, may not be set yet";
        return stockName;
    }

    public String getMode() {
        assert mode != null && mode.length() > 0 : "mode is null or \"\"";
        assert mode.equals("DAY") || mode.equals("WEEK") || mode.equals("MON")
         : "only DAY, WEEK, or MON modes can be chosen";

        return mode;
    }

    public List< MarketInfo > getRecorder() {
        assert recorder != null : "recorder is null, may not be set yet";
        return recorder;
    }
}

class MarketInfo {

    private String stockName = null;
    private double openingPrice = -1;
    private double closingPrice = -1;
    private double highPrice = -1;
    private double lowPrice = -1;
    private String date = null;

    public MarketInfo(String stockName, double openingPrice, double closingPrice, double highPrice, double lowPrice, int year, int month, int date) {
        
        assert stockName != null && stockName.length() > 0 : "stockName is null or \"\"";
        this.stockName = stockName;

        this.date = LocalDate.now().toString();

        setOpeningPrice(openingPrice);
        setClosingPrice(closingPrice);
        setHighPrice(highPrice);
        setLowPrice(lowPrice);
    }

    /**
     * 取得開盤價
     * @return 開盤價
     */
    public double getOpeningPrice() {
        assert openingPrice >= 0 : "openingPrice < 0";
        return openingPrice;
    }

    /**
     * 取得收盤價
     * @return 收盤價
     */
    public double getClosingPrice() {
        assert closingPrice >= 0 : "closingPrice < 0";
        return closingPrice;
    }

    /**
     * 取得最高價
     * @return 最高價
     */
    public double getHighPrice() {
        assert highPrice >= 0 : "highPrice < 0";
        return highPrice;
    }

    /**
     * 取得最低價
     * @return 最低價
     */
    public double getLowPrice() {
        assert lowPrice >= 0 : "lowPrice < 0";
        return lowPrice;
    }

    /**
     * 設定開盤價
     * @param openingPrice : 開盤價
     */
    public void setOpeningPrice(double openingPrice) {
        assert openingPrice >= 0 : "openingPrice < 0";
        this.openingPrice = openingPrice;
    }
    /**
     * 設定收盤價
     * @param closingPrice : 收盤價
     */
    public void setClosingPrice(double closingPrice) {
        assert closingPrice >= 0 : "closingPrice < 0";
        this.closingPrice = closingPrice;
    }
    /**
     * 設定最高價
     * @param highPrice : 最高價
     */
    public void setHighPrice(double highPrice) {
        assert highPrice >= 0 : "highPrice < 0";
        this.highPrice = highPrice;
    }

    /**
     * 設定最低價
     * @param lowPrice : 最低價
     */
    public void setLowPrice(double lowPrice) {
        assert lowPrice >= 0 : "lowPrice < 0";
        this.lowPrice = lowPrice;
    }
}