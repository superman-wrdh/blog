package com.hc.util;

import java.util.Date;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;

/**
 * Created by hc on 2017/4/6.
 */
public class GenerateKey {
    public static String generateRandKey(String salt){
        String YMDHM= formatDate(new Date(),"yyyyMMddmm");
        if(isNotEmpty(salt)){
            YMDHM=YMDHM+salt;
        }
        System.out.println(YMDHM);
        return toMd5(YMDHM);
    }

    public static String formatDate(Date date,String format){
        String result="";
        SimpleDateFormat sdf=new SimpleDateFormat(format);
        if(date!=null){
            result=sdf.format(date);
        }
        return result;
    }
    public static boolean isNotEmpty(String str){
        if((str!=null)&&!"".equals(str.trim())){
            return true;
        }else{
            return false;
        }
    }

    public static String toMd5(String string){
        byte[] by=string.getBytes();
        MessageDigest digest;
        String str="";
        String str16;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(by);
            byte[] data=digest.digest();
            for (int i = 0; i < data.length; i++) {
                str16=Integer.toHexString(0XFF & data[i]);
                if (str16.length()==1) {
                    str=str+"0"+str16;
                }
                else{
                    str=str+str16;
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("code failed--superman");
            e.printStackTrace();
        }
        return str;
    }
}
