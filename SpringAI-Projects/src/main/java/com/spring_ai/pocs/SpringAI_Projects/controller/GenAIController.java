package com.spring_ai.pocs.SpringAI_Projects.controller;

import com.spring_ai.pocs.SpringAI_Projects.service.OllamaChatService;
import com.spring_ai.pocs.SpringAI_Projects.service.StabilityImageService;
import com.spring_ai.pocs.SpringAI_Projects.service.ZhiPuImageService;
import com.spring_ai.pocs.SpringAI_Projects.utils.ImageUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.Image;
import org.springframework.ai.image.ImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
public class GenAIController {

    @Autowired
    private final OllamaChatService ollamaChatService;

    @Autowired
    private final ZhiPuImageService zhiPuImageService;

    @Autowired
    private final StabilityImageService stabilityImageService;

    public GenAIController(OllamaChatService ollamaChatService, ZhiPuImageService zhiPuImageService, StabilityImageService stabilityImageService) {
        this.ollamaChatService = ollamaChatService;
        this.zhiPuImageService = zhiPuImageService;
        this.stabilityImageService = stabilityImageService;
    }

    @GetMapping("/ask-ai")
    public String getOllamaResponse(@RequestParam  String prompt){
        return ollamaChatService.getChatServiceResponse(prompt);
    }

    @GetMapping("/ask-ai-options")
    public String getOllamaResponseOptions(@RequestParam  String prompt){
        return ollamaChatService.getChatServiceResponseOptions(prompt);
    }

    @GetMapping("/generate-image-with-stability")
    public List<String> generateStabilityImage(HttpServletResponse response,
                                               @RequestParam String prompt,
                                               @RequestParam(defaultValue = "hd") String quality,
                                               @RequestParam(defaultValue = "1") int n,
                                               @RequestParam(defaultValue = "1024") int width,
                                               @RequestParam(defaultValue = "1024") int height) throws IOException {
        ImageResponse imageResponse = stabilityImageService.generateImage(prompt, quality, n, width, height);

//        String B64Json_Image = imageResponse.getResult().getOutput().getB64Json();
//        byte[] imageBytes = ImageUtils.Base64StringToByteArray(B64Json_Image);
//        System.out.println("Image bytes --> "+ imageBytes.toString());

//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "image/*");
//        headers.add("Content-Disposition", "inline; filename=image.png");


        // Streams to get urls from ImageResponse
        List<String> image_jsons = imageResponse.getResults().stream()
                .map(result -> result.getOutput().getB64Json())
                .toList();

        return image_jsons;
    }


    @GetMapping("/generate-image-with-zhipu")
    public String generateZhipuImage(HttpServletResponse response, @RequestParam  String prompt) throws IOException {
        ImageResponse imageResponse = zhiPuImageService.generateImage(prompt);
        String url = imageResponse.getResult().getOutput().getUrl();
        System.out.println("Zhipu url is --> "+url);
        return url;
    }

}
