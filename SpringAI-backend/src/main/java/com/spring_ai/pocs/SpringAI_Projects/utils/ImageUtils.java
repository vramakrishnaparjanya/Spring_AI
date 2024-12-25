package com.spring_ai.pocs.SpringAI_Projects.utils;

import java.util.Base64;

public class ImageUtils {

    public static byte[] Base64StringToByteArray(String img){
        return Base64.getDecoder().decode(img);
    }

}
