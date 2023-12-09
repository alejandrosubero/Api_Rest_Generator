package com.Generator.apirest.repositorio;


import com.Generator.apirest.entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {

	public Optional<Proyecto> findByName(String name);
	
}
