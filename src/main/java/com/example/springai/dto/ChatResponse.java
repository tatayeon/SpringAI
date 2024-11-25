package com.example.springai.dto;

import lombok.Builder;

@Builder
public record ChatResponse(
        String answer
) {
}
