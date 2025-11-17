package com.lazarus.resources.dto.resource;

import com.lazarus.resources.dto.common.IdOnlyDTO;
import java.util.Map;

public record ResourceRequestDTO(
        String code,
        String name,
        Map<String, Object> attributesJson,
        String photoUrl,
        Boolean active,
        IdOnlyDTO location,
        IdOnlyDTO type
) {}
