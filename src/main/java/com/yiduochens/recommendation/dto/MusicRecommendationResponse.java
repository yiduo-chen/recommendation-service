package com.yiduochens.recommendation.dto;

public record MusicRecommendationResponse(
        String type,
        String title,
        String reason
) {}