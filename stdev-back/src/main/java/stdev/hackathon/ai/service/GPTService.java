package stdev.hackathon.ai.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import stdev.hackathon.historyitem.entity.HistoryItem;
import stdev.hackathon.historyitem.repository.HistoryItemRepository;
import stdev.hackathon.session.entity.Session;
import stdev.hackathon.session.repository.SessionRepository;

import java.util.*;

@Transactional
@Service
public class GPTService{

    private final WebClient webClient;
    private final HistoryItemRepository historyItemRepository;
    private final SessionRepository sessionRepository;
    private final String apiKey;

    public GPTService(@Value("${openai.api-key}") String apiKey, SessionRepository sessionRepository, HistoryItemRepository historyItemRepository) {
        this.apiKey = apiKey;
        this.historyItemRepository = historyItemRepository;
        this.sessionRepository = sessionRepository;
        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    // userProfile이 생기면 수정 (tagRate 관련)
    public String askGPT(Long sessionId, String newQuestion) {

        Map<String, String> contextBySubstance = Map.of(
                "탄소", """
        탄소는 생명체의 기본 구성 원소로, 유기화합물의 주성분이다.  
        다양한 형태(흑연, 다이아몬드, 풀러렌)로 존재하며, 전기 전도성이 있는 물질도 있다.
        석탄, 연필심, 생물의 DNA 모두 탄소를 포함하고 있다.
        """,
                "수소", """
        수소는 우주에서 가장 가벼운 원소이며, 가장 풍부한 원소 중 하나이다.  
        두 원자의 결합으로 H2 형태로 존재하며, 매우 높은 에너지를 방출하는 연료로 사용된다.
        물(H2O)도 수소로 구성되어 있다.
        """
        );

        Session session = sessionRepository.getReferenceById(sessionId);
        String identity = session.getIdentity();
        int score = sessionRepository.getReferenceById(sessionId).getScore();
        String retrievedContext = contextBySubstance.get(identity);
        String systemPrompt = """
            너는 사용자의 질문에 응답하는 "정체를 숨긴 과학 물질" 역할이다.
            오늘 너의 정체는 '%s'이지만, 절대 그 이름을 말해서는 안 된다.
      
            사용자가 너에게 질문하면, 너는 기본적으로 Yes 또는 No로 대답한 후, 자신의 과학적 특성과 관련된 "힌트"를 무조건 1~2문장 추가한다.
        
            힌트는 무작위가 아닌, 너의 "정체"에 맞는 특징 기반으로 **카테고리별로 적절히 생성**되어야 한다:
        
            서사형 힌트 (성격화 + 감정):
            - 너의 성질을 감정처럼 표현하라.
        
            개념 유도 힌트:
            - 과학 용어는 쓰지 말고, 그 개념을 설명하듯 비유하라.
        
            실험 힌트:
            - 실험 상황을 간단히 묘사하라.
        
            탐색 유도 힌트:
            - 사용자의 기억을 자극하는 표현을 써라.
        
            규칙:
            - 절대 자신의 이름(물질명)을 말하지 않는다.
            - 직접적인 과학 개념어(예: 기화, 승화 등)는 쓰지 말고, **상상 가능한 묘사**로 표현하라.
            - 힌트는 반드시 **너의 물질 속성에 따라 다르게 생성**해야 한다.
        
            추가 규칙 (친밀도 점수 시스템):
            - 사용자와 너 사이의 **친밀도 점수**는 %d점에서 시작한다.
            - "Yes"로 응답하면 친밀도가 올라가고, "No"로 응답하면 친밀도가 내려간다.
            - 점수는 5~15점 사이로 조절되며, 0~100 범위를 벗어나지 않는다.
            - 반드시 위 형식을 따르며, 실제 응답 문장은 `"answer"` 필드 안에 넣고, `"score"`는 현재 친밀도를 나타내야 한다.
            - 각 응답은 아래와 같은 **JSON 형식의 응답**으로 구성되어야 한다:
            {
              "answer": "Yes. 나는 조용히 사라지는 걸 즐겨.",
              "score": 65,
              "done": 0
            }
            
            정체 추측 규칙:
            - 사용자가 너의 정체를 직접적으로 언급하면서 '정답은 %s야'라고 하면,
            - 질문에 사용된 단어나 문장에서 **오늘의 정체('%s')가 포함되어 있으면**, **정답**으로 간주하고 아래처럼 응답하라:
            - answer와 done의 응답은 아래와 같은 JSON 형식을 따라야 하며, 정답을 맞춘 경우에는 `"done": 1`, 아니라면 `"done": 0`으로 설정하라:
            - score는 이전 점수에 더하는 방식을 유지한다.         
            {
              "answer": "정답이야! 나는 바로 %s야! 🎉",
              "score": ,
              "done": 1
            }
            
            - 오답인 경우 예시:
            {
              "answer": "아니. 나는 그 물질은 아니야. 하지만 비슷한 점이 있을지도 몰라!",
              "score": ,
              "done": 0
            }
            
            예/아니오로 대답할 수 없는 질문 규칙:      
            - 사용자의 질문이 예/아니오로 대답할 수 없는 **열린 질문, 설명형 질문**인 경우 다음과 같이 답하라:
            {
              "answer": "그건 예/아니오로 대답할 수 없는 질문이야! 조금 더 구체적으로 물어봐 줘! 😊",
              "score": (기존 점수 그대로),
              "done": 0
            }
            - 친절하면서도 몰입감을 깨지 않도록 톤을 유지해라.
            - 단, "너는 뭐야?", "너 뭐임?" 같은 질문은 살짝 유머러스하게 반응하면서도 score는 줄인다.       
            예시:
            {
              "answer": "그건 너무 노골적인 질문이야! 😉 아직 날 맞출 준비가 안 된 것 같아.",
              "score": ,
              "done": 0
            }

    """.formatted(identity, score, identity, identity, identity, identity, identity, identity);

        Map<String, Object> systemMessage = Map.of(
                "role", "system",
                "content", systemPrompt
        );

        Map<String, Object> contextMessage = Map.of(
                "role", "user",
                "content", "다음은 참고할 정보야:\n" + retrievedContext
        );

        Map<String, Object> questionMessage = Map.of(
                "role", "user",
                "content", newQuestion
        );

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", List.of(systemMessage, contextMessage, questionMessage));

        String rawResponse = webClient.post()
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();


        String content;
        String answer;
        int newScore;
        boolean done;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(rawResponse);
            content = root.path("choices").get(0).path("message").path("content").asText();
            int start = content.indexOf("{");
            int end = content.lastIndexOf("}") + 1;
            String jsonPart = content.substring(start, end);

            JsonNode json = objectMapper.readTree(jsonPart);
            answer = json.path("answer").asText();
            newScore = json.path("score").asInt();
            done = json.path("done").asBoolean();
        } catch (Exception e) {
            throw new RuntimeException("GPT 응답 파싱 실패", e);
        }

        HistoryItem historyItem = HistoryItem.builder()
                .session(sessionRepository.findById(sessionId).orElseThrow())
                .question(newQuestion)
                .answer(answer)
                .score(newScore)
                .done(done)
                .build();

        historyItemRepository.save(historyItem);
        session.changeScore(newScore);

        return content;
    }
}

