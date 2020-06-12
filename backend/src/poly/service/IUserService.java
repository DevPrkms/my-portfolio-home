package poly.service;

import poly.dto.ProgramDTO;
import poly.dto.UserDTO;

import java.util.List;

public interface IUserService {
    int insertProgram(List<ProgramDTO> rList) throws Exception;

    int getExist(String userId) throws Exception;

    List<ProgramDTO> getPrograming(ProgramDTO pDTO) throws Exception;

    int insertProfile(UserDTO uDTO) throws Exception;
}
