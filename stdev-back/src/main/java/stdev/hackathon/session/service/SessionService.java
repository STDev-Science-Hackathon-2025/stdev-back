package stdev.hackathon.session.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stdev.hackathon.session.entity.Session;
import stdev.hackathon.session.repository.SessionRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;
    public Session createNewSession() {
        List<String> substances = Arrays.asList("탄소", "수소");
        String identity = substances.get(new Random().nextInt(substances.size()));

        Session session = Session.builder()
                .score(50)
                .identity(identity)
                .build();

        return sessionRepository.save(session);
    }

    public Session getSession(Long sessionId) {
        return sessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("세션이 존재하지 않습니다. id=" + sessionId));
    }

    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

}
