package com.yiduochens.recommendation.service;

import com.yiduochens.recommendation.domain.RecommendationItem;
import com.yiduochens.recommendation.repository.RecommendationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class RecommendationService {

    private static final String MUSIC_PK = "MUSIC";
    private final RecommendationRepository repository;

    public RecommendationService(RecommendationRepository repository) {
        this.repository = repository;
    }

    public RecommendationItem getRandomMusic() {
        List<RecommendationItem> items = repository.findByPk(MUSIC_PK);
        if (items.isEmpty()) {
            throw new IllegalStateException("No music recommendations found in DynamoDB.");
        }
        int idx = ThreadLocalRandom.current().nextInt(items.size());
        return items.get(idx);
    }

    public RecommendationItem createMusic(String title, String reason) {
        RecommendationItem item = new RecommendationItem();
        item.setPk(MUSIC_PK);
        item.setSk("ITEM#" + UUID.randomUUID());
        item.setTitle(title);
        item.setReason(reason);
        repository.put(item);
        return item;
    }
}
