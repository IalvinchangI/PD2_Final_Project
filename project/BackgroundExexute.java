package project;

import java.util.TimerTask;

import project.trade.TradingAgent;
import project.System.StockDataSystem;
import project.AlpacaAPICall.WebCrawler;
import project.GUI.MainWindow;


/**
 * 要在背景定時執行的程序
 * @author IalvinchangI
 */
public class BackgroundExexute extends TimerTask {
    /**
     * 定時執行
     * <p>
     * 0. 檢查是否開市
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
        if (WebCrawler.checkMarketOpen() == true) {
            // 更新股票資料
            WebCrawler.stockPriceProcessing();

            // 執行交易
            System.out.println("trade");
            boolean trade_TF = this.tradingAgent.trade();
            // boolean trade_TF = true;

            // 更新歷史資料 ?
            if (trade_TF == true) {
                WebCrawler.historyTradingProcessing();
            }
            // else 不更新

            // update GUI
            this.window.updateMainWindow();
        }
        else {
            System.out.println("Not Open");
        }
    }


    /** 
     * 存取資料的地方
     * <p>
     * StockDataSystem的方法 : {@link StockDataSystem}
     */
    private StockDataSystem stockDataSystem = null;
    
    
    /**
     * 主視窗
     */
    private MainWindow window = null;


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
    public BackgroundExexute(StockDataSystem stockDataSystem, MainWindow window) {
        this.stockDataSystem = stockDataSystem;
        this.window = window;
        this.tradingAgent = new TradingAgent(stockDataSystem);
    }
}
