package poly.service;

import poly.dto.UserDTO;

public interface IHomeService {

    UserDTO userLogin(UserDTO uDTO) throws Exception;

    int idCheck(String userId) throws Exception;

    int userReg(UserDTO uDTO) throws Exception;
}
