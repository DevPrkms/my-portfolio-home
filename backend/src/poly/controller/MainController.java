package poly.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import poly.dto.UserDTO;
import poly.service.IHomeService;
import poly.service.impl.HomeService;
import poly.util.CmmUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping(value = "userLogin", method = RequestMethod.POST)
    public String userLogin(HttpServletRequest request, HttpSession session, Model model) throws Exception {
        log.info(this.getClass().getName() + " : userLogin 호출");

        String referer = CmmUtil.nvl((String) request.getHeader("REFERER"));

        String userId = request.getParameter("userId");
        String userPassword = request.getParameter("userPassword");

        UserDTO uDTO = new UserDTO();
        uDTO.setUser_id(userId);
        uDTO.setUser_password(userPassword);

        uDTO = homeservice.userLogin(uDTO);

        if (uDTO == null) {
            model.addAttribute("msg", "입력하신 정보가 서버에 없습니다.");
            model.addAttribute("url", referer);
        } else {
            if (uDTO.getEmail_vaild().equals("N")) {
                model.addAttribute("msg", "이메일 인증이 완료되지 않았습니다.");
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
        }

        return "/redirect";
    }

    @RequestMapping(value = "user/accountsearch")
    public String accountsearch() {
        log.info(this.getClass().getName() + " : accountsearch 호출");
        return "/account/accountsearch";
    }

    @RequestMapping(value = "/idCheck", method = RequestMethod.POST)
    @ResponseBody
    public String idCheck(HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + " : idCheck 호출");
        String userId = CmmUtil.nvl((String) request.getParameter("userId"));

        log.info("userId : " + userId);
        int count = homeService.idCheck(userId);

        if (count == 0) {
            return "0";
        } else {
            return "1";
        }
    }

    @RequestMapping(value = "userReg", method = RequestMethod.POST)
    public String userReg(HttpServletRequest request, Model model) throws Exception {

        log.info(this.getClass().getName() + " : userReg 호출");

        String userId = request.getParameter("userId2");
        String userPassword = request.getParameter("userPassword2");
        String userName = request.getParameter("userName");
        String userEmail = request.getParameter("userEmail");
        String userPhone = request.getParameter("userPhone");

        UserDTO uDTO = new UserDTO();
        uDTO.setUser_id(userId);
        uDTO.setUser_password(userPassword);
        uDTO.setUser_name(userName);
        uDTO.setUser_email(userEmail);
        uDTO.setUser_phone(userPhone);

        int result = 0;

        result = homeService.userReg(uDTO);

        if (result == 1) {
            model.addAttribute("msg","이메일 인증까지 완료되어야 회원가입이 완료됩니다.");
            model.addAttribute("url", "/mail/sendMail.do?userEmail=" + userEmail + "&userId=" + userId);
        } else {
            model.addAttribute("msg", "서버 오류입니다. 잠시후 다시 시도해 주세요.");
            model.addAttribute("url", "/home.do");
        }

        return "/redirect";
    }

    @RequestMapping(value = "emailvaild", method = RequestMethod.GET)
    public String emailvaild(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        log.info(this.getClass().getName() + " : emailvaild 호출");
        String userId = CmmUtil.nvl((String) request.getParameter("userId"));

        int count = homeService.idCheck(userId);

        if (count == 0) {
            model.addAttribute("msg", "잘못된 접근입니다.");
            model.addAttribute("url", "/home.do");
        } else {
            int res = homeService.emailvaild(userId);
            if (res > 0) {
                model.addAttribute("msg", "이메일 인증이 완료되었습니다.");
                model.addAttribute("url", "/home.do");
            } else {
                model.addAttribute("msg", "서버 오류입니다. 잠시후 다시 시도해 주세요.");
                model.addAttribute("url", "/home.do");
            }
        }


        return "/redirect";
    }

}
