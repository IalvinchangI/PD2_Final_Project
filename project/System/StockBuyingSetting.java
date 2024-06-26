package project.System;

public class StockBuyingSetting {

    public static final double DOUBLE_MAX_VALUE = Double.MAX_VALUE;
    public static final double DOUBLE_MIN_VALUE = Double.MIN_VALUE;
    public static final int INTEGER_MAX_VALUE = Integer.MAX_VALUE;
    public static final int INTEGER_MIN_VALUE = Integer.MIN_VALUE;

    private String stockName = null;
    private double bidPrice = -1;
    private double offerStep = -1;
    private double bidStep = -1;
    private int stockCount = 0;
    private boolean isSet = false;

    /**
     * 建立一個買賣設定
     * @param stockName : 股票名稱
     * @param bidPrice : 目標買價
     * @param offerStep : 上漲多少錢時賣出
     * @param bidStep : 下降多少錢時買入
     * @param stockCount : 股數
     */
    public StockBuyingSetting(
        String stockName, double bidPrice, double offerStep, double bidStep, int stockCount, boolean isSet
    ) {

        System.out.println("Buying setting of " + stockName + " has been set");
        setStockName(stockName);
        setBidPrice(bidPrice);
        setOfferStep(offerStep);
        setBidStep(bidStep);
        setStockCount(stockCount);
        setIsSet(true);
        
    }

    public void setIsSet(boolean isSet) {

        this.isSet = isSet;
    }

    public boolean getIsSet() {

        return isSet;
    }

    /**
     * 取得股票名稱
     * @return 該股票名稱
     */
    public String getStockName() {

        assert stockName != null && stockName.length() > 0 : "stockName is null or \"\"";
        return stockName;
    }

    /**
     * 取得該股票的目標買價
     * @return 該股票的目標買價
     */
    public double getBidPrice() {

        assert bidPrice >= 0 : "bidPrice < 0, may not be set yet";
        return bidPrice;
    }

    /**
     * 取得上漲多少錢時賣出
     * @return 上漲多少錢時賣出
     */
    public double getOfferStep() {

        assert offerStep > 0 : "offerStep <= 0, may not be set yet";
        return offerStep;
    }

    /**
     * 取得下跌多少錢時買入
     * @return 下跌多少錢時買入
     */
    public double getBidStep() {

        assert bidStep > 0 : "bidStep <= 0, may not be set yet";
        return bidStep;
    }

    /**
     * 取得該股票交易的股數
     * @return 該股票交易的股數
     */
    public int getStockCount() {

        assert stockCount > 0 : "stockCount <= 0";
        return stockCount;
    }

    /**
     * 設定股票名稱
     * @param stockName : 該股票名稱
     */
    public void setStockName(String stockName) {

        assert stockName != null && stockName.length() > 0 : "stockName is null or \\\"\\\"";
        this.stockName = stockName;
    }

    /**
     * 設定目標買價
     * @param bidPrice : 目標買價
     */
    public void setBidPrice(double bidPrice) {

        assert bidPrice >= 0 : "bid pirce < 0";
        this.bidPrice = bidPrice;
    }

    /**
     * 設定上漲多少錢時賣出
     * @param offerStep : 上漲多少錢時賣出
     */
    public void setOfferStep(double offerStep) {

        assert offerStep >= 0 : "offer step < 0";
        this.offerStep = offerStep;
    }

    /**
     * 設定下跌多少錢時買入
     * @param bidStep : 下跌多少錢時買入
     */
    public void setBidStep(double bidStep) {

        assert bidStep >= 0 : "bidStep < 0";
        this.bidStep = bidStep;
    }

    /**
     * 設定交易的股數
     * @param stockCount : 交易的股數
     */
    public void setStockCount(int stockCount) {

        assert stockCount > 0 : "stockCount <= 0";
        this.stockCount = stockCount;
    }
}
