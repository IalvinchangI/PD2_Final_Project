package project.System;

public interface KeyAndID {
    /**
     * 取得使用者的Key
     * @return 使用者的Key
     */
    public abstract String getAPIKey();

    /**
     * 取得使用者的ID
     * @return 使用者的ID
     */
    public abstract String getAPIID();

    /**
     * 設定使用者的Key
     * @param APIKey : 使用者的Key
    */
    public abstract void setAPIKey(String APIKey);

    /**
     * 設定使用者的ID
     * @param ID : 使用者的ID
     */
    public abstract void setAPIID(String ID);
}