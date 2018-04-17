package hello.service;

import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.xml.bind.DatatypeConverter;

public class Utils {

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
	
	public static String generateToken() {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[128];
		random.nextBytes(bytes);
		return bytes.toString();
	}
	
}
