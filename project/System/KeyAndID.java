package project.System;


public class KeyAndID {

    private String secretKey = null;
    private String KeyID = null;

    /**
     * 建立一個使用者的KeyAndID
     * @param secretKey : 使用者的Secret Key
     * @param KeyID : 使用者的Key ID
     */
    public KeyAndID(String secretKey, String keyID) {

        setsecretKey(secretKey);
        setKeyID(keyID);
    }

    /**
     * 取得使用者的Secret Key
     * @return 使用者的Secret Key
     */
    public String getsecretKey() {

        assert secretKey != null && secretKey.length() > 0 : "secretKey is null or \"\"";
        return secretKey;
    }

    /**
     * 取得使用者的Key ID
     * @return 使用者的Key ID
     */
    public String getKeyID() {

        assert KeyID != null && KeyID.length() > 0  : "KeyID is null or \"\"";
        return KeyID;
    }

    /**
     * 設定使用者的Secret Key
     * @param secretKey : 使用者的Secret Key
    */
    public void setsecretKey(String secretKey) {

        assert secretKey != null && secretKey.length() > 0 : "secretKey is null or \"\"";
        this.secretKey = secretKey;
    }

    /**
     * 設定使用者的Key ID
     * @param ID : 使用者的Key ID
     */
    public void setKeyID(String KeyID) {

        assert KeyID != null && KeyID.length() > 0 : "KeyID is null or \"\"";
        this.KeyID = KeyID;
    }
}