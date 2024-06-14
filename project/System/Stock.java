package project.System;

public class Stock {

    public static final double DOUBLE_MAX_VALUE = Double.MAX_VALUE;
    public static final double DOUBLE_MIN_VALUE = Double.MIN_VALUE;

    private String stockName = null;
    private double stockPrice = -DOUBLE_MAX_VALUE;
    private int stockCount = 0;

    /**
     * 建立一種股票的資料
     * @param stockName : 股票名稱
     * @param stockPrice : 股價
     * @param stockCount : (初始)預設為1股
     */
    public Stock(String stockName, double stockPrice, int stockCount) {
        
        setStockName(stockName);
        setStockPrice(stockPrice);
        setStockCount(stockCount);
    }

    /**
     * 取得該股票的名稱
     * @return 該股票的名稱
     */
    public String getStockName() {

        assert stockName != null && stockName.length() > 0 : "No stock name, may not be set yet";
        return stockName;
    }

    /**
     * 取得該股票的股價
     * @return 該股票的股價
     */
    public double getStockPrice() {

        assert stockPrice >= 0 : "stockPrice < 0, may not be set yet";
        return stockPrice;        
    }

    /**
     * 
     * @return
     */
    public int getStockCount() {
        assert stockCount >= 0 : "stockCount < 0";
        return stockCount;
    }

    public void setStockName(String stockName) {

        assert stockName != null & stockName.length() > 0 : "stockName is null or \"\"";
        this.stockName = stockName;
    }

    public void setStockPrice(double stockPrice) {

        assert stockPrice >= 0 : "stockPrice < 0";
        this.stockPrice = stockPrice;
    }

    public void setStockCount(int stockCount) {
        assert stockCount > 0 : "stockCount <= 0";
        this.stockCount = stockCount;
    }
}
