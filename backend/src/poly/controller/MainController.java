package poly.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import poly.dto.UserDTO;
import poly.service.IHomeService;
import poly.service.impl.HomeService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private HomeService homeService;

    @Resource(name = "HomeService")
    private IHomeService homeservice;

    @RequestMapping(value = "home")
    public String index() {
        log.info(this.getClass().getName() + " : home 호출");
        return "/home";
    }

    @RequestMapping(value = "main")
    public String main() {
        log.info(this.getClass().getName() + " : main 호출");
        return "/main";
    }

    @RequestMapping(value = "userLogin")
    @ResponseBody
    public String userLogin(HttpServletRequest request, HttpSession session, Model model) throws Exception {
        log.info(this.getClass().getName() + " : userLogin 호출");

        String referer = request.getHeader("REFERER");

        String userId = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");

        UserDTO uDTO = new UserDTO();
        uDTO.setUser_id(userId);
        uDTO.setUser_password(userPassword);

        uDTO = homeservice.userLogin(uDTO);
        String userNo = uDTO.getUser_no();
        userId = uDTO.getUser_id();
        String userName = uDTO.getUser_name();
        String userEmail = uDTO.getUser_email();
        String userPhone = uDTO.getUser_phone();

        if (uDTO == null) {
            return "0";
        } else {
            session.setAttribute("userNo", userNo);
            session.setAttribute("userId", userId);
            session.setAttribute("userName", userName);
            session.setAttribute("userEmail", userEmail);
            session.setAttribute("userPhone", userPhone);
            return "1";
        }
    }

}
