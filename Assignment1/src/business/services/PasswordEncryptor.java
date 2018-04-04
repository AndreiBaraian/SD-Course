package business.services;

import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

public class PasswordEncryptor {
	
	public static String computeHash(String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(password.getBytes("UTF-8"));
			return DatatypeConverter.printHexBinary(hash);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
