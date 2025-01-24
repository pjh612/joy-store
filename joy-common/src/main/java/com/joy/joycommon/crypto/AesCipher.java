package com.joy.joycommon.crypto;

import org.springframework.security.crypto.codec.Hex;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AesCipher {
    private final String key;

    public AesCipher(String key) {
        this.key = key;
    }

    public String encrypt(String plainText) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(key.substring(0, 16).getBytes());

        Cipher instance = null;
        try {
            instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] bytes = instance.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

            return new String(Hex.encode(bytes));
        } catch (NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException |
                 InvalidAlgorithmParameterException | InvalidKeyException | NoSuchPaddingException e) {
            throw new CryptoException(e);
        }
    }

    public String decrypt(String encodeText) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(key.substring(0, 16).getBytes());

        Cipher instance = null;
        try {
            instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] decode = Hex.decode(encodeText);
            return new String(instance.doFinal(decode), StandardCharsets.UTF_8);
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException |
                 InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new CryptoException(e);
        }
    }
}
