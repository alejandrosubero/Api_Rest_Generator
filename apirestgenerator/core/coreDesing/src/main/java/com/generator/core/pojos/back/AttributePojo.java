package com.generator.core.pojos.back;

public class AttributePojo {

	private Boolean sId;
	private String idName = "id";

	private String modificadorExtra; // static, final
	private Boolean ismodificadorExtra;

	private String tipoModificador;
	private String tipoDato; // Integer, Long, Double, String, Chat, Byte
	private String atributoName;
	private String nameColum;
	private Boolean atributoUpdatable;
	private Boolean atributoNullable;
	private Long length;

	private Boolean generatedValue; //
	private String tipoGeneratedValor; // strategy = GenerationType.AUTO, GenerationType.SEQUENCE
	private Boolean sequenceGenerator; // este es mas profundo de usar
	private Boolean tipoGenerador; // tipo de generador de sequencia
	private String sequenseName;
	private String nameSequenceTable;
	private Integer initialValue;
	private Integer allocationSize;
	private Boolean transiente; // @Transient
	
	private Boolean isList;
	private Boolean isArray;
	
	
	// private Boolean addSizeCheck;// @Size(min = 3, max = 255)
    // private Integer minSizeCheck;
    // private Integer maxSizeCheck;


	public AttributePojo() {	}
	
	
	public void isNullList() {
		if (this.isList == null) {
			this.isList = false;
		}
	}
	
	
	public void isNullArray() {
		if (this.isArray == null) {
			this.isArray = false;
		}
	}
	

	public Boolean getsId() {
		return sId;
	}

	public void setsId(Boolean sId) {
		this.sId = sId;
	}

	public Boolean getTipoGenerador() {
		return tipoGenerador;
	}

	public void setTipoGenerador(Boolean tipoGenerador) {
		this.tipoGenerador = tipoGenerador;
	}

	public String getIdName() {
		return idName;
	}

	public void setIdName(String idName) {
		this.idName = idName;
	}

	public String getModificadorExtra() {
		return modificadorExtra;
	}

	public void setModificadorExtra(String modificadorExtra) {
		this.modificadorExtra = modificadorExtra;
	}

	public Boolean getIsmodificadorExtra() {
		return ismodificadorExtra;
	}

	public void setIsmodificadorExtra(Boolean ismodificadorExtra) {
		this.ismodificadorExtra = ismodificadorExtra;
	}

	public String getTipoModificador() {
		return tipoModificador;
	}

	public void setTipoModificador(String tipoModificador) {
		this.tipoModificador = tipoModificador;
	}

	public String getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}

	public String getAtributoName() {
		return atributoName;
	}

	public void setAtributoName(String atributoName) {
		this.atributoName = atributoName;
	}

	public String getNameColum() {
		return nameColum;
	}

	public void setNameColum(String nameColum) {
		this.nameColum = nameColum;
	}

	public Boolean getGeneratedValue() {
		return generatedValue;
	}

	public void setGeneratedValue(Boolean generatedValue) {
		this.generatedValue = generatedValue;
	}

	public String getTipoGeneratedValor() {
		return tipoGeneratedValor;
	}

	public void setTipoGeneratedValor(String tipoGeneratedValor) {
		this.tipoGeneratedValor = tipoGeneratedValor;
	}

	public Boolean getSequenceGenerator() {
		return sequenceGenerator;
	}

	public void setSequenceGenerator(Boolean sequenceGenerator) {
		this.sequenceGenerator = sequenceGenerator;
	}

	public Long getLength() {
		return length;
	}

	public void setLength(Long length) {
		this.length = length;
	}

	public String getSequenseName() {
		return sequenseName;
	}

	public void setSequenseName(String sequenseName) {
		this.sequenseName = sequenseName;
	}

	public String getNameSequenceTable() {
		return nameSequenceTable;
	}

	public void setNameSequenceTable(String nameSequenceTable) {
		this.nameSequenceTable = nameSequenceTable;
	}

	public Integer getInitialValue() {
		return initialValue;
	}

	public void setInitialValue(Integer initialValue) {
		this.initialValue = initialValue;
	}

	public Integer getAllocationSize() {
		return allocationSize;
	}

	public void setAllocationSize(Integer allocationSize) {
		this.allocationSize = allocationSize;
	}

	public Boolean getAtributoUpdatable() {
		return atributoUpdatable;
	}

	public void setAtributoUpdatable(Boolean atributoUpdatable) {
		this.atributoUpdatable = atributoUpdatable;
	}

	public Boolean getAtributoNullable() {
		return atributoNullable;
	}

	public void setAtributoNullable(Boolean atributoNullable) {
		this.atributoNullable = atributoNullable;
	}

	public Boolean getTransiente() {
		return transiente;
	}

	public void setTransiente(Boolean transiente) {
		this.transiente = transiente;
	}

	public Boolean getIsList() {
		return isList;
	}

	public void setIsList(Boolean isList) {
		this.isList = isList;
	}

	public Boolean getIsArray() {
		return isArray;
	}

	public void setIsArray(Boolean isArray) {
		this.isArray = isArray;
	}
	

}


