package com.Generator.apirest.services;



import com.Generator.apirest.entity.SalveProyect;

import java.util.List;

public interface SalveProyectAdminService {

    public SalveProyect findByProyectoName(String name);
    public boolean deleteProyect(String name);
    public List<SalveProyect> getAllProyect();

   //  public void saveProyectInternamente(SalveProyect proyect);
    // public boolean saveProyect(SalveProyect proyect);
  //   public SalveProyect findByAutor(String autor);
  //  public  SalveProyect findByUser(String user);
  //  public SalveProyect  findById (Long id);
}
