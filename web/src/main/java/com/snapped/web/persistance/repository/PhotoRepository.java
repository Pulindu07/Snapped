package com.snapped.web.persistance.repository;

import com.snapped.web.persistance.entity.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<PhotoEntity, Long> {

    PhotoEntity findOneByCloudId(String cloudId);
}
