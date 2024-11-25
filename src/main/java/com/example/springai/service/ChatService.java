package com.example.springai.service;

import com.example.springai.dto.ChatRequest;
import com.example.springai.dto.ChatResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final OpenAiChatModel openAiChatModel;

    //여기서 PromptTemplate 이게 핵심 이걸로 이제 프롬프트 엔지니어를 해야한다.
    public ChatResponse chat(ChatRequest chatRequest) {
        PromptTemplate promptTemplate = new PromptTemplate(
                """
                당신은 감정분석 전문가야
                사용자의 감정을 분석해줘
                사용자: {query}
                """
        );

        Prompt prompt = promptTemplate.create(Map.of("query", chatRequest.query()));

        //호출을 해서 이제 정해진 형식으로 넘겨주면 바로 된다~
        String aiResponse = openAiChatModel.call(prompt).getResult().getOutput().getContent();

        log.info("사용자 요청: {}", chatRequest.query());
        log.info("AI 응답: {}", aiResponse);

        return ChatResponse.builder()
                .answer(aiResponse)
                .build();
    }

}