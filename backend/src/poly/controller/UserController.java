package poly.controller;

import org.apache.log4j.Logger;
import org.hsqldb.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import poly.dto.ProgramDTO;
import poly.dto.UserDTO;
import poly.persistance.mapper.IUserMapper;
import poly.service.IUserService;
import poly.util.CmmUtil;
import poly.util.DateUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private Logger log = Logger.getLogger(this.getClass());

    @Resource(name = "UserService")
    private IUserService userService;

    @Resource(name = "UserMapper")
    private IUserMapper userMapper;

    final private String FILE_UPLOAD_SAVE_PATH = "/usr/local/apache-tomcat-8.5.55/webapps/ROOT/profileimg";

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

            if (res > 0) {
                return "1";
            } else {
                return "0";
            }
        } else {
            int res = userService.insertProgram(rList);

            if (res > 0) {
                return "1";
            } else {
                return "0";
            }
        }
    }

    @RequestMapping(value = "/getPrograming")
    @ResponseBody
    public List<ProgramDTO> getPrograming(HttpServletResponse response, HttpServletRequest request) throws Exception {

        log.info(this.getClass().getName() + " : getPrograming 호출");

        String userId = CmmUtil.nvl((String) request.getParameter("userId"));

        List<ProgramDTO> rList = new ArrayList<>();

        ProgramDTO pDTO = new ProgramDTO();

        pDTO.setUser_id(userId);

        rList = userService.getPrograming(pDTO);

        System.out.println("rList : " + rList.size());

        return rList;
    }

    @RequestMapping(value = "insertProfile", method = RequestMethod.POST)
    public String insertProfile(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session, @RequestParam(value = "profileimg") MultipartFile mf) throws Exception {

        log.info(this.getClass().getName() + " : insertProfile 호출");

        String referer = CmmUtil.nvl((String) request.getHeader("REFERER"));
        String userName = CmmUtil.nvl((String) session.getAttribute("userName"));
        String userId = CmmUtil.nvl((String) session.getAttribute("userId"));

        // 파일 저장 변수 선언 부분
        String originalFileName = mf.getOriginalFilename();
        Long fileSize = mf.getSize();
        log.info("파일 사이즈 : " + fileSize);
        log.info("원래 파일 명 : " + originalFileName);

        String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1, originalFileName.length()).toLowerCase();

        String saveFileName = userName + "." + ext;

        if (ext.equals("jpeg") || ext.equals("jpg") || ext.equals("gif") || ext.equals("png")) {

            log.info("saveFileName : " + saveFileName);

            String saveFilePath = FILE_UPLOAD_SAVE_PATH;

            String fullFileInfo = saveFilePath + "/" + saveFileName;

            log.info("저장되는 파일명 : " + fullFileInfo);

            File file = new File(fullFileInfo);
            if (file.exists()) {
                file.delete();
            }

            mf.transferTo(new File(fullFileInfo));

            UserDTO uDTO = new UserDTO();

            uDTO.setUser_profile(saveFileName);
            uDTO.setUser_id(userId);

            int res = userService.insertProfile(uDTO);

            if (res > 0) {
                model.addAttribute("msg", "프로필 이미지가 변경되었습니다.");
                model.addAttribute("url", "/mypage.do");
            } else {
                model.addAttribute("msg", "서버 오류");
                model.addAttribute("url", referer);
            }
        }

        return "/redirect";
    }

    @RequestMapping(value = "/insertSNS")
    public String insertSNS(HttpServletResponse response, HttpServletRequest request, Model model, HttpSession session) throws Exception {
        String github = CmmUtil.nvl((String) request.getParameter("github"));
        String facebook = CmmUtil.nvl((String) request.getParameter("facebook"));
        String instagram = CmmUtil.nvl((String) request.getParameter("instagram"));
        String userId = CmmUtil.nvl((String) session.getAttribute("userId"));

        String referer = CmmUtil.nvl((String) request.getHeader("REFERER"));

        UserDTO uDTO = new UserDTO();
        uDTO.setUser_id(userId);
        uDTO.setUser_git(github);
        uDTO.setUser_facebook(facebook);
        uDTO.setUser_instagram(instagram);

        int res = userService.insertSNS(uDTO);

        if (res > 0) {
            model.addAttribute("msg", "저장이 완료되었습니다.");
            model.addAttribute("url", "/mypage.do");
        } else {
            model.addAttribute("msg", "서버오류");
            model.addAttribute("url", referer);
        }

        return "/redirect";
    }

    @RequestMapping(value = "getSNS")
    @ResponseBody
    public List<UserDTO> getSNS(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String userId = CmmUtil.nvl((String) request.getParameter("userId"));

        List<UserDTO> rList = new ArrayList<>();

        UserDTO uDTO = new UserDTO();

        uDTO.setUser_id(userId);

        rList = userService.getSNS(uDTO);

        System.out.println("rList : " + rList.size());

        return rList;
    }

}
