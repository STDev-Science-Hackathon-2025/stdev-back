package stdev.hackathon.tip.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stdev.hackathon.tip.entity.Tip;
import stdev.hackathon.tip.repository.TipRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipService {

    private final TipRepository tipRepository;

    public List<Tip> getAllTips() {
        return tipRepository.findAll();
    }

    public Tip getTipById(Long tipId) {
        return tipRepository.findById(tipId)
                .orElseThrow(() -> new IllegalArgumentException("해당 Tip이 존재하지 않습니다. id=" + tipId));
    }
}
