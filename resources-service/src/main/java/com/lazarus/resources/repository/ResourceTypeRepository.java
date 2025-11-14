package com.lazarus.resources.repository;

import com.lazarus.resources.model.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ResourceTypeRepository extends JpaRepository<ResourceType, UUID> {
    Optional<ResourceType> findByName(String name);

}