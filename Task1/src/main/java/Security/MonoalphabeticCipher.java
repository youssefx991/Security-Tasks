package Security;

import java.util.*;

public class MonoalphabeticCipher {

    // TODO: Implement this method to generate a substitution map from A-Z using the provided key
    private static Map<Character, Character> generateEncryptionMap(String key) {
        Map<Character, Character> encryptionMap = new HashMap<>();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        key = key.toUpperCase();

        // Students should complete this loop
        for (int i = 0; i < alphabet.length(); i++) {
            // encryptionMap // Hint: Map plaintext letter to cipher letter
            encryptionMap.put(alphabet.charAt(i), key.charAt(i));
        }
        return encryptionMap;
    }

    // TODO: Implement this method to reverse the encryption map (ciphertext -> plaintext)
    private static Map<Character, Character> generateDecryptionMap(String key) {
        Map<Character, Character> decryptionMap = new HashMap<>();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        key = key.toUpperCase();

        // Students should complete this loop
        for (int i = 0; i < alphabet.length(); i++) {
            // decryptionMap // Hint: Reverse mapping
            decryptionMap.put(key.charAt(i), alphabet.charAt(i));
        }
        return decryptionMap;
    }

    public static String encrypt(String plaintext, String key) {
        Map<Character, Character> encryptionMap = generateEncryptionMap(key);
        plaintext = plaintext.toUpperCase();
        StringBuilder encryptedText = new StringBuilder();

        for (char c : plaintext.toCharArray()) {
            // TODO: Use the encryption map to convert each letter
            if (Character.isLetter(c))
                encryptedText.append(encryptionMap.get(c));
            else
                encryptedText.append(c);
        }
        return encryptedText.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        Map<Character, Character> decryptionMap = generateDecryptionMap(key);
        ciphertext = ciphertext.toUpperCase();
        StringBuilder decryptedText = new StringBuilder();

        for (char c : ciphertext.toCharArray()) {
            // TODO: Use the decryption map to convert each letter
            if (Character.isLetter(c))
                decryptedText.append(decryptionMap.get(c));
            else
                decryptedText.append(c);

        }
        return decryptedText.toString();
    }

    public static String findKey(String plaintext, String ciphertext) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] keyMap = new char[26];
        Arrays.fill(keyMap, ' ');

        plaintext = plaintext.toUpperCase();
        ciphertext = ciphertext.toUpperCase();

        for (int i = 0; i < plaintext.length(); i++) {
            char plainChar = plaintext.charAt(i);
            char cipherChar = ciphertext.charAt(i); 

            if (Character.isLetter(plainChar)) {
                int index = alphabet.indexOf(plainChar);

                // TODO: Ensure each letter is mapped only once
                if (keyMap[index] == ' ')                
                    keyMap[index] = cipherChar;
                else if (keyMap[index] != cipherChar)
                {
                    System.out.println("ERROR - same char in plaintext is mapped to different char");
                    return null;
                }

            }
        }

        return new String(keyMap);
    }
}
