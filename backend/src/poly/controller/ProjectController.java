package poly.controller;

import org.apache.log4j.Logger;
import org.snu.ids.ha.index.Keyword;
import org.snu.ids.ha.index.KeywordExtractor;
import org.snu.ids.ha.index.KeywordList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import poly.dto.FileDTO;
import poly.dto.ProjectDTO;
import poly.dto.WordDTO;
import poly.persistance.mapper.IProjectMapper;
import poly.persistance.redis.IRedisMapper;
import poly.service.IProjectService;
import poly.util.CmmUtil;
import poly.util.DateUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class ProjectController {
    private Logger log = Logger.getLogger(this.getClass());

    @Resource(name = "ProjectService")
    private IProjectService projectService;

    @Resource(name = "ProjectMapper")
    private IProjectMapper projectMapper;

    final private String FILE_UPLOAD_SAVE_PATH = "/usr/local/apache-tomcat-8.5.55/webapps/ROOT/prjimg";
    final private String FILE_UPLOAD_SAVE_PATH_FILE = "/usr/local/apache-tomcat-8.5.55/webapps/ROOT/projectfile";

    @RequestMapping(value = "/project/add", method = RequestMethod.POST)
    public String add(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam(value = "prjimg") MultipartFile mf) throws Exception {
        log.info(this.getClass().getName() + " : project add 호출");

        String referer = CmmUtil.nvl((String) request.getHeader("REFERER"));
        String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s\\_]";

        // 게시글 저장 변수 선언 부분
        String pname = request.getParameter("prjname");
        String ptime = request.getParameter("prjtime");
        String pcontents = request.getParameter("prjcontents");
        pcontents = pcontents.replaceAll("\n", "<br/>");
        System.out.println("pcontents : " + pcontents);
        String regid = request.getParameter("regid");
        String rcontents = pcontents.replaceAll("<[^>]*>", " ");

        String i_name = pname.replaceAll(match, "");

        // 파일 저장 변수 선언 부분
        String originalFileName = mf.getOriginalFilename();
        Long fileSize = mf.getSize();
        log.info("파일 사이즈 : " + fileSize);
        log.info("원래 파일 명 : " + originalFileName);

        String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1, originalFileName.length()).toLowerCase();
        System.out.println("ext : " + ext);

        String saveFileName = i_name + "_" + DateUtil.getDateTime("HHmmss") + "." + ext;

        //게시글 저장 프로세스
        ProjectDTO pDTO = new ProjectDTO();
        pDTO.setProject_name(pname);
        pDTO.setProject_day(ptime);
        pDTO.setProject_contents(pcontents);
        pDTO.setR_contents(rcontents);
        pDTO.setReg_id(regid);
        if (!ext.equals("")) {
            pDTO.setImg_save_path(saveFileName);
        }

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

    @RequestMapping(value = "/project/projectdetail", method = RequestMethod.GET)
    public String projectdetail(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

        String project_seq = CmmUtil.nvl((String) request.getParameter("project_seq"));

        ProjectDTO pDTO = new ProjectDTO();
        pDTO.setProject_seq(project_seq);

        pDTO = projectService.getProject(pDTO);

        model.addAttribute("pDTO", pDTO);

        return "/mainpage/projectdetail";
    }

    @RequestMapping(value = "insertFileInfo", method = RequestMethod.POST)
    public String insertFileInfo(HttpServletResponse response, HttpServletRequest request, HttpSession session, @RequestParam(value = "projectfile") MultipartFile mf, Model model) throws Exception {
        log.info("insertFileInfo 접속");

        String projectseq = CmmUtil.nvl((String) request.getParameter("projectseq"));
        String projectname = CmmUtil.nvl((String) request.getParameter("projectname"));
        String userId = CmmUtil.nvl((String) session.getAttribute("userId"));
        String referer = CmmUtil.nvl((String) request.getHeader("REFERER"));

        String originalFileName = mf.getOriginalFilename();
        Long fileSize = mf.getSize();
        log.info("파일 사이즈 : " + fileSize);
        log.info("원래 파일 명 : " + originalFileName);

        String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1, originalFileName.length()).toLowerCase();
        System.out.println("ext : " + ext);

        String saveFileName = projectname + "_" + DateUtil.getDateTime("HHmmss") + "." + ext;

        ProjectDTO pDTO = new ProjectDTO();

        if (!ext.equals("")) {
            String saveFilePath = FILE_UPLOAD_SAVE_PATH_FILE;

            String fullFileInfo = saveFilePath + "/" + saveFileName;

            mf.transferTo(new File(fullFileInfo));

            FileDTO fDTO = new FileDTO();
            fDTO.setProject_seq(projectseq);
            fDTO.setOriginal_file_name(originalFileName);
            fDTO.setStored_file_name(fullFileInfo);
            fDTO.setFile_size(Long.toString(fileSize));
            fDTO.setReg_id(userId);

            int res = projectService.insertFileInfo(fDTO);
            if (res > 0) {
                pDTO.setProject_seq(projectseq);
                pDTO.setFile_save_path(saveFileName);
                projectMapper.updateFileInfo(pDTO);
                model.addAttribute("msg", "저장되었습니다.");
                model.addAttribute("url", "/project/projectdetail.do?project_seq=" + projectseq);
            } else {
                model.addAttribute("msg", " 서버오류");
                model.addAttribute("url", referer);
            }

        } else {
            model.addAttribute("msg", "허용되지 않은 접근입니다.");
            model.addAttribute("url", "/plist.do");
        }

        return "/redirect";
    }

    @RequestMapping(value = "/updateContents.do")
    public String updateContents(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        String projectseq = CmmUtil.nvl((String) request.getParameter("projectseq"));
        String projectcontents = CmmUtil.nvl((String) request.getParameter("mod-contents"));
        projectcontents = projectcontents.replaceAll("\n", "<br/>");

        System.out.println(projectcontents);

        ProjectDTO pDTO = new ProjectDTO();
        pDTO.setProject_seq(projectseq);
        pDTO.setProject_contents(projectcontents);

        int result = projectService.updateContents(pDTO);

        if (result > 0) {
            model.addAttribute("msg", "수정되었습니다.");
            model.addAttribute("url", "/project/projectdetail.do?project_seq=" + projectseq);
        } else {
            model.addAttribute("msg", "서버오류");
            model.addAttribute("url", "/project/projectdetail.do?project_seq=\" + projectseq");
        }

        return "/redirect";
    }

    @RequestMapping(value = "/deleteProject")
    public String deleteProject(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
        String seq = CmmUtil.nvl((String) request.getParameter("projectseq"));


        int res = projectService.deleteProject(seq);
        if (res > 0) {
            model.addAttribute("msg", "삭제 완료");
            model.addAttribute("url", "/plist.do");
        } else {
            model.addAttribute("msg", "서버오류");
            model.addAttribute("url", "/plist.do");
        }
        return "/redirect";
    }
}
