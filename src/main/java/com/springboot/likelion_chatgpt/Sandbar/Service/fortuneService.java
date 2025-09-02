package com.springboot.likelion_chatgpt.Sandbar.Service;

import com.springboot.likelion_chatgpt.Sandbar.dto.SajuRequestDto;
import com.springboot.likelion_chatgpt.Sandbar.dto.SandbarRequestDto;
import com.springboot.likelion_chatgpt.Sandbar.dto.SandbarResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Service
public class fortuneService {

    @Value("${openai.secret-key}")
    private String apiKey;

    @Value("${openai.chat-model}")
    private String chatModel;

    @Value("${fortune.prompt}")
    private String systemPrompt;
    private final RestTemplate restTemplate = new RestTemplate();

    public String analyzeFortune(SajuRequestDto saju) {
        String userPrompt = String.format(
                "이름: %s, 생일: %s, 성별: %s, 태어난 시간: %s\n위 정보를 기반으로 2025년 09월 02일 오늘의 운세를 제공해주세요. 구체적으로 작성해주세요. " +
                        "또한 마지막에 메일 발송용으로 정리하여 요약해서 답변을 제공하세요.",
                saju.name(), saju.birth(), saju.gender(), saju.time()
        );
        return ask(userPrompt);
    }


    public String ask(String userPrompt) {
        URI uri = URI.create("https://api.openai.com/v1/chat/completions");

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        SandbarRequestDto body = new SandbarRequestDto(
                chatModel,
                List.of(
                        new SandbarRequestDto.Message("system", systemPrompt),
                        new SandbarRequestDto.Message("user", userPrompt)
                )
        );

        HttpEntity<SandbarRequestDto> entity = new HttpEntity<>(body, headers);
        ResponseEntity<SandbarResponseDto> resp =
                restTemplate.exchange(uri, HttpMethod.POST, entity, SandbarResponseDto.class);

        if (!resp.getStatusCode().is2xxSuccessful()
                || resp.getBody() == null
                || resp.getBody().choices() == null
                || resp.getBody().choices().isEmpty()
                || resp.getBody().choices().get(0).message() == null) {
            throw new RuntimeException("OpenAI API 호출 실패: " + resp.getStatusCode());
        }

        return Objects.requireNonNull(resp.getBody().choices().get(0).message().content());
    }
}
