package stdev.hackathon.session.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stdev.hackathon.session.entity.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
