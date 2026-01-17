package com.yiduochens.recommendation.controller;

import com.yiduochens.recommendation.domain.RecommendationItem;
import com.yiduochens.recommendation.service.RecommendationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService service;

    public RecommendationController(RecommendationService service) {
        this.service = service;
    }

    @GetMapping("/random")
    public ResponseEntity<RecommendationItem> randomMusic() {
        return ResponseEntity.ok(service.getRandomMusic());
    }

    public record CreateRequest(
            @NotBlank String title,
            @NotBlank String reason
    ) {}

    @PostMapping
    public ResponseEntity<RecommendationItem> create(@Valid @RequestBody CreateRequest req) {
        return ResponseEntity.ok(service.createMusic(req.title(), req.reason()));
    }
}
