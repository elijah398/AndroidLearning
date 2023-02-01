package com.elijah.androidlearning.encrypttool;

import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import androidx.annotation.NonNull;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

/**
 * @FileName null.java
 * @Description TODO
 * @Author 80254912
 * @Date 2022/10/31
 */

public class KeyStoreEncryptUtils {
    private static final String TAG = "KeyStoreEncryptUtils";
    private static final String PROVIDER_ANDROID_KEY_STORE = "AndroidKeyStore";
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int AUTHENTICATION_TAG_SIZE = 128;
    private static final int IV_LENGTH = 12;
    private static final String INVALID_DATA = "";
    private KeyStore keyStore;

    private static KeyStoreEncryptUtils encryptUtilInstance;

    private KeyStoreEncryptUtils() {
        initKeyStore();
    }

    public static KeyStoreEncryptUtils getInstance() {
        synchronized (KeyStoreEncryptUtils.class) {
            if (null == encryptUtilInstance) {
                encryptUtilInstance = new KeyStoreEncryptUtils();
            }
        }
        return encryptUtilInstance;
    }

    /**
     * Load Android keystore.
     */
    private void initKeyStore() {
        try {
            keyStore = KeyStore.getInstance(PROVIDER_ANDROID_KEY_STORE);
            keyStore.load(null);
        } catch (Exception e) {
            Log.e(TAG, "initKeyStore failed, Exception is " + e);
        }
    }

    /**
     * 加密文本
     *
     * @param plainText       需要加密的字符串
     * @param alias           秘钥别名
     * @return
     */
    public String encryptString(final String plainText, final String alias) {
        if (TextUtils.isEmpty(plainText) || TextUtils.isEmpty(alias)) {
            Log.e(TAG, "encryptString failed, plainText or alias is empty");
            return INVALID_DATA;
        }

        try {
            SecretKey key = getSecretKey(alias);
            final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] encryptedByte = cipher.doFinal(plainText.getBytes());
            byte[] iv = cipher.getIV();
            byte[] mergedDate = AESCryptUtil.mergeBytes(iv, encryptedByte);

            return Base64.encodeToString(mergedDate, Base64.DEFAULT);
        } catch (Exception e) {
            Log.e(TAG, "encryptString failed, Exception is " + e);
            return INVALID_DATA;
        }
    }

    /**
     * 解密方法
     *
     * @param cipherText      需要解密的字符串
     * @param alias           key的别称
     * @return
     */
    public String decryptString(String cipherText, String alias) {
        if (TextUtils.isEmpty(cipherText) || TextUtils.isEmpty(alias)) {
            Log.e(TAG, "decryptString failed, ciphertext or alias is empty");
            return INVALID_DATA;
        }

        try {
            SecretKey key = getSecretKey(alias);
            byte[] cipherTextByte =  Base64.decode(cipherText, Base64.DEFAULT);
            final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            byte[] iv = Arrays.copyOfRange(cipherTextByte, 0, IV_LENGTH);
            final GCMParameterSpec spec = new GCMParameterSpec(AUTHENTICATION_TAG_SIZE, iv);
            cipher.init(Cipher.DECRYPT_MODE, key, spec);

            byte[] cipherData = Arrays.copyOfRange(cipherTextByte, IV_LENGTH, cipherTextByte.length);

            return new String(cipher.doFinal(cipherData));
        } catch (Exception e) {
            Log.e(TAG, "decryptString failed, Exception is " + e);
            return INVALID_DATA;
        }
    }

    /**
     * 获取 SecretKey
     *
     * @param  alias           key的别称
     * @return SecretKey       密钥
     */
    private SecretKey getSecretKey(final String alias) {
        SecretKey secretKey = loadSecretKey(alias);
        try {
            if (secretKey == null) {
                secretKey = generateSecretKey(alias);
                keyStore.setKeyEntry(alias, secretKey, null, null);
            }
        } catch (Exception e) {
            Log.e(TAG, "getSecretKey failed, Exception is " + e);
        }
        return secretKey;
    }

    /**
     * 加载 keyStore 中的密钥
     *
     * @param  alias           密钥别名
     * @return SecretKey       密钥
     */
    private SecretKey loadSecretKey(final String alias) {
        try {
            if (!keyStore.containsAlias(alias)) {
                return null;
            }
            KeyStore.SecretKeyEntry secretKeyEntry = (KeyStore.SecretKeyEntry)keyStore.getEntry(alias, null);
            SecretKey secretKey = secretKeyEntry.getSecretKey();
            return secretKey;
        } catch (Exception e) {
            Log.e(TAG, "getSecretKey failed, Exception is " + e);
            return null;
        }
    }

    /**
     * 生成密钥
     *
     * @param  alias           密钥别名
     * @return SecretKey       密钥
     */
    @NonNull
    private SecretKey generateSecretKey(final String alias) throws NoSuchAlgorithmException,
            NoSuchProviderException, InvalidAlgorithmParameterException {

        final KeyGenerator keyGenerator = KeyGenerator
                .getInstance(KeyProperties.KEY_ALGORITHM_AES, PROVIDER_ANDROID_KEY_STORE);

        keyGenerator.init(new KeyGenParameterSpec.Builder(alias,
                KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .build());

        return keyGenerator.generateKey();
    }
}