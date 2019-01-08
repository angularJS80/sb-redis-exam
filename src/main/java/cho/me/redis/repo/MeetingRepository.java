package cho.me.redis.repo;

import cho.me.redis.dto.Meeting;
import org.springframework.data.repository.CrudRepository;

public interface MeetingRepository extends CrudRepository<Meeting,String >{
}
