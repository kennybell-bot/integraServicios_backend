package com.lazarus.resources.repository;


import com.lazarus.resources.model.ResourceTypeAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ResourceTypeAvailabilityRepository extends JpaRepository<ResourceTypeAvailability, UUID> {

    List<ResourceTypeAvailability> findByResourceTypeId(UUID resourceTypeId);

    List<ResourceTypeAvailability> findByResourceTypeIdAndWeekday(UUID resourceTypeId, Integer weekday);
    List<ResourceTypeAvailability> findByWeekday(int weekday);
}