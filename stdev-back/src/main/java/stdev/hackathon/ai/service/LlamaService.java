package stdev.hackathon.ai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import stdev.hackathon.historyitem.entity.HistoryItem;
import stdev.hackathon.ai.dto.PromptRequestDto;
import stdev.hackathon.ai.dto.PromptResponseDto;
import stdev.hackathon.historyitem.repository.HistoryItemRepository;
import stdev.hackathon.session.repository.SessionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LlamaService {

    private final HistoryItemRepository historyItemRepository;
    private final SessionRepository sessionRepository;
    // http://ec2-3-37-30-149.ap-northeast-2.compute.amazonaws.com:8000
    private final WebClient webClient = WebClient.builder()
            .baseUrl("http://localhost:8000") // EC2라면 퍼블릭 IP로 교체
            .build();

    public String askLlama(Long sessionId, String newQuestion) {
        List<HistoryItem> historyItems = historyItemRepository.findBySession_SessionId(sessionId);
        String answer = webClient.post()
                .uri("/generate")
                .bodyValue(new PromptRequestDto(newQuestion, historyItems))
                .retrieve()
                .bodyToMono(PromptResponseDto.class)
                .map(PromptResponseDto::getLatest)
                .block();

        // 응답을 DB에 저장
        HistoryItem item = HistoryItem.builder()
                .question(newQuestion)
                .answer(answer)
                .session(sessionRepository.findById(sessionId).orElseThrow()) // 필요 시 주입
                .build();

        historyItemRepository.save(item);
        return answer;
    }


}