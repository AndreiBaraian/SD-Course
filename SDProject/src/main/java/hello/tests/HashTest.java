package hello.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import hello.service.Utils;

public class HashTest {

	@Test
	public void testHash() {
		assertEquals(Utils.computeHash("admin"),"8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918");
	}
	
}
