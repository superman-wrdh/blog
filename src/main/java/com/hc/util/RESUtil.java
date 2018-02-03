package com.hc.util;

import java.util.ResourceBundle;

/**
 * Created by hc on 2017/3/9.
 */
public class RESUtil {
    protected static final ResourceBundle RES = ResourceBundle.getBundle("service_messages");
    public static String getString(String key){
        return RES.getString(key);
    }
}
