package project.System;


public class KeyAndID {

    private String APIKey = null;
    private String APIID = null;

    /**
     * 建立一個使用者的KeyAndID
     * @param APIKey : 使用者的Secret Key
     * @param APIID : 使用者的Key ID
     */
    public KeyAndID(String APIKey, String APIID) {

        setAPIKey(APIKey);
        setAPIID(APIID);
    }

    /**
     * 取得使用者的Secret Key
     * @return 使用者的Secret Key
     */
    public String getAPIKey() {

        assert APIKey != null && APIKey.length() > 0 : "APIKey is null or \\\"\\\"";
        return APIKey;
    }

    /**
     * 取得使用者的Key ID
     * @return 使用者的Key ID
     */
    public String getAPIID() {

        assert APIID != null && APIID.length() > 0  : "APIID is null or \"\"";
        return APIID;
    }

    /**
     * 設定使用者的Secret Key
     * @param APIKey : 使用者的Secret Key
    */
    public void setAPIKey(String APIKey) {

        assert APIKey != null && APIKey.length() > 0 : "APIKey is null or \"\"";
        this.APIKey = APIKey;
    }

    /**
     * 設定使用者的Key ID
     * @param ID : 使用者的Key ID
     */
    public void setAPIID(String APIID) {

        assert APIID != null && APIID.length() > 0 : "APIID is null or \"\"";
        this.APIID = APIID;
    }
}