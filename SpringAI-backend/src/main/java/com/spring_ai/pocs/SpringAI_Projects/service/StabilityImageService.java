package com.spring_ai.pocs.SpringAI_Projects.service;

import org.springframework.ai.image.Image;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.stabilityai.StabilityAiImageModel;
import org.springframework.ai.stabilityai.StyleEnum;
import org.springframework.ai.stabilityai.api.StabilityAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class StabilityImageService {

    private final StabilityAiImageModel stabilityAiImageModel;

    public StabilityImageService(StabilityAiImageModel stabilityAiImageModel) {
        this.stabilityAiImageModel = stabilityAiImageModel;
    }

    public ImageResponse generateImage(String prompt,
                                       String quality,
                                       int n,
                                       int width,
                                       int height)
    {
        ImageResponse imageResponse = stabilityAiImageModel.call(
                new ImagePrompt(prompt,
                        StabilityAiImageOptions.builder()
                        .withStylePreset(StyleEnum.CINEMATIC)
                        .withN(n)
                        .withHeight(height)
                        .withWidth(width).build())
        );

        return imageResponse;
    }

}
