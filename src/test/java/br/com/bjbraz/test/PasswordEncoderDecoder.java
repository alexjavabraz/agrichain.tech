package br.com.bjbraz.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderDecoder {
	
	@Test
	public void encodeText() {
		String encoded = (encoder().encode("admin"));
		
		System.out.println(encoded);
		
		assertNotNull(encoded);
		
		encoded = "$2a$11$Ul0Amx0jhOS10vIZDJIB3uCyiPDsJ.diIYX9a0ceS7mk18rFH5RTm";
		
		boolean fixedEncoded = encoder().matches("admin", encoded);
		
		assertTrue(fixedEncoded);
	}
	
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder(11);
	}

}
