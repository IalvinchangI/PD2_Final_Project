import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AlpacaAPICall {
    private static final String API_KEY_ID = "PKPFF4WDD5B6EZ75SU2I";
    private static final String API_SECRET_KEY = "BafM6Xa5l9IEzyWxYwTQmeNopXne0MPcurkTAyAX";
    private static final String BASE_URL = "https://paper-api.alpaca.markets/v2";
    private static final String MARKET_URL = "https://data.alpaca.markets/v2";

    public static void main(String[] args) {
        try {
            /*
             * /account 取得帳戶資訊
             * /orders 獲取所有訂單列表
             * /orders/{order_id} 獲取指定訂單的資料 {order_id} 要換成實際訂單id
             * /assets 取得目前資產
             * /assets{asset_id} 獲取指定資產 {asset_id} 要替換成實際id
             */

            // 獲取賬戶信息
            String accountInfo = sendGetRequest_PaperTrading("/account");
            System.out.println("Account Info: " + accountInfo);

            // 獲取訂單狀態
            String orders = sendGetRequest_PaperTrading("/orders");
            System.out.println("Orders: " + orders);

            // 獲取單一股票資料
            String assets = sendGetRequest_Market("/stocks/AAPL/bars?timeframe=1Day");
            System.out.println("The assets: " + assets);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author JackWu
     * @param endpoint
     * 使用endpoint url去寫請求
     * 需要有BASE_URL, ID. KEY並回傳response判斷是否成功(200)
     * @return
     * @throws Exception
     */
    private static String sendGetRequest_PaperTrading(String endpoint) throws Exception {
        URL url = new URL(BASE_URL + endpoint);
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

    /**
     * @author JackWu
     * 獲取當前股市資訊
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
}