package com.example.ghibiliapi.DTO;

import lombok.Getter;
import java.util.List;

public class TextToImageRequest {
    @Getter
    private List<TextPrompt> text_prompts;
    @Getter
    private double cfg_scale = 7;
    @Getter
    private int height = 512;
    @Getter
    private int width = 768;
    @Getter
    private int sample = 1;
    @Getter
    private int steps = 30;
    @Getter
    private String style_preset;

    public static class TextPrompt {
        private String text;

        public TextPrompt(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public TextToImageRequest(String text, String style) {
        this.text_prompts = List.of(new TextPrompt(text));
        this.style_preset = style;
    }
}