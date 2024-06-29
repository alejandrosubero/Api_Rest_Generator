package com.Generator.apirest.pojo;

public class ProyectoPojo {

    private Long id;
	private String name;
	private String mimetype;
    private byte[] bic;


	public ProyectoPojo( ) { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMimetype() {
		return mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	public byte[] getBic() {
		return bic;
	}

	public void setBic(byte[] bic) {
		this.bic = bic;
	}

}
