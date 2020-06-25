package poly.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import poly.dto.MailDTO;
import poly.service.IMailService;
import poly.util.CmmUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MailController {
    private Logger log = Logger.getLogger(this.getClass());

    @Resource(name = "MailService")
    private IMailService mailService;

    @RequestMapping(value = "mail/sendMail", method = RequestMethod.GET)
    public String sendMail(HttpServletResponse response, HttpServletRequest request, Model model) throws Exception {
        log.info(this.getClass().getName() + " : sendMail 호출");

        String toMail = CmmUtil.nvl(request.getParameter("userEmail"));
        String userId = CmmUtil.nvl(request.getParameter("userId"));
        String contents = "아래 링크를 누르시면 가입이 완료됩니다. \n http://52.78.99.99:8080/emailvaild.do?userId=" + userId;

        MailDTO pDTO = new MailDTO();

        pDTO.setToMail(toMail);
        pDTO.setContents(contents);

        int res = mailService.sendEmail(pDTO);

        if(res==1){
            log.info(this.getClass().getName() + " : sendMail 완료");
            model.addAttribute("msg", "이메일이 전송되었습니다.");
            model.addAttribute("url", "/home.do");
        } else {
            log.info(this.getClass().getName() + " : sendMail 실패");
            model.addAttribute("msg", "서버 오류");
            model.addAttribute("url", "/home.do");
        }
        return "/redirect";
    }
}
