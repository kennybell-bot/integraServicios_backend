package com.lazarus.resources.repository;

import com.lazarus.resources.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ResourceRepository extends JpaRepository<Resource, UUID> {
    List<Resource> findByType_Id(UUID typeId);
    List<Resource> findByLocation_Id(UUID locationId);
}
