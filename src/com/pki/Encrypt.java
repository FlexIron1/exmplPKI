package com.pki;

import javax.crypto.Cipher;
import java.security.PublicKey;

import static com.pki.Main.EncryptionUtil.ALGORITHM;

class Encrypt {
    /**
     * Класс зашифровки текста с помощью открытого ключа
     *
     * @param text : Текст
     * @param key  :Публичный ключ
     * @return Зашифровка текста
     * @throws java.lang.Exception
     */
    static byte[] encrypt(String text, PublicKey key) {
        byte[] cipherText = null;
        try {

            final Cipher cipher = Cipher.getInstance(ALGORITHM);
            // Зашифровка текста с помощью открытого ключа
            cipher.init(Cipher.ENCRYPT_MODE, key);
            cipherText = cipher.doFinal(text.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cipherText;
    }
}
