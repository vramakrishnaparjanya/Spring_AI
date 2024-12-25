package com.spring_ai.pocs.SpringAI_Projects.service;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Service;

@Service
public class OllamaChatService {

    private final OllamaChatModel ollamaChatModel;

    public OllamaChatService(OllamaChatModel ollamaChatModel) {
        this.ollamaChatModel = ollamaChatModel;
    }

    public String getChatServiceResponse(String prompt){
        return ollamaChatModel.call(prompt);
    }

    public String getChatServiceResponseOptions(String prompt){
        ChatResponse response = ollamaChatModel.call(
                new Prompt(
                        prompt,
                        OllamaOptions.builder()
                                .withModel(OllamaModel.LLAMA3_2_1B)
                                .withTemperature(0.4)
                                .build()
                ));
        return response.getResult().getOutput().getContent();
    }

}
