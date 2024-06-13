package project.AlpacaAPICall;
import project.System.KeyAndID;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AlpacaAPICall {
    // 處理有沒有開市
    // 取得Key ID
    KeyAndID user = null;
    private static final String API_KEY_ID = "PKG2UYG7EYP063HG5USI";
    //private static String API_KEY_ID = user.getAPIID();
    private static final String API_SECRET_KEY = "dn8AVuR8Ux6VRZhI6IW0fP86HtMjldBhkPLFJPVa";
    //private static String API_SECRET_KEY = user.getAPIID();
    private static final String BASE_URL = "https://paper-api.alpaca.markets/v2";
    private static final String MARKET_URL = "https://data.alpaca.markets/v2/stocks/bars?symbols=";

    public static void main(String[] args) {
        try {
            //user.get();
            getStockData("AAPL");

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

            

            // // 獲取單一股票資料
            // String assets = sendGetRequest_Market("AAPL/bars?timeframe=1Day");
            // System.out.println("The assets: " + assets);

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

    String urlString2 = "https://data.alpaca.markets/v2/stocks/bars?symbols=AAPL&timeframe=1D&start=2024-05-03T00%3A00%3A00Z&end=2024-06-04T00%3A00%3A00Z&limit=1000&adjustment=raw&feed=sip&sort=asc";

    private static void getStockData(String symbol) {
        try {
            LocalDate endDate = LocalDate.now();
            LocalDate startDate = endDate.minusDays(30);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            String urlString = MARKET_URL + symbol + "&timeframe=1D&start=" + startDate.format(formatter) + "T00%3A00%3A00Z&end=" + endDate.format(formatter) + "T00%3A00%3A00Z&limit=1000&adjustment=raw&feed=sip&sort=asc";
            URL url = new URL(urlString2);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("APCA-API-KEY-ID", API_KEY_ID);
            connection.setRequestProperty("APCA-API-SECRET-KEY", API_SECRET_KEY);

            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // 使用 Jackson 解析 JSON
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonResponse = mapper.readTree(response.toString());

                JsonNode bars = jsonResponse.get("bars");
                for (JsonNode bar : bars) {
                    String date = bar.get("t").asText();
                    double open = bar.get("o").asDouble();
                    double close = bar.get("c").asDouble();
                    double high = bar.get("h").asDouble();
                    double low = bar.get("l").asDouble();

                    System.out.println("Date: " + date);
                    System.out.println("Open: " + open);
                    System.out.println("Close: " + close);
                    System.out.println("High: " + high);
                    System.out.println("Low: " + low);
                    System.out.println("-----------------------------");
                }
            } else {
                System.out.println("GET request not worked, Response Code: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
// https://data.alpaca.markets/v2/stocks/bars?symbols=AAPL&timeframe=1D&start=2024-05-03T00%3A00%3A00Z&end=2024-06-04T00%3A00%3A00Z&limit=1000&adjustment=raw&feed=sip&sort=asc