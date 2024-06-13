package project.System;

public interface StockBuyingSetting {

    /**
     * 取得股票名稱
     * @return 該股票名稱
     */
    public abstract String getStockName();

    /**
     * 取得該股票的目標賣價
     * @return 該股票的目標賣價
     */
    public abstract double getSellPrice();

    /**
     * 取得該股票的目標買價
     * @return 該股票的目標買價
     */
    public abstract double getBuyPrice();

    /**
     * 取得買賣價錢間隔
     * @return 買賣價錢間隔
     */
    public abstract double getStep();

    /**
     * 設定股票名稱
     * @param stockName : 該股票名稱
     */
    public abstract void setStockName(String stockName);

    /**
     * 設定目標賣價
     * @param sellPrice : 目標賣價
     */
    public abstract void setSellPrice(double sellPrice);

    /**
     * 設定目標買價
     * @param buyPrice : 目標買價
     */
    public abstract void setBuyPrice(double buyPrice);

    /**
     * 設定買賣價錢間隔
     * @param step : 買賣價錢間隔
     */
    public abstract void setStep(double step);
}
