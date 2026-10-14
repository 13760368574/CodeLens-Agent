package com.codelens.service;
import com.codelens.domain.ReviewTask; import com.codelens.repo.ReviewTaskRepository; import org.springframework.beans.factory.annotation.Value; import org.springframework.data.redis.core.StringRedisTemplate; import org.springframework.scheduling.annotation.Scheduled; import org.springframework.stereotype.Component; import java.time.Instant;
@Component public class ReviewWorker { final StringRedisTemplate redis; final ReviewTaskRepository tasks; @Value("${codelens.queue-key}") String queue; public ReviewWorker(StringRedisTemplate r,ReviewTaskRepository t){redis=r;tasks=t;}
 @Scheduled(fixedDelay=1000) public void consume(){String id=redis.opsForList().leftPop(queue); if(id==null)return; tasks.findById(Long.valueOf(id)).ifPresent(this::run);}
 void run(ReviewTask t){try{t.status=ReviewTask.Status.FETCHING_DIFF;tasks.save(t); // GitLab client/Agent adapter is intentionally isolated for replacement with credentials.
   t.status=ReviewTask.Status.REVIEWING;tasks.save(t); t.status=ReviewTask.Status.POST_PROCESSING;tasks.save(t); t.status=ReviewTask.Status.PUBLISHING;tasks.save(t); t.status=ReviewTask.Status.SUCCEEDED;t.finishedAt=Instant.now();tasks.save(t);
 }catch(Exception e){t.attempt++;t.errorMessage=e.getMessage();t.status=t.attempt<3?ReviewTask.Status.RETRY_WAIT:ReviewTask.Status.FAILED;tasks.save(t);}}
}
