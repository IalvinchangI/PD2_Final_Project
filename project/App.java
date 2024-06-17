package project;

import java.util.Timer;


import project.AlpacaAPICall.WebCrawler;

import project.GUI.MainWindow;

import project.System.StockDataSystem;
import project.System.DataSystem;


/**
 * main class for this project
 */
public class App {
    /**
     * 交易間隔時間、股票資料更新間隔時間
     * <p>
     * 單位：ms
     */
    public static final long TRANSACTION_INTERVAL = 5 * 60 * 1000;


    /** 背景執行的時鐘 */
    private static Timer backgroundTimer = null;


    /** 存資料的地方 */
    private static StockDataSystem stockDataSystem = null;


    /** 主螢幕 */
    private static MainWindow window = null;


    /**
     * 程式進入點
     * @param args
     */
    public static void main(String[] args) {
        // new StockDataSystem, Timer, GUI and load StockDataSystem
        App.stockDataSystem = new DataSystem();  // new StockDataSystem
        WebCrawler.downloadStockDataSystem(App.stockDataSystem);  // load StockDataSystem
        App.window = new MainWindow("股票機器人", 1400, 850, stockDataSystem);

        window.setVisible(true);
    }


    /**
     * 1. 爬一開始的資料
     * <p>
     * 2. 存到 stockDataSystem
     */
    public static void crawlAndStoreData() {
        // market is open?
        System.out.println("checkMarketOpen");
        boolean marketOpen_TF = WebCrawler.checkMarketOpen();

        // crawl data
        System.out.println("stockDataProcessing");
        WebCrawler.stockDataProcessing();  // 查詢 30 天股價
        System.out.println("historyTradingProcessing");
        WebCrawler.historyTradingProcessing();  // 爬歷史資料
        if (marketOpen_TF == true) {
            System.out.println("stockPriceProcessing");
            WebCrawler.stockPriceProcessing();  // 查詢時價
        }
        else {
            System.out.println("Not Open");
        }
    }


    /**
     * 啟動 backgroundTimer，並設定 backgroundExexute
     */
    public static void initBackgroundExecute() {
        App.backgroundTimer = new Timer();  // new Timer; running the program in BackgroundExecute
        
        // schedule BackgroundExecute
        BackgroundExexute backgroundExexute = new BackgroundExexute(App.stockDataSystem, App.window);
        System.out.println("backgroundExexute");
        backgroundTimer.schedule(backgroundExexute, 100, TRANSACTION_INTERVAL);
    }


    /**
     * 把 backgroundExexute 的 Timer 停掉
     * <p>
     * 如果還未執行 {@code initBackgroundExecute} 不做任何事
     */
    public static void stopBackgroundExecute() {
        if (App.backgroundTimer != null) {
            App.backgroundTimer.cancel();
        }
    }
}
