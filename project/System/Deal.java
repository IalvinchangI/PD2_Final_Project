package project.System;

import java.util.Date;;

public interface Deal {
    /**
     * 取得該次交易中的股票名稱
     * @return 該次交易中的股票名稱
     */
    public abstract String getStockName();

    /**
     * 取得該次交易的時間
     * @return 該次交易的時間
     */
    public abstract Date getDateAndTime();

    /**
     * 取得該次交易的盈虧
     * @return 該次交易的盈虧
     */
    public abstract double getProfitAndLoss();

    /**
     * 取得該次交易的股數
     * @return 該次交易的股數
     */
    public abstract int getStockCount();

    /**
     * 儲存該次交易的股票名稱
     * @param stockName : 該次交易的股票名稱
     */
    public abstract void setStockName(String stockName);

    /**
     * 儲存該次交易的時間
     * @param year : 年份
     * @param month : 月份
     * @param date : 日期
     * @param hour : 幾點
     * @param minute : 幾分
     * @param second : 幾秒
     */
    public abstract void setDateAndTime(int year, int month, int date, int hour, int minute, int second);

    /**
     * 儲存該次交易的盈虧
     * @param profitAndLoss : 該次交易的盈虧
     */
    public abstract void setProfitAndLoss(double profitAndLoss);

    /**
     * 儲存該次交易的股數
     * @param stockCount : 該次交易的股數
     */
    public abstract void setStockCount(int stockCount);
}
