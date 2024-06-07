import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AlpacaAPICall {
    private static final String API_KEY_ID = "PK9EA04EHDR5SGVKKE5D";
    private static final String API_SECRET_KEY = "URql6xA4jg9c2RbYZeGA8JGA8bYYqRe5E57DhtfI";
    private static final String BASE_URL = "https://paper-api.alpaca.markets/v2";

    public static void main(String[] args) {
        try {
            // // 獲取賬戶信息
            // String accountInfo = sendGetRequest("/account");
            // System.out.println("Account Info: " + accountInfo);

            // 獲取訂單狀態
            String orders = sendGetRequest("/orders");
            System.out.println("Orders: " + orders);

            // // 獲取資產資料
            // String assets = sendGetRequest("/assets");
            // System.out.println("The assets: " + assets);

            /*
             * /account 取得帳戶資訊
             * /orders 獲取所有訂單列表
             * /orders/{order_id} 獲取指定訂單的資料 {order_id} 要換成實際訂單id
             * /assets 取得目前資產
             * /assets{asset_id} 獲取指定資產 {asset_id} 要替換成實際id
             */

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String sendGetRequest(String endpoint) throws Exception {
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
}