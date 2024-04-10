package com.api.wiki.repository;

import com.api.wiki.entitys.VersionControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VersionControlRepository extends JpaRepository<VersionControl, Long> {
    public List<VersionControl> findByVersion(String version);
}
