package poly.service;

import poly.dto.ProgramDTO;

import java.util.List;

public interface IUserService {
    int insertProgram(List<ProgramDTO> rList) throws Exception;

    int getExist(String userId) throws Exception;
}
