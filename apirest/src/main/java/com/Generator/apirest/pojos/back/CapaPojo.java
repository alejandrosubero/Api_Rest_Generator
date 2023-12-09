package com.Generator.apirest.pojos.back;

public class CapaPojo {

	private Boolean createCapaPojoForEntitys;
	private Boolean createCapaEntitysPlusPojo;
	private Boolean createCapaJavaBase7;
	private String savePojo;

	private String modelT;
	private String modelM;


	public CapaPojo() {
		super();
	}

	public String getModelM() {
		return modelM;
	}

	public void setModelM(String modelM) {
		this.modelM = modelM;
	}

	public String getModelT() {
		return modelT;
	}

	public void setModelT(String modelT) {
		this.modelT = modelT;
	}

	public Boolean getCreateCapaPojoForEntitys() {
		return createCapaPojoForEntitys;
	}
	public void setCreateCapaPojoForEntitys(Boolean createCapaPojoForEntitys) {
		this.createCapaPojoForEntitys = createCapaPojoForEntitys;
	}
	public Boolean getCreateCapaJavaBase7() {
		return createCapaJavaBase7;
	}
	public void setCreateCapaJavaBase7(Boolean createCapaJavaBase7) {
		this.createCapaJavaBase7 = createCapaJavaBase7;
	}

	public String getSavePojo() {
		return savePojo;
	}

	public void setSavePojo(String savePojo) {
		this.savePojo = savePojo;
	}

	public Boolean getCreateCapaEntitysPlusPojo() {
		return createCapaEntitysPlusPojo;
	}

	public void setCreateCapaEntitysPlusPojo(Boolean createCapaEntitysPlusPojo) {
		this.createCapaEntitysPlusPojo = createCapaEntitysPlusPojo;
	}
}
