package com.lazarus.reviews.service;

import com.lazarus.reviews.dto.CreateRatingRequest;
import com.lazarus.reviews.dto.RatingResponse;
import com.lazarus.reviews.dto.UpdateRatingRequest;

import java.util.UUID;

public interface RatingService {

    RatingResponse createRating(CreateRatingRequest request);

    RatingResponse updateRating(UUID id, UpdateRatingRequest request);

    RatingResponse getRatingById(UUID id);

    RatingResponse getRatingByReservationId(UUID reservationId);

    Double getGlobalAverageScore();

    Double getAverageScoreByReservation(UUID reservationId);

    void deleteRating(UUID id);
}
