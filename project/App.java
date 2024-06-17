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
    public static final long TRANSACTION_INTERVAL = 10 * 1000;


    /** 背景執行的時鐘 */
    private static Timer backgroundTimer = null;


    /** 存資料的地方 */
    private static StockDataSystem stockDataSystem = null;


    private static MainWindow window = null;


    /**
     * 程式進入點
     * @param args
     */
    public static void main(String[] args) {
        // new StockDataSystem, Timer, GUI and load StockDataSystem
        App.stockDataSystem = new DataSystem();  // TODO new StockDataSystem
        WebCrawler.downloadStockDataSystem(App.stockDataSystem);  // load StockDataSystem
        App.window = new MainWindow("股票機器人", 1400, 850, stockDataSystem);
        
        
        // GUI login


        // String KEY_ID = "PKG2UYG7EYP063HG5USI";
        // String SECRET_KEY = "dn8AVuR8Ux6VRZhI6IW0fP86HtMjldBhkPLFJPVa";
        // stockDataSystem.setKeyAndID(SECRET_KEY, KEY_ID);

        // if (WebCrawler.check_Key_ID() == false) {
        //     System.out.println("KEY_ID or SECRET_KEY 是錯的");
        //     System.exit(1);
        // }


        // System.out.println("saveBuyingSetting");
        // String[] symbols    = {"AAPL", "GOOGL", "META", "TSLA", "AMZN", "MSFT"};
        // double[] buyPrice   = {212.6,  178.,    504.,   178.5,  183.,   442.  };  // 設定的買入價格
        // double[] bidStep    = {0.2,    0.05,    0.2,    0.1,    0.1,    0.2   };  // 設定的買入間隔
        // double[] offerStep  = {0.3,    0.1,     0.3,    0.2,    0.1,    0.4   };  // 設定的賣出間隔
        // for (int i = 0; i < symbols.length; i++) {
        //     stockDataSystem.saveBuyingSetting(symbols[i], buyPrice[i], offerStep[i], bidStep[i], 1);
        // }

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
        backgroundTimer.schedule(backgroundExexute, TRANSACTION_INTERVAL, TRANSACTION_INTERVAL);
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
