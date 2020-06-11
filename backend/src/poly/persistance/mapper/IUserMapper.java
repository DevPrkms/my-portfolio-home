package poly.persistance.mapper;

import config.Mapper;
import poly.dto.ProgramDTO;

import java.util.List;

@Mapper("UserMapper")
public interface IUserMapper {
    int insertProgram(List<ProgramDTO> rList) throws Exception;

    int getExist(String userId) throws Exception;

    void deleteProgram(String userId) throws Exception;
}
