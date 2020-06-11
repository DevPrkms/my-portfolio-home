package poly.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import poly.dto.ProgramDTO;
import poly.persistance.mapper.IUserMapper;
import poly.service.IUserService;
import poly.util.CmmUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private Logger log = Logger.getLogger(this.getClass());

    @Resource(name = "UserService")
    private IUserService userService;

    @Resource(name = "UserMapper")
    private IUserMapper userMapper;

    @RequestMapping(value = "insertProgram")
    @ResponseBody
    public String insertProgram(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

        String userId = CmmUtil.nvl((String) session.getAttribute("userId"));
        String[] namearr = request.getParameterValues("name");
        String[] scorearr = request.getParameterValues("score");

        List<ProgramDTO> rList = new ArrayList<ProgramDTO>();

        for (int i = 0; i < namearr.length; i++) {
            ProgramDTO pDTO = new ProgramDTO();
            String name = namearr[i];
            String score = scorearr[i];
            pDTO.setUser_id(userId);
            pDTO.setProgram_name(name);
            pDTO.setProgram_score(score);
            rList.add(pDTO);
        }

        int cnt = userService.getExist(userId);

        if (cnt > 0) {
            userMapper.deleteProgram(userId);
            log.info("삭제완료");
            int res = userService.insertProgram(rList);
            log.info("삽입완료");
        } else {
            int res = userService.insertProgram(rList);
        }

        return "/redirect";
    }

}
