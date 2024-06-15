package project;

import java.util.TimerTask;

import project.trade.TradingAgent;
import project.System.StockDataSystem;
import project.AlpacaAPICall.WebCrawler;


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
     * <p>
     * 4. 更新 GUI 畫面
     */
    public void run() {
        // 更新股票資料
        WebCrawler.stockPriceProcessing();

        // 執行交易
        System.out.println("trade");
        boolean trade_TF = this.tradingAgent.trade();

        // 更新歷史資料 ?
        if (trade_TF == true) {
            WebCrawler.historyTradingProcessing();
        }
        // else 不更新
        // TODO update GUI
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
    public BackgroundExexute(StockDataSystem stockDataSystem) {
        this.stockDataSystem = stockDataSystem;
        this.tradingAgent = new TradingAgent(stockDataSystem);
    }
}
