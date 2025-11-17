package com.lazarus.resources.mapper;

import com.lazarus.resources.model.Resource;
import com.lazarus.resources.dto.resource.*;
import com.lazarus.resources.dto.type.ResourceTypeResponseDTO;
import com.lazarus.resources.dto.location.ResourceLocationResponseDTO;
import com.lazarus.resources.model.ResourceLocation;
import com.lazarus.resources.model.ResourceType;

import java.util.UUID;

import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ResourceMapper {

    @Mapping(target = "location", qualifiedByName = "locationToDTO")
    @Mapping(target = "type", source = "type.id", qualifiedByName = "fromTypeId")
    @Mapping(target = "id", ignore = true)
    Resource toEntity(ResourceRequestDTO dto);

    @Mapping(target = "location", qualifiedByName = "locationToDTO")
    @Mapping(target = "type", qualifiedByName = "typeToDTO")
    ResourceResponseDTO toDTO(Resource resource);

    @Named("locationToDTO")
    default ResourceLocationResponseDTO mapLocation(ResourceLocation loc) {
        return new ResourceLocationResponseDTO(
                loc.getId(),
                loc.getName(),
                loc.getCampus(),
                loc.getBuilding(),
                loc.getFloor(),
                loc.getRoom(),
                loc.getDescription()
        );
    }

    @Named("typeToDTO")
    default ResourceTypeResponseDTO mapType(ResourceType type) {
        if (type == null) return null;
        return new ResourceTypeResponseDTO(
                type.getId(),
                type.getUnitCode(),
                type.getCode(),
                type.getName(),
                type.getDescription(),
                type.getMinGranularityMinutes()
        );
    }

    default ResourceLocation map(UUID id) {
        if (id == null) return null;
        ResourceLocation e = new ResourceLocation();
        e.setId(id);
        return e;
    }

    @Named("fromTypeId")
    default ResourceType mapTypeFromId(UUID id) {
        if (id == null) return null;
        ResourceType e = new ResourceType();
        e.setId(id);
        return e;
    }

}
