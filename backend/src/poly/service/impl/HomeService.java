package poly.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import poly.dto.UserDTO;
import poly.persistance.mapper.IHomeMapper;
import poly.service.IHomeService;

import javax.annotation.Resource;

@Service("HomeService")
public class HomeService implements IHomeService {

    private Logger log = Logger.getLogger(this.getClass());

    @Resource(name="HomeMapper")
    private IHomeMapper homeMapper;

    @Override
    public UserDTO userLogin(UserDTO uDTO) throws Exception {
        log.info(this.getClass().getName() + " : HS login 호출");
        log.info("uDTO : " + uDTO.getUser_id());
        return homeMapper.userLogin(uDTO);
    }
}
