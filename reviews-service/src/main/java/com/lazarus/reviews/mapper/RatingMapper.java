package com.lazarus.reviews.mapper;

import com.lazarus.reviews.dto.CreateRatingRequest;
import com.lazarus.reviews.dto.RatingResponse;
import com.lazarus.reviews.dto.UpdateRatingRequest;
import com.lazarus.reviews.model.Rating;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Component;

@Component
public class RatingMapper {

    // Create DTO -> Entity
    public Rating toEntity(CreateRatingRequest dto) {
        Rating rating = new Rating();
        rating.setReservationId(dto.getReservationId());
        rating.setScore(BigDecimal.valueOf(dto.getScore()));
        return rating;
    }

    // Update DTO -> Entity (solo score)
    public void updateEntity(Rating entity, UpdateRatingRequest dto) {
        entity.setScore(BigDecimal.valueOf(dto.getScore()));
    }

    // Entity -> Response DTO
    public RatingResponse toResponse(Rating rating) {
        OffsetDateTime createdAtOffset =
                rating.getCreatedAt().atOffset(ZoneOffset.UTC);

        return new RatingResponse(
                rating.getId(),
                rating.getReservationId(),
                rating.getScore().doubleValue(),
                createdAtOffset
        );
    }

}
