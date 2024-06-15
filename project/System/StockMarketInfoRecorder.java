package project.System;

import java.util.List;
import java.util.ArrayList;

public class StockMarketInfoRecorder {

    private String stockName = null;
    private ArrayList< MarketInfo > recorder = null;
    private String mode = null;

    /**
     * 建立一種股票，指定時間段中在市場上的資訊
     * @param stockName : 股票名稱
     * @param mode : 單一觀察時間段的長度 (mode: 每日"DAY", 每周"WEEK", 每月"MON")(預設為"DAY")
     */
    public StockMarketInfoRecorder(String stockName, String mode) {
        
        recorder = new ArrayList<>();

        assert stockName != null && stockName.length() > 0 : "Invalid stockName";
        this.stockName = stockName;

        if (mode == null || mode.length() == 0) {

            this.mode = "DAY";
        }
        else if ((mode.equals("DAY") || mode.equals("WEEK") || mode.equals("MON")) == false) {

            this.mode = "DAY";
        }
        else {
            this.mode = mode;
        }
    }

    /**
     * 將單一時間段中，該股票的市場資訊放入紀錄陣列recorder (List<MarketInfo>)中
     * @param stockName : 股票名稱
     * @param highPrice : 最高價
     * @param lowPrice : 最低價
     * @param openingPrice : 開盤價
     * @param closingPrice : 收盤價
     * @param year : 年份
     * @param month : 月份
     * @param date : 日期
     */
    public void addMarketInfo2Recorder(
        String stockName,  String mode, 
        double highPrice, double lowPrice, double openingPrice, double closingPrice, 
        int year, int month, int date
    ) {
       
        MarketInfo marketInfo = new MarketInfo(
            stockName, mode, openingPrice, closingPrice, highPrice, lowPrice, year, month, date
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

    /**
     * 取得單一觀察的時間段長度 (mode: 每日"DAY", 每周"WEEK", 每月"MON")(預設為"DAY")
     * @return 觀察的時間段長度
     */
    public String getMode() {

        assert mode != null && mode.length() > 0 : "mode is null or \"\"";
        assert mode.equals("DAY") || mode.equals("WEEK") || mode.equals("MON")
         : "only DAY, WEEK, or MON modes can be chosen";

        return mode;
    }

    /**
     * 取得觀察的紀錄陣列recorder (List<MarketInfo>)
     * @return 紀錄陣列recorder
     */
    public List< MarketInfo > getRecorder() {
        assert recorder != null : "recorder is null, may not be set yet";

        return recorder;
    }
}
