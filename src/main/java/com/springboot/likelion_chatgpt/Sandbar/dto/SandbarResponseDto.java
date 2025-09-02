package com.springboot.likelion_chatgpt.Sandbar.dto;
import java.util.List;

public record SandbarResponseDto(
        String id,
        Long created,
        List<Choice> choices,
        Usage usage
) {
    public record Choice(Integer index, Message message, String finish_reason) {}
    public record Message(String role, String content) {}
    public record Usage(Integer prompt_tokens, Integer completion_tokens, Integer total_tokens) {}
}