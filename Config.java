package com.example.aplikasiremote;

/**
 * Created by Jihad044 on 04/04/2018.
 */

public class Config {
    public static final String mServerUrl = "http://192.168.43.128:8080/mk/";

    public static final String URL_ADD_DATA = mServerUrl+"AddData.php";
    public static final String URL_GET_DATA = mServerUrl+"GetData.php";
    public static final String URL_GET_LAST_STATUS = mServerUrl+"GetLastStatus.php";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_ID= "id";
    public static final String KEY_POWER = "power";
    public static final String KEY_WAKTU = "waktu";

    //Tags that will be used to receive the post from php scripts
    public static final String TAG_JSON_ARRAY = "result";
    public static final String TAG_ID= "id";
    public static final String TAG_POWER = "power";
    public static final String TAG_WAKTU = "waktu";

}
