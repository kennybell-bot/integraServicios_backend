package com.lazarus.resources.dto.type;

import java.time.LocalTime;
import com.lazarus.resources.dto.common.IdOnlyDTO;

public record ResourceTypeAvailabilityRequestDTO(
        Integer weekday,
        LocalTime startTime,
        LocalTime endTime,
        Boolean isOpen,
        IdOnlyDTO resourceType
) {}
