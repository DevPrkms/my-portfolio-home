package poly.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import poly.dto.ProgramDTO;
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
}
