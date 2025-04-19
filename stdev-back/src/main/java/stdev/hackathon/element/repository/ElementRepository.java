package stdev.hackathon.element.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stdev.hackathon.element.entity.Element;

@Repository
public interface ElementRepository extends JpaRepository<Element, Long> {
}
