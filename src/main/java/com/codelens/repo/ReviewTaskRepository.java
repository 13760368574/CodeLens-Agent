package com.codelens.repo; import com.codelens.domain.ReviewTask; import org.springframework.data.jpa.repository.*; import java.util.*;
public interface ReviewTaskRepository extends JpaRepository<ReviewTask,Long>{Optional<ReviewTask> findByIdempotencyKey(String key); List<ReviewTask> findTop20ByStatusOrderByQueuedAtAsc(ReviewTask.Status status);}
