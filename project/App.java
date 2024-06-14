package project;

import java.util.Timer;

import project.BackgroundExexute;

import project.AlpacaAPICall.WebCrawler;

import project.System.StockDataSystem;

import project.System.StockBuyingSetting;

/**
 * main class for this project
 */
public class App {
    /**
     * 交易間隔時間、股票資料更新間隔時間
     * <p>
     * 單位：ms
     */
    public static final long TRANSACTION_INTERVAL = 60 * 1000;

    /**
     * 程式進入點
     * @param args
     */
    public static void main(String[] args) {
        // new StockDataSystem, Timer, GUI and load StockDataSystem
        StockDataSystem stockDataSystem = new _____();  // TODO new StockDataSystem
        WebCrawler.downloadStockDataSystem(stockDataSystem);  // load StockDataSystem
        Timer backgroundTimer = new Timer();  // new Timer; running the program in BackgroundExecute
        // TODO new GUI

        // GUI login
        String KEY_ID = "PKG2UYG7EYP063HG5USI";
        String SECRET_KEY = "dn8AVuR8Ux6VRZhI6IW0fP86HtMjldBhkPLFJPVa";
        stockDataSystem.setKeyAndID(SECRET_KEY, KEY_ID);

        if (WebCrawler.check_Key_ID() == false) {
            System.out.println("KEY_ID or SECRET_KEY 是錯的");
            System.exit(1);
        }


        String[] symbols    = {"AAPL", "GOOGL", "META", "TSLA", "AMZN", "MSFT"};
        double[] buyPrice   = {212.,   178.,    504.,   178.5,  183.,   442.  };  // 設定的買入價格
        double[] bidStep    = {0.2,    0.05,    0.2,    0.1,    0.1,    0.2   };  // 設定的買入間隔
        double[] offerStep  = {0.3,    0.1,     0.3,    0.2,    0.1,    0.4   };  // 設定的賣出間隔
        for (int i = 0; i < symbols.length; i++) {
            stockDataSystem.saveBuyingSetting(symbols[i], buyPrice[i], bidStep[i], offerStep[i]);
        }


        // market is open?
        boolean marketOpen_TF = WebCrawler.checkMarketOpen();


        // crawl data
        WebCrawler.stockDataProcessing();  // 查詢 30 天股價
        WebCrawler.historyTradingProcessing();  // 爬歷史資料
        if (marketOpen_TF == true) {
            WebCrawler.stockPriceProcessing();  // 查詢時價
        }

        // schedule BackgroundExecute if market open
        BackgroundExexute backgroundExexute = null;
        if (marketOpen_TF == true) {
            backgroundExexute = new BackgroundExexute(stockDataSystem);
            backgroundTimer.schedule(backgroundExexute, TRANSACTION_INTERVAL, TRANSACTION_INTERVAL);
        }
    }
}
