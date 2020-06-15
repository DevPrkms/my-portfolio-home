package poly.persistance.mapper;

import config.Mapper;
import poly.dto.FileDTO;
import poly.dto.ProjectDTO;
import poly.dto.WordDTO;

import java.util.List;

@Mapper("ProjectMapper")
public interface IProjectMapper {
    int insertProjectInfo(ProjectDTO pDTO) throws Exception;

    int selectProjectInfo(String pname) throws Exception;

    int insertFileInfo(FileDTO fDTO) throws Exception;

    int pnameCheck(String pname) throws Exception;

    List<ProjectDTO> getProjectInfo(ProjectDTO pDTO) throws Exception;

    List<WordDTO> getWord(String userId) throws Exception;
}
