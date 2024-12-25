package com.spring_ai.pocs.SpringAI_Projects.service;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.zhipuai.ZhiPuAiImageModel;
import org.springframework.stereotype.Service;

@Service
public class ZhiPuImageService {

    private final ZhiPuAiImageModel zhiPuAiImageModel;

    public ZhiPuImageService(ZhiPuAiImageModel zhiPuAiImageModel) {
        this.zhiPuAiImageModel = zhiPuAiImageModel;
    }

    public ImageResponse generateImage(String prompt)
    {
        ImageResponse imageResponse = zhiPuAiImageModel.call(new ImagePrompt("show india map"));
        return imageResponse;
    }

}
