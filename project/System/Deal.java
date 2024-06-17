package project.System;

import java.time.LocalDate;

public class Deal {
    public static final double DOUBLE_MAX_VALUE = Double.MAX_VALUE;
    public static final double DOUBLE_MIN_VALUE = Double.MIN_VALUE;

    private String stockName = null;
    private int stockCount = 0;
    private String date = null;
    private double profitAndLoss = -DOUBLE_MAX_VALUE;
    private boolean isValid = false;

    /**
     * 建立一筆交易資料
     * @param stockName : 股票名稱
     * @param stockCount : 交易股數
     * @param profitAndLoss : 交易盈虧
     * @param year : 年份
     * @param month : 月份
     * @param date : 日期
     */
    public Deal(String stockName, int stockCount, double profitAndLoss, int year, int month, int date) {
        
        setStockName(stockName);
        setStockCount(stockCount);
        setProfitAndLoss(profitAndLoss);
        setDate(year, month, date);
        isValid = true;
    }

    /**
     * 取得該次交易中的股票名稱
     * @return 該次交易中的股票名稱
     */
    public String getStockName(){

        assert stockName != null && stockName.length() > 0 : "stockName is null or \"\"";
        return stockName;
    }

    /**
     * 取得該次交易的時間
     * @return 該次交易的時間
     */
    public String getDateAndTime() {

        assert date != null && date.length() > 0 : "date is null or \"\", may not be set yet";
        return date;
    }

    /**
     * 取得該次交易的盈虧
     * @return 該次交易的盈虧
     */
    public double getProfitAndLoss() {

        if (profitAndLoss == -DOUBLE_MAX_VALUE) {
            System.out.println("No profitAndLoss, may not be set yet");
            return -DOUBLE_MAX_VALUE;
        }
        else {
            return profitAndLoss;
        }
    }

    /**
     * 取得該次交易的股數
     * @return 該次交易的股數
     */
    public int getStockCount() {

        if (stockCount <= 0) {
            System.out.println("No stock count, may not be set yet");
            return 0;
        }
        else {
            return stockCount;
        }
    }

    /**
     * 儲存該次交易的股票名稱
     * @param stockName : 該次交易的股票名稱
     */
    public void setStockName(String stockName) {

        assert (stockName != null) && (stockName.length() != 0) : "Invalid stock name";
        this.stockName = stockName;
    }

    /**
     * 儲存該次交易的時間
     * @param year : 年份
     * @param month : 月份
     * @param date : 日期
     */
    public void setDate(int year, int month, int date) {

        this.date = LocalDate.of(year, month, month).toString();
    }

    /**
     * 儲存該次交易的盈虧
     * @param profitAndLoss : 該次交易的盈虧
     */
    public void setProfitAndLoss(double profitAndLoss) {

        this.profitAndLoss = profitAndLoss;
    }

    /**
     * 儲存該次交易的股數
     * @param stockCount : 該次交易的股數
     */
    public void setStockCount(int stockCount) {

        assert stockCount > 0 : "stock must > 0";
        this.stockCount = stockCount;
    }

    /**
     * 取得股價 (abs(profitAndLoss)/stockCount)
     * @return 股價
     */
    public double getStockPrice() {

        assert stockCount > 0 : "stockCount <= 0";
        return Math.abs(profitAndLoss/stockCount);
    }

    /**
     * 確認這筆交易是有效的
     * @return 交易的有效性
     */
    public boolean checkDealValid() {
        return isValid;
    }
}
