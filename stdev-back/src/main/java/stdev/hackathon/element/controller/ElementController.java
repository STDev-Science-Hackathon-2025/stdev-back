package stdev.hackathon.element.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stdev.hackathon.element.entity.Element;
import stdev.hackathon.element.service.ElementService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/elements")
public class ElementController {

    private final ElementService elementService;

    @GetMapping("/{elementId}")
    public Element getElementById(@PathVariable Long elementId) {
        return elementService.getElementById(elementId);
    }

}