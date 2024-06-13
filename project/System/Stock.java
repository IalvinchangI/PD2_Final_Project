package project.System;

public interface Stock {
    /**
     * 取得該股票的名稱
     * @return 該股票的名稱
     */
    public abstract String getStockName();

    /**
     * 取得該股票的股價
     * @return 該股票的股價
     */
    public abstract double getStockPrice();
}
