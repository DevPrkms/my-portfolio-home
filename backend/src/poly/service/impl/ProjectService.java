package poly.service.impl;

import org.apache.log4j.Logger;
import org.apache.tools.ant.Project;
import org.springframework.stereotype.Service;
import poly.dto.FileDTO;
import poly.dto.ProjectDTO;
import poly.persistance.mapper.IProjectMapper;
import poly.persistance.redis.IRedisMapper;
import poly.service.IProjectService;
import poly.util.DateUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("ProjectService")
public class ProjectService implements IProjectService {

    private Logger log = Logger.getLogger(this.getClass());

    @Resource(name = "ProjectMapper")
    private IProjectMapper projectMapper;

    @Resource(name = "RedisMapper")
    private IRedisMapper redisMapper;

    @Override
    public int insertProjectInfo(ProjectDTO pDTO) throws Exception {
        return projectMapper.insertProjectInfo(pDTO);
    }

    @Override
    public int selectProjectInfo(String pname) throws Exception {
        return projectMapper.selectProjectInfo(pname);
    }

    @Override
    public int insertFileInfo(FileDTO fDTO) throws Exception {
        return projectMapper.insertFileInfo(fDTO);
    }

    @Override
    public int pnameCheck(String pname) throws Exception {
        return projectMapper.pnameCheck(pname);
    }

    @Override
    public List<ProjectDTO> getProjectInfo(ProjectDTO pDTO) throws Exception {

        log.info(this.getClass().getName() + " : getProjectInfo 호출");

        List<ProjectDTO> rList = null;

        System.out.println("pDTO : " + pDTO.getReg_id());

        String key = "PROJECT_INFO_" + DateUtil.getDateTime("yyyyMMdd");

        if (redisMapper.getExists(key)) {
            rList = redisMapper.getProjectInfo(key);

            if (rList == null) {
                rList = new ArrayList<ProjectDTO>();
            }

            redisMapper.setTimeOutMinute(key, 1);
        } else {

            rList = projectMapper.getProjectInfo(pDTO);

            if (rList == null) {
                rList = new ArrayList<ProjectDTO>();
            }

            if (rList.size() > 0) {
                redisMapper.setProjectInfo(key, rList);

                redisMapper.setTimeOutMinute(key, 1);
            }
        }

        log.info(this.getClass().getName() + " : getProjectInfo 종료");

        return rList;
    }
}
