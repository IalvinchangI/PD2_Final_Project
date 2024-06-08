package project;

import java.util.TimerTask;

import project.trade.TradingAgent;;
// import StockDataSystem


/**
 * 要在背景定時執行的程序
 * @author IalvinchangI
 */
public class BackgroundExexute extends TimerTask {
    /**
     * 定時執行
     * <p>
     * 1. 更新股票資料
     * <p>
     * 2. 執行交易 (透過{@code TradingAgent})
     * <p>
     * 3. 更新歷史資料
     */
    public void run() {

    }


    /** 
     * 存取資料的地方
     * <p>
     * StockDataSystem的方法 : {@link StockDataSystem}
     */
    private StockDataSystem stockDataSystem = null;


    /**
     * 交易機器人
     */
    private TradingAgent tradingAgent = null;


    /**
     * 1. 創建物件
     * <p>
     * 2. 設定 能存取資料的地方
     * <p>
     * 3. 創建交易機器人
     * @param stockDataSystem 能存取資料的地方
     */
    public BackgroundExecute(StockDataSystem stockDataSystem) {
        this.stockDataSystem = stockDataSystem;
        this.tradingAgent = new TradingAgent(stockDataSystem);
    }
}
