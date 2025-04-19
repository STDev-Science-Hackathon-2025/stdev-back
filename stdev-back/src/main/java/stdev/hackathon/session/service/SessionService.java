package stdev.hackathon.session.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stdev.hackathon.session.entity.Session;
import stdev.hackathon.session.repository.SessionRepository;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;
    public Session createNewSession() {
        Session session = Session.builder()
                .familiarityScore(0)
                .build();

        return sessionRepository.save(session);
    }

}
