package com.codelens.webhook;
import com.codelens.service.ReviewTaskService; import jakarta.servlet.http.HttpServletRequest; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import java.io.*;
@RestController @RequestMapping("/api/webhooks/gitlab") public class GitLabWebhookController { private final ReviewTaskService service; public GitLabWebhookController(ReviewTaskService s){this.service=s;}
 @PostMapping public ResponseEntity<Void> receive(@RequestHeader(value="X-Gitlab-Token",required=false) String token,@RequestHeader(value="X-Gitlab-Event",defaultValue="Merge Request Hook") String event,@RequestBody String body){service.accept(token,event,body); return ResponseEntity.accepted().build();} }
