package com.conexus.api.config;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;

public class PasswordHasher {

    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    private static final byte[] SALT = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08};

    public static String hashPassword(String password) {
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), SALT, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    public static void main(String[] args) {
        String password = "mySecretPassword";
        String hashedPassword = PasswordHasher.hashPassword(password);
        System.out.println(hashedPassword);
    }
}
