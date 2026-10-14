package com.codelens.web;

import com.codelens.domain.ReviewTask;
import com.codelens.repo.ReviewTaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review-tasks")
public class ReviewTaskController {
    private final ReviewTaskRepository repository;
    public ReviewTaskController(ReviewTaskRepository repository) { this.repository = repository; }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewTask> get(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
