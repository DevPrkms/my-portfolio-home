package poly.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping(value = "home")
	public String index() {
		log.info(this.getClass().getName() + " : home 호출");
		return "/home";
	}

	@RequestMapping(value = "userLogin")
	public String overlap(HttpServletRequest request, HttpSession session, Model model) throws Exception {
		log.info(this.getClass().getName() + " : userLogin 호출");
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");

		return "/redirect";
	}

}
