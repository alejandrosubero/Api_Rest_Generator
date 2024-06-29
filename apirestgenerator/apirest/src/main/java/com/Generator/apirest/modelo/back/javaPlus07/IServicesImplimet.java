package com.Generator.apirest.modelo.back.javaPlus07;

import com.Generator.apirest.pojos.back.EntityPojo;
import com.Generator.apirest.pojos.back.LayerPojo;
import com.Generator.apirest.pojos.back.RelationshipPojo;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;

public interface IServicesImplimet {

    public StringBuilder metods(ArchivoBaseDatosPojo archivo, EntityPojo entidad, String repositorieNameOjecte, String entidadNombre);

    public StringBuilder crearMetodoloop(EntityPojo entidad, String repositorieNameOjecte);

    public StringBuilder metodgetAll(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre);

    public StringBuilder metodSave(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre);

    public StringBuilder metodDelete(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre);

    public StringBuilder metodUpdate(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre);

    public StringBuilder metodfindById(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre);

    public StringBuilder metodsaveOrUpdate(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre);

    public StringBuilder ContainingRelacion(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre);

    public StringBuilder Relacionx(EntityPojo entidad, String entidadNombre, RelationshipPojo relacion);

    public StringBuilder ContainingRelacionNoBiDirectional(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre, LayerPojo layerPojo);

    public  StringBuilder metodContaining(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre);


}
