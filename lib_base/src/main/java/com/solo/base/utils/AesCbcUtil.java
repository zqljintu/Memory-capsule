package com.solo.base.utils;

import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Description
 */
public class AesCbcUtil {

    /**
     * 加密用的Key 可以用26个字母和数字组成
     * 此处使用AES-128-CBC加密模式，key需要为16位。
     */
    public static String sKey = "4I0wrCt6pTJXjdLm";
    public static String ivParameter = "2001553752205000";


    public static String A = "IpSbiHput5MHDIlu/crWdw==";
    public static String B = "PO0+NK4vz+dY+BoH0e4XTw==";

    public static String O = "tZ9UWty+ctqNjb7fPdqehA==";
    public static String NO = "8aS4JyNH0sMkalaGHW+OMw==";
    public static String E = "+6O19pGfUCPZqhaCuKbuMQ==";

    // 加密
    public static String encrypt(String sSrc) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = sKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
            String strBase64 = new String(Base64.encode(encrypted, Base64.DEFAULT));
            return strBase64;//此处使用BASE64做转码。
        } catch (Exception ex) {
            return sSrc;
        }
    }

    // 解密
    public static String decrypt(String sSrc) {
        try {
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = Base64.decode(sSrc, Base64.DEFAULT);//先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            return sSrc;
        }
    }

}