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
import poly.util.CmmUtil;

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
    public String index(HttpSession session) {
        log.info(this.getClass().getName() + " : home 호출");
        session.invalidate();
        return "/home";
    }

    @RequestMapping(value = "main")
    public String main() {
        log.info(this.getClass().getName() + " : main 호출");
        return "/main";
    }

    @RequestMapping(value = "userLogin")
    public String userLogin(HttpServletRequest request, HttpSession session, Model model) throws Exception {
        log.info(this.getClass().getName() + " : userLogin 호출");

        String referer = CmmUtil.nvl((String)request.getHeader("REFERER"));

        String userId = request.getParameter("userName");
        String userPassword = request.getParameter("userPassword");

        UserDTO uDTO = new UserDTO();
        uDTO.setUser_id(userId);
        uDTO.setUser_password(userPassword);

        uDTO = homeservice.userLogin(uDTO);

        if (uDTO == null) {
            model.addAttribute("msg", "입력하신 정보가 서버에 없습니다.");
            model.addAttribute("url", referer);
        } else {
            model.addAttribute("msg", uDTO.getUser_name() + " 님 환영합니다.");
            model.addAttribute("url", "/main.do");
            session.setAttribute("userNo", uDTO.getUser_no());
            session.setAttribute("userId", uDTO.getUser_id());
            session.setAttribute("userName", uDTO.getUser_name());
            session.setAttribute("userEmail", uDTO.getUser_email());
            session.setAttribute("userPhone", uDTO.getUser_phone());
        }

        return "/redirect";
    }

    @RequestMapping(value = "user/accountsearch")
    public String accountsearch() {
        log.info(this.getClass().getName() + " : accountsearch 호출");
        return "/account/accountsearch";
    }

}
