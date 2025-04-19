package stdev.hackathon.ai.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stdev.hackathon.ai.service.LlamaService;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/llama")
public class LlamaController {
    private final LlamaService llamaService;

    @PostMapping("/ask")
    public ResponseEntity<String> ask(@RequestBody String prompt) {
        String response = llamaService.askLlama(prompt);
        return ResponseEntity.ok(response);
    }
}