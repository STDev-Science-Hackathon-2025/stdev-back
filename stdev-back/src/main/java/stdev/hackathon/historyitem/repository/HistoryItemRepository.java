package stdev.hackathon.historyitem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stdev.hackathon.historyitem.entity.HistoryItem;

import java.util.List;

@Repository
public interface HistoryItemRepository extends JpaRepository<HistoryItem, Long> {
    List<HistoryItem> findBySession_SessionId(Long sessionId);
}
