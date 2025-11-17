package com.lazarus.resources.dto.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceTypeRequestDTO{
        String unitCode;
        String code;
        String name;
        String description;
        Integer minGranularityMinutes;
} 

