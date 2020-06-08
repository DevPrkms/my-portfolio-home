package poly.persistance.redis.impl;

import org.apache.log4j.Logger;
import org.apache.tools.ant.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import poly.dto.ProjectDTO;
import poly.persistance.redis.IRedisMapper;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component("RedisMapper")
public class RedisMapper implements IRedisMapper {

    @Autowired
    public RedisTemplate<String, Object> redisDB;

    private Logger log = Logger.getLogger(this.getClass());

    @Override
    public boolean getExists(String key) throws Exception {

        log.info(this.getClass().getName() + " : getExists 호출");

        return redisDB.hasKey(key);
    }

    @Override
    public List<ProjectDTO> getProjectInfo(String key) throws Exception {

        log.info(this.getClass().getName() + " : getProjectInfo 호출");

        List<ProjectDTO> rList = null;

        redisDB.setKeySerializer(new StringRedisSerializer());
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(ProjectDTO.class));

        if(redisDB.hasKey(key)){
            rList = (List) redisDB.opsForList().range(key, 0, -1);
        }

        log.info(this.getClass().getName() + " : getProjectInfo 종료");

        return rList;
    }

    @Override
    public int setProjectInfo(String key, List<ProjectDTO> pList) throws Exception {

        int res = 0;

        log.info(this.getClass().getName() + " : setProjectInfo 호출");

        redisDB.setKeySerializer(new StringRedisSerializer());
        redisDB.setValueSerializer(new Jackson2JsonRedisSerializer<>(ProjectDTO.class));

        if(this.getExists(key)) {
            redisDB.delete(key);
        }

        Iterator<ProjectDTO> it = pList.iterator();

        while (it.hasNext()) {
            ProjectDTO pDTO = (ProjectDTO) it.next();

            redisDB.opsForList().rightPush(key, pDTO);

            pDTO = null;
        }

        res = 1;

        log.info(this.getClass().getName() + " : setProjectInfo 종료");

        return res;
    }

    @Override
    public boolean setTimeOutHour(String roomKey, int hours) throws Exception {
        log.info(this.getClass().getName() + " : setTimeOutHour 호출");
        return redisDB.expire(roomKey, hours, TimeUnit.HOURS);
    }
}
