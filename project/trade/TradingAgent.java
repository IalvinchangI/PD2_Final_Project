package project.trade;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;

// import StockDataSystem
// import StockBuyingSetting


/**
 * 交易機器人
 * @author IalvinchangI
 */
public class TradingAgent {

    public final static String ORDER_URL = "https://paper-api.alpaca.markets/v2/orders";


    /** 
     * 能取得{@code StockBuyingSetting}的地方
     * <p>
     * StockDataSystem的方法 : {@link StockDataSystem}
     */
    private StockDataSystem stockDataSystem = null;


    /**
     * 1. 創建物件
     * <p>
     * 2. 設定 能取得{@code StockBuyingSetting}的地方
     * @param stockDataSystem 能取得{@code StockBuyingSetting}的地方
     */
    public TradingAgent(StockDataSystem stockDataSystem) {
        this.stockDataSystem = stockDataSystem;
    }


    /**
     * 在目前市場狀況下，是否有股票符合設定的交易標準，如果有，就買賣該股票
     * @return 是否有執行交易
     */
    public boolean trade() {
        boolean trade_TF = false;
        
        for (String stockName : stockDataSystem.get__________) {  // TODO
            StockBuyingSetting buyingSetting = stockDataSystem.getBuyingSetting(stockName);
            // according to buyingSetting, decide what should do (buy or sell)
            // case1: throw buying request and (trade_TF = true)
            // case2: throw selling request and (trade_TF = true)
            // otherwise, check next stock
        }

        return trade_TF;
    }


    /**
     * 連上Alpaca並遞送訂單
     * @param stockName 股票代號
     * @param quantity number of shares to trade. Can be fractionable for only market and day order types.
     * @param type market / limit / stop / stop_limit / trailing_stop
     * @param side buy / sell
     * @return 是否成功交易
     */
    private boolean request(String stockName, int quantity, String type, String side) {
        // order: json format
        String json = String.format("{\"symbol\":\"%s\",\"qty\":%d,\"side\":\"%s\",\"type\":\"%s\"}", stockName, quantity, side, type);

        // new url
        URL url = new URL(ORDER_URL);

        // new HttpURLConnection
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // set HTTP method
        connection.setRequestMethod("POST");

        // set HTTP header
        connection.setRequestProperty("Content-Type", "application/json");
        KeyAndID key_id = stockDataSystem.getKeyAndID();
        connection.setRequestProperty("APCA-API-KEY-ID", key_id.getAPIKey());
        connection.setRequestProperty("APCA-API-SECRET-KEY", key_id.getAPIID());

        // ask for write
        connection.setDoOutput(true);

        // write request
        try (DataOutputStream writer = new DataOutputStream(connection.getOutputStream())) {
            writer.writeBytes(json);
            writer.flush();
        }

        // read response
        boolean success_TF = true;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            // TODO 有問題的話，要把 success_TF = false
            System.out.println(response.toString());
        }

        // 關閉連接
        connection.disconnect();
        return success_TF;
    }

    /**
     * 連上Alpaca並遞送購買股票的訂單
     * @param stockName 股票代號
     * @param quantity number of shares to trade. Can be fractionable for only market and day order types.
     * @param type market / limit / stop / stop_limit / trailing_stop
     * @return 是否成功交易
     */
    private boolean buyingRequest(String stockName, int quantity, String type) {
        return request(stockName, quantity, type, "buy");
    }
    
    /**
     * 連上Alpaca並遞送販賣股票的訂單
     * @param stockName 股票代號
     * @param quantity number of shares to trade. Can be fractionable for only market and day order types.
     * @param type market / limit / stop / stop_limit / trailing_stop
     * @return 是否成功交易
     */
    private boolean sellingRequest(String stockName, int quantity, String type) {
        return request(stockName, quantity, type, "sell");
    }
}
    

