package poly.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import poly.dto.ProgramDTO;
import poly.dto.UserDTO;
import poly.persistance.mapper.IUserMapper;
import poly.service.IUserService;

import javax.annotation.Resource;
import java.util.List;

@Service("UserService")
public class UserService implements IUserService {

    private Logger log = Logger.getLogger(this.getClass());

    @Resource(name = "UserMapper")
    private IUserMapper userMapper;

    @Override
    public int insertProgram(List<ProgramDTO> rList) throws Exception {
        return userMapper.insertProgram(rList);
    }

    @Override
    public int getExist(String userId) throws Exception {
        return userMapper.getExist(userId);
    }

    @Override
    public List<ProgramDTO> getPrograming(ProgramDTO pDTO) throws Exception {
        return userMapper.getPrograming(pDTO);
    }

    @Override
    public int insertProfile(UserDTO uDTO) throws Exception {
        return userMapper.insertProfile(uDTO);
    }

    @Override
    public int insertSNS(UserDTO uDTO) throws Exception {
        return userMapper.insertSNS(uDTO);
    }

    @Override
    public List<UserDTO> getSNS(UserDTO uDTO) throws Exception {
        return userMapper.getSNS(uDTO);
    }
}
