package com.api.wiki.repository;

import com.api.wiki.entitys.PaquetePackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaquetePackageRepository extends JpaRepository<PaquetePackage, Long> {
}
