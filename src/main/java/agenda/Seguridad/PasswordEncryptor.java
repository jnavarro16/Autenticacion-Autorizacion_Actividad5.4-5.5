package agenda.Seguridad;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static agenda.Seguridad.Constans.INIT_VECTOR;
import static agenda.Seguridad.Constans.SECRET_KEY;

public class PasswordEncryptor {
    //encriptar
    public static String encrypt(String plaintext) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

            byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    //desencriptar
    public static String decrypt(String encryptedtext) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes(StandardCharsets.UTF_8));
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

            byte[] decoded = Base64.getDecoder().decode(encryptedtext);
            byte[] decryptedBytes = cipher.doFinal(decoded);
            return new String(decryptedBytes, StandardCharsets.UTF_8);

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
