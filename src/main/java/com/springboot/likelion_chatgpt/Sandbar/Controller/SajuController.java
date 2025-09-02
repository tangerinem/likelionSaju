package com.springboot.likelion_chatgpt.Sandbar.Controller;

import com.springboot.likelion_chatgpt.Sandbar.Service.SandbarService;
import com.springboot.likelion_chatgpt.Sandbar.Service.fortuneService;
import com.springboot.likelion_chatgpt.Sandbar.dto.SajuRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saju")
public class SajuController {

    private final SandbarService sandbarService;
    private final fortuneService fortuneService;

    public SajuController(SandbarService sandbarService, fortuneService fortuneService) {
        this.sandbarService = sandbarService;
        this.fortuneService = fortuneService;
    }


    @PostMapping("/saju-analyze")
    public ResponseEntity<String> analyze(@RequestBody SajuRequestDto saju) {
        String result = sandbarService.analyzeSaju(saju);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/fortune-analyze")
    public ResponseEntity<String> fortuneAnalyze(@RequestBody SajuRequestDto saju) {
        String result = fortuneService.analyzeFortune(saju);
        System.out.println(result);
        return ResponseEntity.ok(result);
    }
}
