package com.basecamp.guestbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GuestBookController {
	@Autowired
	MessageDAO mdao;
	
	@RequestMapping(value = "/")
	public String execute(Model model) {
		try {
			model.addAttribute("messages", mdao.selectAll());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "guestbook";
	}
}