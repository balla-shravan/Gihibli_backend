package com.example.ghibiliapi.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.ghibiliapi.Client.StabilityAiClient;
import com.example.ghibiliapi.DTO.TextToImageRequest;

@Service
public class GhibliArtService {
    private final StabilityAiClient stabilityAiClient;

    private final String apiKey;

    public GhibliArtService(StabilityAiClient stabilityAiClient,
                            @Value("${stability.api.key}") String apiKey) {
        this.stabilityAiClient = stabilityAiClient;
        this.apiKey = apiKey;
    }

    public byte[] createGhibliArt(MultipartFile image, String prompt) {
        String finalPrompt = prompt + ", in the beautiful, detailed anime style of Studio Ghibli.";
        String engineId = "stable-diffusion-xl-1024-v1-0";
        String stylePreset = "anime";
        return stabilityAiClient.generateImagetoImage(
                "Bearer " + apiKey,
                engineId,
                image,
                finalPrompt,
                stylePreset
        );
    }

    public byte[] createGhibliArtFromText(String prompt, String style) {
        String finalPrompt = prompt + ", in the beautiful, detailed anime style of Studio Ghibli.";
        String engineId = "stable-diffusion-xl-1024-v1-0";
        String stylePreset = style.equals("general") ? "anime" : style.replace("_", "-");
        TextToImageRequest requestPayload = new TextToImageRequest(finalPrompt, stylePreset);
        return stabilityAiClient.generateImageFromText(
                "Bearer " + apiKey,
                engineId,
                requestPayload
        );
    }
}