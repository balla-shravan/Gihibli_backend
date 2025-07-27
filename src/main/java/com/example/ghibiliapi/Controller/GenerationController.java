package com.example.ghibiliapi.Controller;

import com.example.ghibiliapi.DTO.TextGenerationRequestDTO;
import com.example.ghibiliapi.Service.GhibliArtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = {"http://localhost:8080", "http://172.25.128.1:8080", "http://192.168.29.113:8080"})
@RequiredArgsConstructor
public class GenerationController {
    private final GhibliArtService ghibliArtService;

    @PostMapping(value = "/generate", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateGhibliArt(@RequestParam("image") MultipartFile image,
                                                    @RequestParam("prompt") String prompt) {
        try {
            System.out.println("Received image path: " + image.getOriginalFilename());
            System.out.println("Received prompt: " + prompt);
            byte[] imageByte = ghibliArtService.createGhibliArt(image, prompt);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageByte);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(value = "/generate-from-text", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateGhibliArtFromText(@RequestBody TextGenerationRequestDTO textGenerationRequestDTO) {
        try {
            System.out.println("Received prompt: " + textGenerationRequestDTO.getPrompt());
            System.out.println("Received style: " + textGenerationRequestDTO.getStyle());
            byte[] imageBytes = ghibliArtService.createGhibliArtFromText(
                    textGenerationRequestDTO.getPrompt(),
                    textGenerationRequestDTO.getStyle()
            );
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
