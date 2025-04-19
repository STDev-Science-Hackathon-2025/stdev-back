package stdev.hackathon.tip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import stdev.hackathon.tip.entity.Tip;
import stdev.hackathon.tip.service.TipService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tips")
public class TipController {

    private final TipService tipService;

    @GetMapping
    public List<Tip> getAllTips() {
        return tipService.getAllTips();
    }

    @GetMapping("/{tipId}")
    public Tip getTipById(@PathVariable Long tipId) {
        return tipService.getTipById(tipId);
    }
}
