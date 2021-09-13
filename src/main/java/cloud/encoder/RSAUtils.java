package cloud.encoder;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.IOException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtils {

    //生成秘钥对
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }

    //获取公钥(Base64编码)
    static String getPublicKey(KeyPair keyPair) {
        PublicKey publicKey = keyPair.getPublic();
        byte[] bytes = publicKey.getEncoded();
        return new BASE64Encoder().encode((bytes));
    }

    //获取私钥(Base64编码)
    static String getPrivateKey(KeyPair keyPair) {
        PrivateKey privateKey = keyPair.getPrivate();
        byte[] bytes = privateKey.getEncoded();
        return new BASE64Encoder().encode((bytes));
    }

    //将Base64编码后的公钥转换成PublicKey对象
    static PublicKey string2PublicKey(String pubStr) throws Exception {
        byte[] keyBytes = new BASE64Decoder().decodeBuffer((pubStr));
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    //将Base64编码后的私钥转换成PrivateKey对象
    static PrivateKey string2PrivateKey(String priStr) throws Exception {
        byte[] keyBytes = new BASE64Decoder().decodeBuffer((priStr));
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    //公钥加密
    public static String publicEncrypt(String content, String publicKeyStr) throws Exception {
        PublicKey publicKey = RSAUtils.string2PublicKey(publicKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bytes = cipher.doFinal(content.getBytes());
        return new BASE64Encoder().encode(bytes);
    }

    //私钥解密
    public static byte[] privateDecrypt(String content, String privateKeyStr) throws Exception {
        PrivateKey privateKey = RSAUtils.string2PrivateKey(privateKeyStr);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytes = cipher.doFinal(new BASE64Decoder().decodeBuffer(content));
        return bytes;
    }


    public static void main(String[] args) {
        try {
            //===============生成公钥和私钥，公钥传给客户端，私钥服务端保留==================
            KeyPair keyPair = RSAUtils.getKeyPair();
            String publicKeyStr = RSAUtils.getPublicKey(keyPair);
            String privateKeyStr = RSAUtils.getPrivateKey(keyPair);
            System.out.println("RSA公钥Base64编码:" + publicKeyStr);
            System.out.println("RSA私钥Base64编码:" + privateKeyStr);

            //=================开始加密=================
            String message = "panda";
            //用公钥加密
            String publicEncrypt = RSAUtils.publicEncrypt(message, publicKeyStr);
            System.out.println("公钥加密并Base64编码的结果：" + publicEncrypt);


            //===================开始解密================
            //用私钥解密
            byte[] privateDecrypt = RSAUtils.privateDecrypt(publicEncrypt, privateKeyStr);
            //解密后的明文
            System.out.println("解密后的明文: " + new String(privateDecrypt));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

