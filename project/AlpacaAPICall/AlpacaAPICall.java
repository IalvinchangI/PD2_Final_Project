package project.AlpacaAPICall;
import project.System.KeyAndID;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AlpacaAPICall {
    // 處理有沒有開市
    // 取得Key ID
    KeyAndID user = null;
    private static final String API_KEY_ID = "PKG2UYG7EYP063HG5USI";
    //private static String API_KEY_ID = user.getAPIID();
    private static final String API_SECRET_KEY = "dn8AVuR8Ux6VRZhI6IW0fP86HtMjldBhkPLFJPVa";
    //private static String API_SECRET_KEY = user.getAPIID();
    private static final String BASE_URL = "https://paper-api.alpaca.markets/v2";
    private static final String MARKET_URL = "https://data.alpaca.markets/v2/stocks/";
    private static final String ORDERS_URL = BASE_URL + "/orders";

    public static void main(String[] args) {
        try {
            // 市價買入一單位 AAPL 股票
            String orderId = submitMarketOrder("NVDA", 1, "buy");
            System.out.println("Order ID: " + orderId);
            //user.get();

            /*
             * /account 取得帳戶資訊
             * /orders 獲取所有訂單列表
             * /orders/{order_id} 獲取指定訂單的資料 {order_id} 要換成實際訂單id
             * /assets 取得目前資產
             * /assets{asset_id} 獲取指定資產 {asset_id} 要替換成實際id
             */
            checkMarketOpen();

            //getStockData("AAPL");
            // // 獲取訂單狀態
            // String orders = sendGetRequest_PaperTrading("/orders");
            // System.out.println("Orders: " + orders);

            

            // 獲取單一股票資料
            String assets = sendGetRequest_Market("AAPL/bars?timeframe=1Day");
            System.out.println("The assets: " + assets);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 此函式測試使用者輸入的 Key ID 是否正確
     * @author吳宗翰 
     * @return true 代表 Key ID 輸入正確
     * @throws Exception
     */
    public static boolean check_Key_ID() throws Exception {
        URL url = new URL(BASE_URL + "/account");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("APCA-API-KEY-ID", AlpacaAPICall.API_KEY_ID);
        connection.setRequestProperty("APCA-API-SECRET-KEY", AlpacaAPICall.API_SECRET_KEY);

        int responseCode = connection.getResponseCode();

        if(responseCode == 200) return true;
        else return false;
    }

    /**
     * 此函式測試現在是否在美股開市時間
     * @author吳宗翰
     * @return true 代表有開市
     * @throws Exception
     */
    public static boolean checkMarketOpen() throws Exception {
        URL url = new URL(BASE_URL + "/clock");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("APCA-API-KEY-ID", AlpacaAPICall.API_KEY_ID);
        connection.setRequestProperty("APCA-API-SECRET-KEY", AlpacaAPICall.API_SECRET_KEY);

        int responseCode = connection.getResponseCode();

        if(responseCode == 200) return true;
        else return false;
    }

    // /**
    //  * @author JackWu
    //  * @param endpoint
    //  * 使用endpoint url去寫請求
    //  * 需要有BASE_URL, ID. KEY並回傳response判斷是否成功(200)
    //  * @return
    //  * @throws Exception
    //  */
    // private static String sendGetRequest_PaperTrading(String endpoint) throws Exception {
    //     URL url = new URL(BASE_URL + endpoint);
    //     HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    //     connection.setRequestMethod("GET");
    //     connection.setRequestProperty("APCA-API-KEY-ID", API_KEY_ID);
    //     connection.setRequestProperty("APCA-API-SECRET-KEY", API_SECRET_KEY);

    //     int responseCode = connection.getResponseCode();
    //     System.out.println("Response Code: " + responseCode);

    //     BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    //     String inputLine;
    //     StringBuffer response = new StringBuffer();
    //     while ((inputLine = in.readLine()) != null) {
    //         response.append(inputLine);
    //     }
    //     in.close();

    //     return response.toString();
    // }

    /**
     * @author JackWu
     * 獲取當前股市資訊 AAPL, GOOGL, AMZN, MSFT, FB, TSLA為基本股票，其餘用添加的
     * 需要有Market 的 url 去寫請求，並且response回傳200為成功
     */
    private static String sendGetRequest_Market(String endpoint) throws Exception {
        URL url = new URL(MARKET_URL + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("APCA-API-KEY-ID", API_KEY_ID);
        connection.setRequestProperty("APCA-API-SECRET-KEY", API_SECRET_KEY);

        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    // private static void getStockData(String symbol) {
    //     try {
    //         LocalDate endDate = LocalDate.now();
    //         LocalDate startDate = endDate.minusDays(30);
    //         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    //         String urlString = MARKET_URL + symbol + "/bars?start=" + startDate.format(formatter) + "&end=" + endDate.format(formatter) + "&timeframe=day";
    //         System.out.println("Request URL: " + urlString);  // 打印URL用于调试
    //         URL url = new URL(urlString);
    //         HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    //         connection.setRequestMethod("GET");
    //         connection.setRequestProperty("APCA-API-KEY-ID", API_KEY_ID);
    //         connection.setRequestProperty("APCA-API-SECRET-KEY", API_SECRET_KEY);

    //         int responseCode = connection.getResponseCode();
    //         System.out.println(responseCode);
    //         if (responseCode == 200) {
    //             BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    //             JsonReader jsonReader = Json.createReader(in);
    //             JsonObject jsonResponse = jsonReader.readObject();
    //             jsonReader.close();
    //             in.close();

    //             JsonArray bars = jsonResponse.getJsonArray("bars");
    //             for (JsonObject bar : bars.getValuesAs(JsonObject.class)) {
    //                 String date = bar.getString("t");
    //                 double open = bar.getJsonNumber("o").doubleValue();
    //                 double close = bar.getJsonNumber("c").doubleValue();
    //                 double high = bar.getJsonNumber("h").doubleValue();
    //                 double low = bar.getJsonNumber("l").doubleValue();

    //                 System.out.println("Date: " + date);
    //                 System.out.println("Open: " + open);
    //                 System.out.println("Close: " + close);
    //                 System.out.println("High: " + high);
    //                 System.out.println("Low: " + low);
    //                 System.out.println("-----------------------------");
    //             }
    //         } else {
    //             System.out.println("GET request not worked, Response Code: " + responseCode);
    //         }

    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }
    // 提交市價訂單

    /**
     * 股票下單
     * @author吳宗翰
     * @param symbol
     * @param quantity
     * @param side
     * @return
     */
    private static String submitMarketOrder(String symbol, int quantity, String side) {
        try {
            URL url = new URL(ORDERS_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("APCA-API-KEY-ID", API_KEY_ID);
            connection.setRequestProperty("APCA-API-SECRET-KEY", API_SECRET_KEY);
            connection.setDoOutput(true);

            String orderBody = "{\"symbol\":\"" + symbol + "\",\"qty\":" + quantity + ",\"side\":\"" + side + "\",\"type\":\"market\",\"time_in_force\":\"gtc\"}";

            connection.getOutputStream().write(orderBody.getBytes());

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                String jsonResponse = response.toString();
                // 解析 JSON 響應，這裡可以根據需要來處理訂單 ID 的獲取
                return jsonResponse;
            } else {
                System.out.println("Failed to submit order. Response Code: " + responseCode);
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}