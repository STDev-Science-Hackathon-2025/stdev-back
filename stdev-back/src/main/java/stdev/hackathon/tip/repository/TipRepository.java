package stdev.hackathon.tip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stdev.hackathon.tip.entity.Tip;

public interface TipRepository extends JpaRepository<Tip, Long> {
}
