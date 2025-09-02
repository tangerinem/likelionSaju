package com.springboot.likelion_chatgpt.Sandbar.Controller;

import com.springboot.likelion_chatgpt.Sandbar.Service.SandbarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class SandbarController {

    private final SandbarService sandbarService;

    public SandbarController(SandbarService sandbarService) {
        this.sandbarService = sandbarService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestParam String prompt) {
        String answer = sandbarService.ask(prompt);
        return ResponseEntity.ok(answer);
    }
}