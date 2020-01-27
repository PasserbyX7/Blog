package cn.util;

import java.security.MessageDigest;

/**
 * MD5Utils
 */
public class MD5Utils {
    public static String code(String str){
        try {
            MessageDigest md=MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[]byteDigest=md.digest();
            StringBuffer sb=new StringBuffer();
            for(int i:byteDigest){
                if(i<0)
                    i+=256;
                if(i<16)
                    sb.append("0");
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}