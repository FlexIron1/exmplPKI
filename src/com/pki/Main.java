package com.pki;

import java.io.*;
import java.security.*;
import java.util.Scanner;

import static com.pki.Decrypt.decrypt;
import static com.pki.Encrypt.encrypt;
import static com.pki.GenerateKey.areKeysPresent;
import static com.pki.GenerateKey.generateKey;

public class Main {

    public static class EncryptionUtil {
        /**
         * тип шифрования
         */

        public static final String ALGORITHM = "RSA";

        /**
         * Строка для хранения приватного ключа.
         */
        public static final String PRIVATE_KEY_FILE = "C:/keys/private.key";

        /**
         * Строка для хранения публичного ключа.
         */
        public static final String PUBLIC_KEY_FILE = "C:/keys/public.key";

       static Scanner scanner = new Scanner(System.in);

        /**
         * Тест приложения
         */
        public static void main(String[] args) {

            try {

                // Проверка,есть ли пара ключей,если нет то генерируем их
                if (!areKeysPresent()) {
                    // Метод генерирует ключи с типом шифрования RSA
                    generateKey();
                }

                final String originalText = scanner.nextLine();
                ObjectInputStream inputStream = null;

                // Зашифровка строки
                inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
                final PublicKey publicKey = (PublicKey) inputStream.readObject();
                final byte[] cipherText = encrypt(originalText, publicKey);

                // Пасшифровка строки
                inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
                final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
                final String plainText = decrypt(cipherText, privateKey);

                // Выводим на экран Оригинальный,зашифрованный и расшифрованный текст
                System.out.println("Original Text: " + originalText);
                System.out.println("Encrypted Text: " + cipherText.toString());
                System.out.println("Decrypted Text: " + plainText);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
