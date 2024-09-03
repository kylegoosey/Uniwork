import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class VigenereCipher implements Cipher {


    private String readFile(String filename) {
        String content = "";
        String contentD = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                content = content + line + "\n";
            }
            if (content.isEmpty() == false) {
                content = content.substring(0, content.length() - 1);
                contentD = content.toUpperCase();
            }
        } catch (IOException e) {
            return null;
        }
        return contentD;
    }

    
    private String adjustKeyLength(String key, int length) {
        String adjustedKey = "";
        for (int i = 0; i < length; i++) {
            int pos = i % key.length();
            char c = key.charAt(pos);
            adjustedKey = adjustedKey + c;
        }
        String adjustedKeyD = adjustedKey.toUpperCase();
        return adjustedKeyD;
    }


    public String encrypt(String messageFilename, String keyFilename) {
        return processFile(messageFilename, keyFilename, true);
    }


    public String decrypt(String messageFilename, String keyFilename) {
        return processFile(messageFilename, keyFilename, false);
    }

    private String processFile(String messageFilename, String keyFilename, boolean encrypt) {
        String message = readFile(messageFilename);
        String ogKey = readFile(keyFilename);
        String key = adjustKeyLength(ogKey, message.length());
        String processedMessage = "";

        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            int keyChar = key.charAt(i);
            if (Character.isLetter(c)) {
                int interVal;
                int processedCharV;
                if (encrypt == true) {
                    interVal = c + keyChar + 26;
                    processedCharV = (interVal) % 26 + 'A';
                } else {
                    interVal = c - keyChar + 26;
                    processedCharV = (interVal) % 26 + 'A';
                }
                char processedChar = (char) processedCharV;
                processedMessage = processedMessage +  processedChar;
            } else {
                processedMessage = processedMessage + c;
            }
        }

        return processedMessage;
    }


    public static void main(String[] args) {
        VigenereCipher cipher = new VigenereCipher();
        String encrypted = cipher.encrypt("encrypt_check.txt", "key_check.txt");
        System.out.println("Encrypted message: " + encrypted);
        String decrypted = cipher.decrypt("decrypt_check.txt", "key_check.txt");
        System.out.println("Decrypted message: " + decrypted);
    }
}