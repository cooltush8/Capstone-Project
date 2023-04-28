package com.hdfc.capstone.EMS.entity;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryptionUtil {
//	 public static String encrypt(String data, String key) throws Exception {
//	        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//	        cipher.init(Cipher.ENCRYPT_MODE, generateKey(key));
//	        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
//	    }
//
//	    public static String decrypt(String encryptedData, String key) throws Exception {
//	        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//	        cipher.init(Cipher.DECRYPT_MODE, generateKey(key));
//	        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)));
//	    }
//
//	    private static SecretKeySpec generateKey(String key) throws Exception {
//	        SecureRandom secureRandom = new SecureRandom(key.getBytes());
//	        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//	        keyGenerator.init(256, secureRandom);
//	        SecretKey secretKey = keyGenerator.generateKey();
//	        return new SecretKeySpec(secretKey.getEncoded(), "AES");
//	    }
	
	
	private static final String SECRET_KEY = "123456789";  
	 private static final String SALTVALUE = "abcdefg";  
	    
	public static String encrypt(String strToEncrypt) {  
		try   
		{  
			/* Declare a byte array. */  
			byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};  
			IvParameterSpec ivspec = new IvParameterSpec(iv);        
			
			/* Create factory for secret keys. */  
    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");  
    /* PBEKeySpec class implements KeySpec interface. */  
    KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALTVALUE.getBytes(), 65536, 256);  
    SecretKey tmp = factory.generateSecret(spec);  
    SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");  
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  
    cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);  
    
    /* Retruns encrypted value. */  
    return Base64.getEncoder()  
   		 .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));  
  }   
  
  catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e)   
  {  
    System.out.println("Error occured during encryption: " + e.toString());  
  }  
  return null;  
  }

}
