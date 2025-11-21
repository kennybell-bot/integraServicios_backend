package com.lazarus.reviews.service.impl;

import com.lazarus.reviews.dto.CreateRatingRequest;
import com.lazarus.reviews.dto.RatingResponse;
import com.lazarus.reviews.dto.UpdateRatingRequest;
import com.lazarus.reviews.mapper.RatingMapper;
import com.lazarus.reviews.model.Rating;
import com.lazarus.reviews.repository.RatingRepository;
import com.lazarus.reviews.service.RatingService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;

    public RatingServiceImpl(RatingRepository ratingRepository, RatingMapper ratingMapper) {
        this.ratingRepository = ratingRepository;
        this.ratingMapper = ratingMapper;
    }

    @Override
    public RatingResponse createRating(CreateRatingRequest request) {

        if (ratingRepository.existsByReservationId(request.getReservationId())) {
            throw new IllegalStateException("A rating for this reservation already exists.");
        }

        Rating rating = ratingMapper.toEntity(request);
        rating.setScore(BigDecimal.valueOf(request.getScore()));

        Rating saved = ratingRepository.save(rating);
        return ratingMapper.toResponse(saved);
    }

    @SuppressWarnings("null")
    @Override
    public RatingResponse updateRating(UUID id, UpdateRatingRequest request) {

        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rating not found with id " + id));

        rating.setScore(BigDecimal.valueOf(request.getScore()));

        Rating updated = ratingRepository.save(rating);
        return ratingMapper.toResponse(updated);
    }

    @SuppressWarnings("null")
    @Override
    public RatingResponse getRatingById(UUID id) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rating not found with id " + id));

        return ratingMapper.toResponse(rating);
    }

    @Override
    public RatingResponse getRatingByReservationId(UUID reservationId) {
        Rating rating = ratingRepository.findByReservationId(reservationId)
                .orElseThrow(() -> new EntityNotFoundException("Rating not found for reservation " + reservationId));

        return ratingMapper.toResponse(rating);
    }

    @Override
    public Double getGlobalAverageScore() {
        return ratingRepository.getGlobalAverageScore();
    }

    @Override
    public Double getAverageScoreByReservation(UUID reservationId) {
        return ratingRepository.getAverageByReservation(reservationId);
    }

    @SuppressWarnings("null")
    @Override
    public void deleteRating(UUID id) {
        if (!ratingRepository.existsById(id)) {
            throw new EntityNotFoundException("Rating not found with id " + id);
        }
        ratingRepository.deleteById(id);
    }
}

