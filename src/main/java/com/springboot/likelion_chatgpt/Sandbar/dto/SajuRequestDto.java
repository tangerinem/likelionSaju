package com.springboot.likelion_chatgpt.Sandbar.dto;


public record SajuRequestDto(
        String name,
        String birth,   // "YYYY-MM-DD"
        String gender   // "남" 또는 "여"
) {}
