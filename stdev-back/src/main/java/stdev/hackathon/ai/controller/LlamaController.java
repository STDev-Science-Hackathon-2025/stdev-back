package stdev.hackathon.ai.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stdev.hackathon.ai.service.GPTService;
import stdev.hackathon.ai.service.LlamaService;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/llama")
public class LlamaController {
    private final LlamaService llamaService;
    private final GPTService gptService;

    @PostMapping("/ask/{sessionId}")
    public ResponseEntity<String> ask(@PathVariable Long sessionId, @RequestBody String prompt) {
        String response = llamaService.askLlama(sessionId, prompt);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/ask-gpt/{sessionId}")
    public ResponseEntity<String> askGPT(@PathVariable Long sessionId, @RequestBody String prompt) {
        String response = gptService.askGPT(sessionId, prompt);
        return ResponseEntity.ok(response);
    }
}