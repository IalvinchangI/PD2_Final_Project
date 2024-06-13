package project.AlpacaAPICall;
import project.System.KeyAndID;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class sampleCode {
    // 處理有沒有開市
    // 取得Key ID
    KeyAndID user = null;
    private static final String API_KEY_ID = "PKG2UYG7EYP063HG5USI";
    //private static String API_KEY_ID = user.getAPIID();
    private static final String API_SECRET_KEY = "dn8AVuR8Ux6VRZhI6IW0fP86HtMjldBhkPLFJPVa";
    //private static String API_SECRET_KEY = user.getAPIID();
    private static final String BASE_URL = "https://paper-api.alpaca.markets/v2";
    private static final String ORDERS_URL = BASE_URL + "/orders";

    public static void main(String[] args) {
        try {
            // TODO user.get(); 在這邊抓key id
            // 市價買入一單位 AAPL 股票
            String orderId = submitMarketOrder("NVDA", 1, "buy");
            System.out.println("Order ID: " + orderId);
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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