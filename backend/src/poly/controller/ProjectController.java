package poly.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import poly.dto.FileDTO;
import poly.dto.ProjectDTO;
import poly.persistance.redis.IRedisMapper;
import poly.service.IProjectService;
import poly.util.CmmUtil;
import poly.util.DateUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProjectController {
    private Logger log = Logger.getLogger(this.getClass());

    @Resource(name = "ProjectService")
    private IProjectService projectService;

    final private String FILE_UPLOAD_SAVE_PATH = "C:/Users/data-lab1/Desktop/개인프로젝트/My Portfolio/backend/WebContent/prjimg";

    @RequestMapping(value = "/project/add", method = RequestMethod.POST)
    public String add(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam(value = "prjimg") MultipartFile mf) throws Exception {
        log.info(this.getClass().getName() + " : project add 호출");

        String referer = CmmUtil.nvl((String) request.getHeader("REFERER"));
        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s\\_]";

        // 게시글 저장 변수 선언 부분
        String pname = request.getParameter("prjname");
        String ptime = request.getParameter("prjtime");
        String pcontents = request.getParameter("prjcontents");
        String regid = request.getParameter("regid");

        String i_name = pname.replaceAll(match, "");

        // 파일 저장 변수 선언 부분
        String originalFileName = mf.getOriginalFilename();
        Long fileSize = mf.getSize();
        log.info("파일 사이즈 : " + fileSize);
        log.info("원래 파일 명 : " + originalFileName);

        String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1, originalFileName.length()).toLowerCase();

        String saveFileName = i_name + "_" + DateUtil.getDateTime("HHmmss") + "." + ext;

        //게시글 저장 프로세스
        ProjectDTO pDTO = new ProjectDTO();
        pDTO.setProject_name(pname);
        pDTO.setProject_day(ptime);
        pDTO.setProject_contents(pcontents);
        pDTO.setReg_id(regid);
        pDTO.setImg_save_path(saveFileName);

        int prjresult = projectService.insertProjectInfo(pDTO);

        if (prjresult > 0) {
            int cnt = projectService.selectProjectInfo(pname);
            log.info(cnt);

            if (ext.equals("jpeg") || ext.equals("jpg") || ext.equals("gif") || ext.equals("png")) {

                log.info("saveFileName : " + saveFileName);

                String saveFilePath = FILE_UPLOAD_SAVE_PATH;

                String fullFileInfo = saveFilePath + "/" + saveFileName;

                log.info("저장되는 파일명 : " + fullFileInfo);

                mf.transferTo(new File(fullFileInfo));

                FileDTO fDTO = new FileDTO();
                fDTO.setProject_seq(Integer.toString(cnt));
                fDTO.setOriginal_file_name(originalFileName);
                fDTO.setStored_file_name(fullFileInfo);
                fDTO.setFile_size(Long.toString(fileSize));
                fDTO.setReg_id(regid);

                int fileresult = projectService.insertFileInfo(fDTO);

                if (fileresult > 0) {
                    log.info("파일 insert 성공");
                } else {
                    log.info("파일 insert 실패");
                }
            }
            model.addAttribute("msg", "프로젝트 등록이 완료되었습니다.");
            model.addAttribute("url", "/padd.do");
        } else {
            model.addAttribute("msg", "프로젝트 등록이 실패하였습니다.");
            model.addAttribute("url", "referer");
        }

        return "/redirect";
    }


    @RequestMapping(value = "pnameCheck")
    @ResponseBody
    public String pnameCheck(HttpServletResponse response, HttpServletRequest request) throws Exception {
        log.info(this.getClass().getName() + " : pnameCheck 호출");

        String pname = request.getParameter("pname");

        int cnt = projectService.pnameCheck(pname);

        if (cnt == 0) {
            return "0";
        } else {
            return "1";
        }
    }

    @RequestMapping(value = "Project/getProjectInfo")
    @ResponseBody
    public List<ProjectDTO> getProjectInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

        log.info(this.getClass().getName() + " : getProjectInfo 호출");

        List<ProjectDTO> rList = null;

        String userid = CmmUtil.nvl((String) session.getAttribute("userId"));

        ProjectDTO pDTO = new ProjectDTO();

        pDTO.setReg_id(userid);

        rList = projectService.getProjectInfo(pDTO);

        log.info("rList : " + rList.size());

        if (rList == null) {
            rList = new ArrayList<ProjectDTO>();
        }

        log.info("rList : " + rList.size());

        log.info(this.getClass().getName() + " : getProjectInfo 종료");

        return rList;
    }
}