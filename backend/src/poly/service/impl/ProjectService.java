package poly.service.impl;

import org.springframework.stereotype.Service;
import poly.dto.FileDTO;
import poly.dto.ProjectDTO;
import poly.persistance.mapper.IProjectMapper;
import poly.service.IProjectService;

import javax.annotation.Resource;

@Service("ProjectService")
public class ProjectService implements IProjectService {

    @Resource(name="ProjectMapper")
    private IProjectMapper projectMapper;

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
}
