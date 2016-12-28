package com.basecamp.guestbook;

import static org.junit.Assert.*;
import org.junit.Test;

public class MailValidationTest {
	// regex: ^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$
	
	@Test
	public void validDashTest() {
		boolean result = MailValidation.check("minkyu-kim@nhnent.com");
		assertTrue(result);
	}
	
	@Test
	public void validUnderbarTest() {
		boolean result = MailValidation.check("minkyu_kim@nhnent.com");
		assertTrue(result);
	}
	
	@Test
	public void validCommaTest() {
		boolean result = MailValidation.check("minkyu.kim@nhnent.com");
		assertTrue(result);
	}
	
	@Test
	public void length0Test() {
		boolean result = MailValidation.check("@.com");
		assertFalse(result);
	}
	
	@Test
	public void length1Test() {
		boolean result = MailValidation.check("m@n.com");
		assertTrue(result);
	}
	
	@Test
	public void startWithDashTest() {
		boolean result = MailValidation.check("-minkyukim@nhnent.com");
		assertFalse(result);
	}
	
	@Test
	public void startWithUnderbarTest() {
		boolean result = MailValidation.check("_minkyukim@nhnent.com");
		assertFalse(result);
	}
	
	@Test
	public void startWithCommaTest() {
		boolean result = MailValidation.check(".minkyukim@nhnent.com");
		assertFalse(result);
	}
	
	@Test
	public void continuousDashTest() {
		boolean result = MailValidation.check("minkyu--kim@nhnent.com");
		assertFalse(result);
	}
	
	@Test
	public void continuousUnberbarTest() {
		boolean result = MailValidation.check("minkyu__kim@nhnent.com");
		assertFalse(result);
	}
	
	@Test
	public void continuousCommaTest() {
		boolean result = MailValidation.check("minkyu..kim@nhnent.com");
		assertFalse(result);
	}
	
	@Test
	public void endWithValidDomainTest() {
		boolean result = MailValidation.check("minkyu.kim@nhnent.com");
		assertTrue(result);
		
		result = MailValidation.check("minkyu.kim@nhnent.kr");
		assertTrue(result);
	}
	
	@Test
	public void endWithLongDomainTest() {
		boolean result = MailValidation.check("minkyu.kim@nhnent.comm");
		assertFalse(result);
	}
	
	@Test
	public void endWithShortDomainTest() {
		boolean result = MailValidation.check("minkyu.kim@nhnent.c");
		assertFalse(result);
	}
	
	@Test
	public void endWithNumericDomainTest() {
		boolean result = MailValidation.check("minkyu.kim@nhnent.k4r");
		assertFalse(result);
	}
}