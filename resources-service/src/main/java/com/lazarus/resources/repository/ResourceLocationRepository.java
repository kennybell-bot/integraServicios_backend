package com.lazarus.resources.repository;

import com.lazarus.resources.model.ResourceLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ResourceLocationRepository extends JpaRepository<ResourceLocation, UUID> {
}