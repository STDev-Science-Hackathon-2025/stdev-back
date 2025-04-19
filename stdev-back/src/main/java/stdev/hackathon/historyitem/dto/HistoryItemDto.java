package stdev.hackathon.historyitem.dto;

import lombok.Getter;
import stdev.hackathon.historyitem.entity.HistoryItem;

@Getter
public class HistoryItemDto {
    private String question;
    private String answer;

    public HistoryItemDto(HistoryItem entity) {
        this.question = entity.getQuestion();
        this.answer = entity.getAnswer();
    }
}
