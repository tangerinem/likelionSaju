package com.springboot.likelion_chatgpt.Sandbar.dto;


import java.util.List;

public record SandbarRequestDto(
        String model,
        List<Message> messages
) {
    public record Message(String role, String content) {}
}
