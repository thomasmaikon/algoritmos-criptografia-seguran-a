package Cifras;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class AES256 implements Criptografo{

    KeyGenerator geradorDeChaves;
    SecretKey chaveSecreta;
    IvParameterSpec vetorIni;

    public AES256() {
        try {
            gerarChave();
            generateIv();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private void gerarChave() throws NoSuchAlgorithmException {
        geradorDeChaves = KeyGenerator.getInstance("AES");
        geradorDeChaves.init(256);
        chaveSecreta = geradorDeChaves.generateKey();
    }
    private void generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        vetorIni = new IvParameterSpec(iv);
    }

    @Override
    public String encriptar(String textoAberto){
        try{
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, chaveSecreta, vetorIni);
            byte[] cipherText = cipher.doFinal(textoAberto.getBytes());
            return Base64.getEncoder().encodeToString(cipherText);
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public String decriptar(String textoCifrado) {
        try{
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, chaveSecreta, vetorIni);
            byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(textoCifrado));
            return new String(plainText);
        }catch(Exception e){

        }
        return null;
    }

}
