package com.basecamp.guestbook;

import java.util.regex.Pattern;

public class MailValidation {
	private static final String regex = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
	public static boolean check(String email) {
		return Pattern.matches(regex, email);
	}
}