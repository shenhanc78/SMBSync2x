package com.sentaroh.android.SMBSync2;

import android.util.Base64;
import java.security.MessageDigest;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptUtil {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final int IV_SIZE = 16;
    private static final int KEY_SIZE = 16; // 128 bit

    public static class CipherParms {
        public Cipher encryptCipher;
        public Cipher decryptCipher;
        public SecretKeySpec keySpec;
    }

    private static SecretKeySpec deriveKey(String password) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] keyBytes = new byte[KEY_SIZE];
        System.arraycopy(digest.digest(password.getBytes("UTF-8")), 0, keyBytes, 0, KEY_SIZE);
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }

    public static CipherParms initEncryptEnv(String password) {
        try {
            CipherParms cp = new CipherParms();
            cp.keySpec = deriveKey(password);
            cp.encryptCipher = Cipher.getInstance(TRANSFORMATION);
            return cp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static CipherParms initDecryptEnv(String password) {
        try {
            CipherParms cp = new CipherParms();
            cp.keySpec = deriveKey(password);
            cp.decryptCipher = Cipher.getInstance(TRANSFORMATION);
            return cp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] encrypt(String data, CipherParms cp) {
        try {
            byte[] iv = new byte[IV_SIZE];
            new SecureRandom().nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cp.encryptCipher.init(Cipher.ENCRYPT_MODE, cp.keySpec, ivSpec);
            byte[] encrypted = cp.encryptCipher.doFinal(data.getBytes("UTF-8"));
            
            byte[] combined = new byte[IV_SIZE + encrypted.length];
            System.arraycopy(iv, 0, combined, 0, IV_SIZE);
            System.arraycopy(encrypted, 0, combined, IV_SIZE, encrypted.length);
            return combined;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decrypt(byte[] data, CipherParms cp) {
        try {
            byte[] iv = new byte[IV_SIZE];
            System.arraycopy(data, 0, iv, 0, IV_SIZE);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            
            byte[] encrypted = new byte[data.length - IV_SIZE];
            System.arraycopy(data, IV_SIZE, encrypted, 0, encrypted.length);
            
            cp.decryptCipher.init(Cipher.DECRYPT_MODE, cp.keySpec, ivSpec);
            byte[] decrypted = cp.decryptCipher.doFinal(encrypted);
            return new String(decrypted, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String makeSHA1Hash(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] hash = digest.digest(data.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
