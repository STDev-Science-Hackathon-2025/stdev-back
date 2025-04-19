package stdev.hackathon.element.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stdev.hackathon.element.entity.Element;
import stdev.hackathon.element.repository.ElementRepository;

@Service
@RequiredArgsConstructor
public class ElementService {

    private final ElementRepository elementRepository;

    public Element getElementById(Long elementId) {
        return elementRepository.findById(elementId)
                .orElseThrow(() -> new IllegalArgumentException("해당 원소가 존재하지 않습니다. id=" + elementId));
    }

}
