package com.Generator.apirest.services;



import com.Generator.apirest.entity.SalveProyect;

import java.util.List;

public interface SalveProyectService {
	public void saveProyectInternamente(SalveProyect proyect);
	public boolean saveProyect(SalveProyect proyect);
	public SalveProyect findByProyectoName(String name);
	public  List<SalveProyect> findByAutor(String autor);
	public  List<SalveProyect> findByUser(String user);
	public SalveProyect  findById (Long id);
	public Long countProyect(String name);
//	public boolean deleteProyect(String name);
}
