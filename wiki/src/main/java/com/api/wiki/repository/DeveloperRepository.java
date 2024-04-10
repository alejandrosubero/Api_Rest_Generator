package com.api.wiki.repository;

import com.api.wiki.entitys.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    public List<Developer> findByName(String name);
    public List<Developer> findByLastName(String lastName);
}
