package cho.me.redis;

import cho.me.redis.dto.Meeting;
import cho.me.redis.repo.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DefaultDataPopulator implements ApplicationRunner {

    @Autowired
    MeetingRepository meetingRepository;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valusOps;


    @Override
     public void run(ApplicationArguments args) throws Exception {
        Meeting meeting = new Meeting();
        meetingRepository.save(meeting);

        Long count = 0L;
        try {
            valusOps.increment("spring:redis:visitcount", 1);
            count = Long.valueOf(valusOps.get("spring:redis:visitcount"));
            System.out.println("count : "+count);


            String key = "key:springboot";

            valusOps.getOperations().opsForValue().set(key, "Hello");
            String value = (String)valusOps.getOperations().opsForValue().get(key);
            System.out.println("value : "+value);


        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
