package stdev.hackathon.historyitem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import stdev.hackathon.session.entity.Session;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class HistoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyItemId;
    private String question;
    @Column(columnDefinition = "TEXT")
    private String answer;
    private int score;
    private boolean done;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    private Session session;

}
