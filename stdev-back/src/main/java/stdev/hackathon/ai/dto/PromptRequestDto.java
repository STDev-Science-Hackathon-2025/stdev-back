package stdev.hackathon.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import stdev.hackathon.historyitem.dto.HistoryItemDto;
import stdev.hackathon.historyitem.entity.HistoryItem;

import java.util.List;

@AllArgsConstructor
@Getter
public class PromptRequestDto {
    private String prompt;
    private List<HistoryItemDto> history;

}