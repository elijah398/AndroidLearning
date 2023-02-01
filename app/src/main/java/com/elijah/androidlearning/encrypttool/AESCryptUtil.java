package com.elijah.androidlearning.encrypttool;

import android.os.Build;
import android.util.Base64;
import android.util.Log;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @FileName AESCryptUtil.java
 * @Description AES加密工具
 * @Author 80254912
 * @Date 2022/10/29
 */
public class AESCryptUtil {
    private static final String TAG = "AESCryptUtil";
    private static final String TRANSFORMATION = "AES/CFB/PKCS5Padding";
    private static final String ALGORITHM = "AES";
    private static final int IV_PARAMETER_BYTE_ARRAY_LENGTH = 16;
    private static final byte[] INVALID_ENCRYPT_DATA = new byte[1];
    private static final String INVALID_DECRYPT_DATA = "";

    /**
     * AES CFB 加密
     *
     * @param key 加密 key
     * @param iv iv 偏移
     * @param value 待加密数据
     */
    public static byte[] encrypt(final SecretKey key,
                                 final IvParameterSpec iv,
                                 final byte[] value) throws GeneralSecurityException {
        final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        return cipher.doFinal(value);
    }

    /**
     * AES CFB 解密
     *
     * @param key 加密 key
     * @param iv iv 偏移
     * @param encrypted 待解密数据
     */
    public static String decrypt(final SecretKey key,
                                 final IvParameterSpec iv,
                                 final byte[] encrypted) throws GeneralSecurityException {
        final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] plainText = cipher.doFinal(encrypted);
        return new String(plainText);
    }

    /**
     * 指定 key，AES CFB 加密
     *
     * @param params 待加密数据
     * @param keyString secretKey
     * @return byte[] 加密后的数据
     */
    public static byte[] encryptDataWithKey(byte[] params, String keyString) {
        try {
            final SecretKey secretKey = new SecretKeySpec(keyString.getBytes(), ALGORITHM);
            byte[] originIv = getGUID().getBytes();
            final IvParameterSpec iv = new IvParameterSpec(originIv);
            byte[] encryptedData = encrypt(secretKey, iv, params);
            byte[] mergedDate = mergeBytes(originIv, encryptedData);
            return Base64.encode(mergedDate, Base64.DEFAULT);
        } catch (Exception e) {
            Log.e(TAG, "encryptDataWithKey filed, Exception" + e);
            return INVALID_ENCRYPT_DATA;
        }
    }

    /**
     * 指定 key，AES CFB 解密
     *
     * @param data 待解密数据
     * @param keyString secretKey
     * @return String 解密后的数据
     */
    public static String decryptDataWithKey(byte[] data, String keyString) {
        try {
            final SecretKey secretKey = new SecretKeySpec(keyString.getBytes(), ALGORITHM);
            byte[] base64DecodeData =  Base64.decode(data, Base64.DEFAULT);
            byte[] originIv = Arrays.copyOfRange(base64DecodeData, 0, IV_PARAMETER_BYTE_ARRAY_LENGTH);
            byte[] encryptedData = Arrays.copyOfRange(base64DecodeData, IV_PARAMETER_BYTE_ARRAY_LENGTH, base64DecodeData.length);
            final IvParameterSpec iv = new IvParameterSpec(originIv);
            return decrypt(secretKey, iv, encryptedData);
        } catch (Exception e) {
            Log.e(TAG, "decryptDataWithKey filed, Exception" + e);
            return INVALID_DECRYPT_DATA;
        }
    }

    /**
     * 合并两个字节数组到一个字节数组
     *
     * @param data1 字节数组1
     * @param data2 字节数组2
     * @return byte[] 合并后的字节数字
     */
    public static byte[] mergeBytes(byte[] data1, byte[] data2) {
        byte[] result = new byte[data1.length + data2.length];
        System.arraycopy(data1, 0, result, 0, data1.length);
        System.arraycopy(data2, 0, result, data1.length, data2.length);
        return result;
    }

    /**
     * 生成16位不重复的随机数，含数字+大小写
     * @return
     */
    public static String getGUID() {
        StringBuilder sb = new StringBuilder();
        Random rd = new SecureRandom();
        for (int i = 0; i < 16; i++) {
            int type = rd.nextInt(3);
            switch (type){
                case 0:
                    sb.append(rd.nextInt(10));
                    break;
                case 1:
                    sb.append((char)(rd.nextInt(25) + 65));
                    break;
                case 2:
                    sb.append((char)(rd.nextInt(25) + 97));
                    break;
                default:
                    break;
            }
        }
        return sb.toString();
    }
}

