package com.lazarus.resources.repository;

import com.lazarus.resources.model.ResourceLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ResourceLocationRepository extends JpaRepository<ResourceLocation, UUID> {
}