package poly.persistance.mapper;

import config.Mapper;
import poly.dto.ProgramDTO;
import poly.dto.UserDTO;

import java.util.List;

@Mapper("UserMapper")
public interface IUserMapper {
    int insertProgram(List<ProgramDTO> rList) throws Exception;

    int getExist(String userId) throws Exception;

    void deleteProgram(String userId) throws Exception;

    List<ProgramDTO> getPrograming(ProgramDTO pDTO) throws Exception;

    int insertProfile(UserDTO uDTO) throws Exception;

    int insertSNS(UserDTO uDTO) throws Exception;

    List<UserDTO> getSNS(UserDTO uDTO) throws Exception;
}
