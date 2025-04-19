package stdev.hackathon.ai.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class LlamaService {
    private final WebClient webClient = WebClient.builder()
            .baseUrl("http://ec2-3-37-30-149.ap-northeast-2.compute.amazonaws.com:8000") // EC2라면 퍼블릭 IP로 교체
            .build();

    public String askLlama(String prompt) {
        return webClient.post()
                .uri("/generate")
                .bodyValue(new PromptRequest(prompt))
                .retrieve()
                .bodyToMono(PromptResponse.class)
                .map(PromptResponse::getLatest)
                .block(); // 비동기 → 동기 변환 (간단한 사용 예시)
    }

    public static class PromptRequest {
        private String prompt;

        public PromptRequest(String prompt) {
            this.prompt = prompt;
        }

        public String getPrompt() {
            return prompt;
        }

        public void setPrompt(String prompt) {
            this.prompt = prompt;
        }
    }

    public static class PromptResponse {
        private String latest;

        public String getLatest() {
            return latest;
        }

        public void setLatest(String latest) {
            this.latest = latest;
        }
    }
}