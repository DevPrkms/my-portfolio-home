package poly.persistance.mapper;

import config.Mapper;
import poly.dto.UserDTO;

@Mapper("HomeMapper")
public interface IHomeMapper {
    UserDTO userLogin(UserDTO uDTO) throws Exception;
}