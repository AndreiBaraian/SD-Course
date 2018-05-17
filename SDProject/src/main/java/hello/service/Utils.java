package hello.service;

import java.security.MessageDigest;
import java.util.UUID;

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
		UUID uuid = null;
		StringBuilder token = new StringBuilder();
		for(int i=0;i<4;i++)
		{
			uuid = UUID.randomUUID();
			token.append(uuid.toString());
		}
		return token.substring(0,128);
	}

}
