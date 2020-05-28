package poly.persistance.mapper;

import config.Mapper;
import poly.dto.UserDTO;

@Mapper("HomeMapper")
public interface IHomeMapper {
    UserDTO userLogin(UserDTO uDTO) throws Exception;

    int idCheck(String userId) throws Exception;

    int userReg(UserDTO uDTO) throws Exception;

    int emailvaild(String userId) throws Exception;

    int emCheck(String userEmail) throws Exception;
}
