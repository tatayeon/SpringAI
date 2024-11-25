package com.example.springai.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collections;

import com.example.springai.dto.ChatResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.model.Media;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeType;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

    private final OpenAiChatModel openAiChatModel;

    public ChatResponse image(MultipartFile file) throws IOException {
        String prompt = "사진을 보고 뭔지 판단해줘";

        Media media = new Media(
                MimeType.valueOf(MediaType.IMAGE_JPEG_VALUE),
                new InputStreamResource(new ByteArrayInputStream(file.getBytes()))
        );

        UserMessage userMessage = new UserMessage(prompt, Collections.singletonList(media));

        String aiResponse = openAiChatModel.call(userMessage);

        log.info("사용자 요청 이미지 이름: {}", file.getOriginalFilename());
        log.info("AI 응답: {}", aiResponse);

        return ChatResponse.builder()
                .answer(aiResponse)
                .build();
    }

}
