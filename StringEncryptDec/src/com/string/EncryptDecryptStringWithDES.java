package com.string;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;



public class EncryptDecryptStringWithDES {

	private static Cipher ecipher;
	private static Cipher dcipher;
	
	private static  SecretKey key;
	
	
	public static void main(String[] args) {

		
			try {
				
				// generate secret key using DES algorithm
				//Data encryption standard
				key=KeyGenerator.getInstance("AES").generateKey();
				
				ecipher=Cipher.getInstance("AES");
				dcipher=Cipher.getInstance("AES");
				
				 // initialize the ciphers with the given key
				
				ecipher.init(Cipher.ENCRYPT_MODE, key);
				dcipher.init(Cipher.DECRYPT_MODE, key);
				
				String enc=encrypt("madhu");
				System.out.println(enc);
				String dec=decrypt(enc);
				System.out.println(dec);
				
			} 
			catch (NoSuchAlgorithmException  | NoSuchPaddingException | InvalidKeyException e) {
	            System.out.println("No Such Algorithm:" + e.getMessage());
	            return;
	        }
	     
	}
			
			public static String encrypt(String str) {
				
				try {
					// encode the string into a sequence of bytes using the named charset
					 
				    // storing the result into a new byte array. 
					byte[] utf8=str.getBytes("UTF8");
					byte[] enc=ecipher.doFinal(utf8);
					// encode to base64
					enc=BASE64EncoderStream.encode(enc);
					
					return new String(enc);
				} catch (Exception e) {
					e.printStackTrace(); 
				}
				return null;
			}

	public static String decrypt(String str) {
		try {
			
			// decode with base64 to get bytes
			byte[] dec=BASE64DecoderStream.decode(str.getBytes());
			byte[] utf8=dcipher.doFinal(dec);
			
			// create new string based on the specified charset
			return new String(utf8,"UTF8");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
