package com.basecamp.guestbook;
import javax.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface VOFiller {
	public abstract Object fill(HttpServletRequest request);
}
