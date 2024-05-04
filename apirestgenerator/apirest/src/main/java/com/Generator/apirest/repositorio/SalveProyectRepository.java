package com.Generator.apirest.repositorio;


import com.Generator.apirest.entity.SalveProyect;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SalveProyectRepository extends CrudRepository<SalveProyect, Long> {

	public Optional<SalveProyect> findByProyectoName(String name);

	public List<Optional<SalveProyect>> findByAutor(String autor);

	public List<Optional<SalveProyect>> findByUser(String user);

	public Optional<SalveProyect> findByProyectoNameContaining(String name);
	
	
	
	// @Query ("select i.id, count(i) from SalveProyect i where i.user = :nombre")
	// @Query ("SELECT COUNT(*)  FROM SalveProyect i where i.user = ?1")
	 // @Query( value = "SELECT * FROM Users u WHERE u.status = ?1", nativeQuery = true)
	@Query( value = "SELECT COUNT(i)  from SalveProyect i where i.user = ?1") 
	public Long CountSalveProyect( String nombre);
	

//	@Query(value = "SELECT * FROM usuario;", nativeQuery = true)
//	public List<Usuario> findAllUser();
//
//	//@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
//	@Transactional
//	@Modifying
//	@Query( value =	"INSERT INTO usuario (id, nombre, paswork) VALUES ( :id, :nombre, :paswork);", nativeQuery = true)
//	void insertUser(@Param("id") UUID id , @Param("nombre") String nombre, @Param("paswork") String paswork);
	
	
	
}
