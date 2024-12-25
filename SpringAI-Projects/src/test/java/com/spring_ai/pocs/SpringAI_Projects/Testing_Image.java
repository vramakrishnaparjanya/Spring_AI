package com.spring_ai.pocs.SpringAI_Projects;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;

public class Testing_Image {

    private static final String B64_FILE_PATH = "D:\\jpmc\\Spring_AI\\SpringAI-Projects\\src\\test\\resources\\base64.txt";

    private static String IMG_FILE_PATH = "D:\\jpmc\\Spring_AI\\SpringAI-Projects\\src\\test\\resources\\output.png";

    public static void main(String[] args) {
        String imgstring = readB64File();
        convert_B64String_To_Image(imgstring);
    }

    public static String readB64File() {
        // Read the Base64 string from the file
        StringBuilder base64StringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(B64_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                base64StringBuilder.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return base64StringBuilder.toString();
    }


    public static void convert_B64String_To_Image(String b64_string) {
        try {
            // Decode the Base64 string to bytes
            byte[] imageBytes = Base64.getDecoder().decode(b64_string);

            // Write the bytes to a file
            try (FileOutputStream outputStream = new FileOutputStream(IMG_FILE_PATH)) {
                outputStream.write(imageBytes);
                System.out.println("Image saved to " + IMG_FILE_PATH);
            }
        } catch ( IOException e) {
            System.err.println("Error while decoding and saving the image: " + e.getMessage());
        }
    }


}
