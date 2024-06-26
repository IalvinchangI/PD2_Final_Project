package project.System;

import java.time.LocalDate;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MarketInfo {

    private String stockName = null;
    private String mode = null;
    private double openingPrice = -1;
    private double closingPrice = -1;
    private double highPrice = -1;
    private double lowPrice = -1;
    private String date = null;

    /**
     * 建立特定股票在市場上的資訊
     * @param stockName : 股票名稱
     * @param mode : 單一觀察的時間段長度 (mode: 每日"DAY", 每周"WEEK", 每月"MON")
     * @param openingPrice : 開盤價
     * @param closingPrice : 收盤價
     * @param highPrice : 最高價
     * @param lowPrice : 最低價
     * @param year : 年份
     * @param month : 月份
     * @param date : 日期
     */
    public MarketInfo(
        String stockName, String mode,
        double openingPrice, double closingPrice, double highPrice, double lowPrice,
        int year, int month, int date
    ) {
        
        assert stockName != null && stockName.length() > 0 : "stockName is null or \"\"";
        this.stockName = stockName;

        assert mode.equals("DAY") || mode.equals("WEEK") || mode.equals("MON")
        : "mode not equals to \"DAY\", \"WEEK\", or \"MON\"";
        this.mode = mode;

        this.date = LocalDate.of(year, month, date).toString();

        setOpeningPrice(openingPrice);
        setClosingPrice(closingPrice);
        setHighPrice(highPrice);
        setLowPrice(lowPrice);
    }

    /**
     * 取得股票名稱
     * @return 股票名稱
     */
    public String getStockName() {

        assert stockName != null && stockName.length() > 0
        : "stockName is null or\"\", may not be set yet";
        return stockName;
    }

    /**
     * 取得單一觀察的時間長度 (mode: 每日"DAY", 每周"WEEK", 每月"MON")
     * @return 單一觀察的時間長度
     */
    public String getMode() {

        assert mode.equals("DAY") || mode.equals("WEEK") || mode.equals("MON")
        : "mode not equals to \"DAY\", \"WEEK\", or \"MON\"";
        return mode;
    }

    /**
     * 取得觀察的時間
     * @return (mode:時間) "DAY":YYYY-MM-DD, "WEEK":(起始的)YYYY-MM-DD, "MON":(起始的)YYYY-MM-DD
     */
    public String getDate() {

        Pattern pattern = Pattern.compile("\\d+-\\d+-\\d+");
        Matcher matcher = pattern.matcher(date);

        if (matcher.matches() == false) {

            System.out.println("date should be YYYY-MM-DD");
            System.out.println("date: " + date);
            return null;
        }
        else {
            return date;
        }
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
