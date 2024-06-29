package com.Generator.apirest.core.pojos.back;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EntityPojo {

	private Boolean isEntity;
	private String nombreClase;
	private String nombreTabla;
	private String paquete;
	private Boolean delete;
	private List<AttributePojo> atributos = new ArrayList<>();
    private List<RelationshipPojo> relaciones = new ArrayList<>();

	private Boolean isSerializable;

	public EntityPojo() {}

	public Boolean getSerializable() {
		return isSerializable;
	}

	public void setSerializable(Boolean serializable) {
		isSerializable = serializable;
	}

	public void deleteActive(Boolean orden) {
		this.delete = orden;
	}
	
	
	public Boolean getIsEntity() {
		return isEntity;
	}

	public void setIsEntity(Boolean isEntity ) {
		this.isEntity = isEntity;
	}

	public String getNombreClase() {
		return nombreClase;
	}

	public void setNombreClase(String nombreClase) {
		this.nombreClase = nombreClase;
	}

	public String getNombreTabla() {
		return nombreTabla;
	}

	public void setNombreTabla(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}

	public String getPaquete() {
		return paquete;
	}

	public void setPaquete(String paquete) {
		this.paquete = paquete;
	}

	public Boolean getDelete() {
		return delete;
	}

	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

	public List<AttributePojo> getAtributos() {
		return atributos;
	}

	public void setAtributos(List<AttributePojo> atributos) {
		this.atributos = atributos;
	}

	public List<RelationshipPojo> getRelaciones() {
		return relaciones;
	}

	public void setRelaciones(List<RelationshipPojo> relaciones) {
		this.relaciones = relaciones;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EntityPojo that = (EntityPojo) o;
		return Objects.equals(isEntity, that.isEntity) && Objects.equals(nombreClase, that.nombreClase) && Objects.equals(nombreTabla, that.nombreTabla) && Objects.equals(paquete, that.paquete) && Objects.equals(delete, that.delete) && Objects.equals(atributos, that.atributos) && Objects.equals(relaciones, that.relaciones) && Objects.equals(isSerializable, that.isSerializable);
	}

	@Override
	public int hashCode() {
		return Objects.hash(isEntity, nombreClase, nombreTabla, paquete, delete, atributos, relaciones, isSerializable);
	}
}
