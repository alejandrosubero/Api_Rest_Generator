package com.Generator.apirest.pojos.back;

public class RelacionPojo {

	private Boolean mappedByRelacion;// nuevo
	private String mappedBy; // a un no esta en uso
	private Boolean bidireccional;/// no esta en uso aun
	private String nameClassRelacionar; // clase padre
	private String nameClassRelacion;// clase a relacionar con el padre
	private String nameRelacion;
	private String relation; // @OneToOne, @OneToMany, @ManyToOne, @OneToMany, @ManyToMany
	private Boolean joinColumn;
	private Boolean fetchType;
	private String fetchTypes; // FetchType.LAZY, FetchType.EAGER
	private String joinColumnName;
	private Boolean isJoinTable;
	private Boolean jointabaleTipo;
	private String joinColumnNameReferencedColumnName;
 	private String JoinTableName;
	private String joinColumnName2;
 	private String cascadeType;// all, delete, persist,
	private Boolean orphanRemoval;

	public RelacionPojo() {	}

	public Boolean getMappedByRelacion() {
		return mappedByRelacion;
	}

	public void setMappedByRelacion(Boolean mappedByRelacion) {
		this.mappedByRelacion = mappedByRelacion;
	}

	public String getNameClassRelacionar() {
		return nameClassRelacionar;
	}

	public void setNameClassRelacionar(String nameClassRelacionar) {
		this.nameClassRelacionar = nameClassRelacionar;
	}

	public String getMappedBy() {
		return mappedBy;
	}

	public void setMappedBy(String mappedBy) {
		this.mappedBy = mappedBy;
	}

	public Boolean getOrphanRemoval() {
		return orphanRemoval;
	}

	public void setOrphanRemoval(Boolean orphanRemoval) {
		this.orphanRemoval = orphanRemoval;
	}

	public String getNameClassRelacion() {
		return nameClassRelacion;
	}

	public void setNameClassRelacion(String nameClassRelacion) {
		this.nameClassRelacion = nameClassRelacion;
	}

	public String getNameRelacion() {
		return nameRelacion;
	}

	public void setNameRelacion(String nameRelacion) {
		this.nameRelacion = nameRelacion;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public Boolean getBidireccional() {
		return bidireccional;
	}

	public void setBidireccional(Boolean bidireccional) {
		this.bidireccional = bidireccional;
	}

	public Boolean getJoinColumn() {
		return joinColumn;
	}

	public void setJoinColumn(Boolean joinColumn) {
		this.joinColumn = joinColumn;
	}

	public Boolean getFetchType() {
		return fetchType;
	}

	public void setFetchType(Boolean fetchType) {
		this.fetchType = fetchType;
	}

	public String getFetchTypes() {
		return fetchTypes;
	}

	public void setFetchTypes(String fetchTypes) {
		this.fetchTypes = fetchTypes;
	}

	public String getJoinColumnName() {
		return joinColumnName;
	}

	public void setJoinColumnName(String joinColumnName) {
		this.joinColumnName = joinColumnName;
	}


	public String getJoinColumnNameReferencedColumnName() {
		return joinColumnNameReferencedColumnName;
	}

	public void setJoinColumnNameReferencedColumnName(String joinColumnNameReferencedColumnName) {
		this.joinColumnNameReferencedColumnName = joinColumnNameReferencedColumnName;
	}

	public String getCascadeType() {
		return cascadeType;
	}

	public void setCascadeType(String cascadeType) {
		this.cascadeType = cascadeType;
	}

	public Boolean getIsJoinTable() {
		return isJoinTable;
	}

	public void setIsJoinTable(Boolean isJoinTable) {
		this.isJoinTable = isJoinTable;
	}

	public Boolean getJoinTable() {
		return isJoinTable;
	}

	public void setJoinTable(Boolean joinTable) {
		isJoinTable = joinTable;
	}

	public String getJoinTableName() {
		return JoinTableName;
	}

	public void setJoinTableName(String joinTableName) {
		JoinTableName = joinTableName;
	}

	public String getJoinColumnName2() {
		return joinColumnName2;
	}

	public void setJoinColumnName2(String joinColumnName2) {
		this.joinColumnName2 = joinColumnName2;
	}

	public Boolean getJointabaleTipo() {
		return jointabaleTipo;
	}

	public void setJointabaleTipo(Boolean jointabaleTipo) {
		this.jointabaleTipo = jointabaleTipo;
	}


}
