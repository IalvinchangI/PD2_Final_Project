package project.AlpacaAPICall;
import project.System.KeyAndID;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AlpacaAPICall {
    // 取得Key ID
    KeyAndID user = null;
    private static final String API_KEY_ID = "PKG2UYG7EYP063HG5USI";
    //private static String API_KEY_ID = user.getAPIID();
    private static final String API_SECRET_KEY = "dn8AVuR8Ux6VRZhI6IW0fP86HtMjldBhkPLFJPVa";
    //private static String API_SECRET_KEY = user.getAPIID();
    private static final String BASE_URL = "https://paper-api.alpaca.markets/v2";
    private static final String MARKET_URL = "https://data.alpaca.markets/v2/stocks/bars?symbols=";
    private static final String ASSET_URL = "https://data.alpaca.markets/v2/stocks/";
    private static final String ACCOUNT_URL = "https://api.alpaca.markets/v2/account/activities";

    // 基本六張股票
    static private String[] symbols = {"AAPL", "GOOGL", "AMZN", "META", "MSFT", "TSLA"};

    // 用於查詢交易歷史紀錄
    static private String activityType = "FILL";

    public static void main(String[] args) {
        try {
            //user.get();

            String jsonResponse = getTradingActivities(activityType);
            parseAndPrintTradingActivities(jsonResponse);
            // TODO 改寫成歷史資料
            //getStockData("AAPL");
            // // 獲取訂單狀態
            // String orders = sendGetRequest_PaperTrading("/orders");
            // System.out.println("Orders: " + orders);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 此函式測試使用者輸入的 Key ID 是否正確
     * @author JackWu
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
     * @author JackWu
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

    /**
     * 提供查詢時價要 call 的函式
     * @author JackWu
     * @throws Exception
     */
    public static void stockPriceProcessing() throws Exception{
        for(String symbol : symbols){
            symbol = symbol + "/bars?timeframe=1Day";
            sendGetRequest_Market(symbol);
        }
    }

    /**
     * 提供要查詢 30 天股價時所 call 的函式
     * @author JackWu
     */
    public static void StockDataProcessing() {
        for(String symbol : symbols){
            getStockData(symbol);
        }
    }

    /**
     * @author JackWu
     * 獲取當前股市資訊 AAPL, GOOGL, AMZN, MSFT, FB, TSLA為基本股票，其餘用添加的
     * 需要有Market 的 url 去寫請求，並且response回傳200為成功
     */
    private static void sendGetRequest_Market(String endpoint) throws Exception {
        URL url = new URL(ASSET_URL + endpoint);
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

        extractStockPrice(response.toString());
    }

    /**
     * @author JackWu
     * 提取當前股票價格
     * @param jsonResponse
     * @return
     */
    private static String extractStockPrice(String jsonResponse) {
        String searchKey = "\"ap\":";
        int startIndex = jsonResponse.indexOf(searchKey) + searchKey.length();
        int endIndex = jsonResponse.indexOf(",", startIndex);
        if (endIndex == -1) {
            endIndex = jsonResponse.indexOf("}", startIndex);
        }
        return jsonResponse.substring(startIndex, endIndex).trim();
    }

    /**
     * 獲取三十天內的股票開盤、收盤、最高、最低價
     * @author JackWu
     * @param symbol
     */
    private static void getStockData(String symbol) {
        try {
            LocalDate endDate = LocalDate.now().minusDays(2);
            LocalDate startDate = endDate.minusDays(32);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            String urlString = MARKET_URL + symbol + "&timeframe=1D&start=" + startDate.format(formatter) + "T00%3A00%3A00Z&end=" + endDate.format(formatter) + "T00%3A00%3A00Z&limit=1000&adjustment=raw&feed=sip&sort=asc";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("APCA-API-KEY-ID", API_KEY_ID);
            connection.setRequestProperty("APCA-API-SECRET-KEY", API_SECRET_KEY);

            int responseCode = connection.getResponseCode();
            System.out.println(responseCode);

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;
            
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                
                String responseBody = response.toString();
                extractAndPrintStockData(responseBody, symbol);
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author JackWu
     * 切開字串預處理
     * @param responseBody
     * @param symbol
     */
    private static void extractAndPrintStockData(String responseBody, String symbol) {
        String dataArray = responseBody.substring(responseBody.indexOf("[") + 1, responseBody.lastIndexOf("]"));
        String[] dataEntries = dataArray.split("\\},\\{");

        for (String entry : dataEntries) {
            entry = entry.replace("{", "").replace("}", "").replace("\"", "");

            String[] keyValuePairs = entry.split(",");
            double open = 0, close = 0, high = 0, low = 0;
            String date = "";
            int year = 0;
            int month = 0;
            int day = 0;

            for (String pair : keyValuePairs) {
                String[] keyValue = pair.split(":");
                switch (keyValue[0]) {
                    case "o":
                        open = Double.parseDouble(keyValue[1]);
                        break;
                    case "c":
                        close = Double.parseDouble(keyValue[1]);
                        break;
                    case "h":
                        high = Double.parseDouble(keyValue[1]);
                        break;
                    case "l":
                        low = Double.parseDouble(keyValue[1]);
                        break;
                    case "t":
                        date = keyValue[1];
                        year = Integer.parseInt(date.substring(0, 4).trim());
                        month = Integer.parseInt(date.substring(5, 7).trim());
                        day = Integer.parseInt(date.substring(8, 10).trim());
                        break;
                }
            }

            System.out.println("stock name : " + symbol);
            System.out.println(year + "/" + month + "/" + day);
            System.out.println("Open: " + open);
            System.out.println("Close: " + close);
            System.out.println("High: " + high);
            System.out.println("Low: " + low);
            System.out.println("-----------------------------");
        }
    }

    private static String getTradingActivities(String activityType) throws Exception {
        URL url = new URL(ACCOUNT_URL + "?activity_type=" + activityType);
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

    private static void parseAndPrintTradingActivities(String jsonResponse) {
        String searchKey = "\"symbol\":";
        String[] activities = jsonResponse.split("\\},\\{");
        
        for (String activity : activities) {
            String symbol = extractJsonValue(activity, "\"symbol\":\"", "\"");
            String qty = extractJsonValue(activity, "\"qty\":\"", "\"");
            String side = extractJsonValue(activity, "\"side\":\"", "\"");
            String price = extractJsonValue(activity, "\"price\":\"", "\"");
            String transactionTime = extractJsonValue(activity, "\"transaction_time\":\"", "\"");

            System.out.println("Symbol: " + symbol + ", Qty: " + qty + ", Side: " + side + ", Price: " + price + ", Transaction Time: " + transactionTime);
        }
    }

    private static String extractJsonValue(String json, String keyStart, String keyEnd) {
        int startIndex = json.indexOf(keyStart) + keyStart.length();
        int endIndex = json.indexOf(keyEnd, startIndex);
        return json.substring(startIndex, endIndex);
    }
}