package com.basecamp.guestbook;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WriteController {

	@RequestMapping(value = "/write")
	public String home(Model model) {
		return "write";
	}
}