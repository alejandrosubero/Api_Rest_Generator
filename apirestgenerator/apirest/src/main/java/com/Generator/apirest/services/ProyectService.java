package com.Generator.apirest.services;



import com.Generator.apirest.entity.Proyecto;

import java.util.List;


public interface ProyectService {

	public Proyecto findByName(String nombreProyecto);
	public List<Proyecto> findByAll();
	public boolean saveProyecto(Proyecto proyecto);
}
