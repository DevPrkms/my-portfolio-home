package poly.controller;

import org.apache.log4j.Logger;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import poly.dto.WordDTO;
import poly.service.IProjectService;
import poly.util.CmmUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RController {

    private Logger log = Logger.getLogger(this.getClass());

    @Resource
    private IProjectService projectService;

    @RequestMapping(value = "rConnect")
    @ResponseBody
    public List<WordDTO> rConnect(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("rConnect 호출");
        RConnection c = new RConnection();
        log.info("R연결 완료");
        String userId = CmmUtil.nvl((String) request.getParameter("userId"));
        log.info("userId : " + userId);
        log.info("userId request로 받음");
        WordDTO wDTO = new WordDTO();
        List<WordDTO> sList = new ArrayList<>();

        List<WordDTO> rList = new ArrayList<>();

        rList = projectService.getWord(userId);
        log.info("컨텐츠 가져옴");
        log.info(rList.get(0).getR_contents());
        if (rList.size() == 0) {
            log.info("컨텐츠 없음");
            sList = new ArrayList<>();
        } else {
            log.info("R분석 시작");
            String[] sArr = new String[rList.size()];
            for (int i = 0; i < rList.size(); i++) {
                sArr[i] = rList.get(i).getR_contents();
                log.info(sArr[i]);
            }
            c.assign("sArr", sArr);
            c.eval("m_df <- sArr %>% SimplePos09 %>% melt %>% as_tibble %>% select(3,1)");
            c.eval("m_df <- m_df %>% mutate(noun=str_match(value, '([A-Z|a-z|0-9|가-힣]+)/N')[,2]) %>% na.omit");
            c.eval("m_df <- m_df %>% count(noun, sort=TRUE)");
            c.eval("m_df <- filter(m_df,nchar(noun)>=2)");
//            c.eval("m_df <- filter(m_df,n>=2");
            REXP x = c.eval("m_df$noun");
            REXP y = c.eval("m_df$n");
            String[] noun = x.asStrings();
            String[] count = y.asStrings();
            for (int i = 0; i < noun.length; i++) {
                wDTO = new WordDTO();
                wDTO.setWord(noun[i]);
                wDTO.setWordCount(count[i]);
                log.info("Word : " + wDTO.getWord() + ", Count : " + wDTO.getWordCount());
                sList.add(wDTO);
                wDTO = null;
            }
        }
        c.close();
        log.info("R끝");
        return sList;
    }
}
