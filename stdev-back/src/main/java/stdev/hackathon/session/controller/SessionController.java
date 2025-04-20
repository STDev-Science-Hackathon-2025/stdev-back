package stdev.hackathon.session.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stdev.hackathon.session.entity.Session;
import stdev.hackathon.session.service.SessionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sessions")
public class SessionController {

    private final SessionService sessionService;

    // 세션 생성
    @Operation(summary = "세션 생성")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "세션 생성 성공"),
            @ApiResponse(responseCode = "400", description = "세션 생성 실패")
    })
    @PostMapping("")
    public ResponseEntity<String> createSession() {
        Session session = sessionService.createNewSession(); // 변경: session 반환하도록 수정
        return ResponseEntity.ok("Created session with ID: " + session.getSessionId());
    }

    // 세션 조회
    @GetMapping("/{sessionId}")
    public ResponseEntity<Session> getSession(@PathVariable Long sessionId) {
        Session session = sessionService.getSession(sessionId);
        return ResponseEntity.ok(session);
    }

    @GetMapping
    public ResponseEntity<List<Session>> getAllSessions() {
        List<Session> sessions = sessionService.getAllSessions();
        return ResponseEntity.ok(sessions);
    }

}
