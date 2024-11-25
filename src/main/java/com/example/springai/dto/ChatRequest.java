package com.example.springai.dto;

import jakarta.validation.constraints.NotBlank;
//요청 DTO작성되는 란
public record ChatRequest(
        @NotBlank String query
){

}
