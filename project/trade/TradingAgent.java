package project.trade;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import project.System.StockDataSystem;
import project.System.KeyAndID;
import project.System.Stock;
import project.System.StockBuyingSetting;
import project.System.Deal;


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
        System.out.println("TRADING");
        boolean trade_TF = false;  // 是否有執行交易

        Set<String> stockNames = this.stockDataSystem.getStockNamesHasBuyingSetting();
        if (stockNames != null) {  // 有買賣
            for (String stockName : stockNames) {
                // according to buyingSetting, decide what should do (buy or sell)
                // case1: throw buying request and (trade_TF = true)
                // case2: throw selling request and (trade_TF = true)
                // otherwise, check next stock
                
                // get data
                boolean hasChanged_TF = stockDataSystem.checkBuyingSettingIsSet(stockName);  // 上次拿之後，有無更改 buyingSetting
                StockBuyingSetting buyingSetting = stockDataSystem.getBuyingSetting(stockName);
                List<Deal> stockHistoryRecord = stockDataSystem.getHistoryRecord(stockName);
                // Deal newestStockHistoryRecord = stockHistoryRecord.get(0);  // 取得 某股票的最新交易紀錄
                Stock stock = stockDataSystem.getStock(stockName);  // 現在股票資料
                System.out.println(stockName);
                System.out.println(stock.getStockPrice());


                // get detail
                double newestDealPrice = 0;
                if (hasChanged_TF == true || stockHistoryRecord == null) {  // if (it has changed) or (it is the first trade)
                    newestDealPrice = buyingSetting.getBidPrice();  // 將 上次交易價 設為 使用者設定的買入價格
                }
                else {  // hasChanged_TF == false => 自上次 get 以來，buyingSetting 沒變
                    newestDealPrice = stockHistoryRecord.get(0).getStockPrice();  // 取得 上次交易價 (單張股票)
                }
                double nowStockPrice = stock.getStockPrice();  // 取得 現在股價
                double priceDelta = nowStockPrice - newestDealPrice;  // 現在股價 和 上次交易價 (or 使用者設定的買入價格) 的價格差


                // buy
                if (nowStockPrice <= buyingSetting.getBidPrice()) {  // 現在股價 比 設定的買入價格 低or相等
                    if ((priceDelta / buyingSetting.getBidStep()) <= -1.) {  // 價格差(負的) 比 設定的買入間隔 大
                        if (this.buyingRequest(stockName, buyingSetting.getStockCount()) == true) {  // 買入
                            System.out.println("TradingAgent\ttrade\tbuy -> 現在股價 : " + nowStockPrice + "\t上次交易價 : " + newestDealPrice + "\t時間 : " + LocalDateTime.now().toString());
                            trade_TF = true;
                        }
                        // else 買入失敗
                        continue;
                    }
                }


                // sell
                if (stock.getStockCount() > 0) {  // 判斷是否賣空
                    System.out.println("sell : " + stock.getStockCount());
                    if ((priceDelta / buyingSetting.getOfferStep()) >= 1.) {  // 價格差(正的) 比 設定的賣出間隔 大
                        if (this.sellingRequest(stockName, buyingSetting.getStockCount()) == true) {  // 賣出
                            System.out.println("TradingAgent\ttrade\tsell -> 現在股價 : " + nowStockPrice + "\t上次交易價 : " + newestDealPrice + "\t時間 : " + LocalDateTime.now().toString());
                            trade_TF = true;
                        }
                        // else 賣出失敗
                        continue;
                    }
                }

                // nothing if 價格差 比 設定的 買入/賣出 間隔 小
            }
        }
        // else 不用買賣

        return trade_TF;
    }

    /** 買賣的 request 的內容 */
    private static final String TRADING_CONTENT = "{\"symbol\":\"%s\",\"qty\":%d,\"side\":\"%s\",\"type\":\"market\",\"time_in_force\":\"gtc\"}";

    /** request失敗 的 respond (Buying power or shares is not sufficient.) */
    private static final String FORBIDDEN_CODE = "403";
    /** request失敗 的 respond (Input parameters are not recognized.) */
    private static final String UNPROCESSABLE_CODE = "422";

    /**
     * 連上Alpaca並遞送訂單
     * @param stockName 股票代號
     * @param quantity number of shares to trade. Can be fractionable for only market and day order types.
     * @param side buy / sell
     * @return 是否成功交易
     */
    private boolean request(String stockName, int quantity, String side) {
        System.out.println("start connect");
        boolean success_TF = true;
        HttpURLConnection connection = null;
        try {
            // order: json format
            String json = String.format(TRADING_CONTENT, stockName, quantity, side);
            System.out.println(json);

            // new url
            URL url = new URL(ORDER_URL);

            // new HttpURLConnection
            connection = (HttpURLConnection) url.openConnection();

            // set HTTP method
            connection.setRequestMethod("POST");

            // set HTTP header
            connection.setRequestProperty("Content-Type", "application/json");
            KeyAndID key_id = stockDataSystem.getKeyAndID();
            connection.setRequestProperty("APCA-API-KEY-ID", key_id.getKeyID());
            connection.setRequestProperty("APCA-API-SECRET-KEY", key_id.getsecretKey());

            // ask for write
            connection.setDoOutput(true);

            // write request
            try (DataOutputStream writer = new DataOutputStream(connection.getOutputStream())) {
                writer.writeBytes(json);
                writer.flush();
            } catch (IOException e) {
                success_TF = false;
                e.printStackTrace();
            }

            // read response
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
                // 沒用
                // if (response.toString().equals(FORBIDDEN_CODE)) {
                //     success_TF = false;
                // }
                // else if (response.toString().equals(UNPROCESSABLE_CODE)) {
                //     success_TF = false;
                // }
                System.out.println(response.toString());
            } catch (IOException e) {
                success_TF = false;
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            success_TF = false;
            e.printStackTrace();
        } catch (IOException e1) {
            success_TF = false;
            e1.printStackTrace();
        } finally {
            // 關閉連接
            if (connection != null) {
                connection.disconnect();
            }
        }
        return success_TF;
    }

    /**
     * 連上Alpaca並遞送購買股票的訂單
     * @param stockName 股票代號
     * @param quantity number of shares to trade. Can be fractionable for only market and day order types.
     * @return 是否成功交易
     */
    private boolean buyingRequest(String stockName, int quantity) {
        return request(stockName, quantity, "buy");
    }
    
    /**
     * 連上Alpaca並遞送販賣股票的訂單
     * @param stockName 股票代號
     * @param quantity number of shares to trade. Can be fractionable for only market and day order types.
     * @return 是否成功交易
     */
    private boolean sellingRequest(String stockName, int quantity) {
        return request(stockName, quantity, "sell");
    }
}
    

