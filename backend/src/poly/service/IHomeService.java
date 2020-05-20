package poly.service;

import poly.dto.UserDTO;

public interface IHomeService {

    UserDTO userLogin(UserDTO uDTO) throws Exception;

}
