package com.Generator.apirest.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "SalveProyect")
public class SalveProyect  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 556891258919453055L;


	   @Id
	   @GeneratedValue(generator = "sequence_mat_id_generator")
	   @SequenceGenerator(name="sequence_mat_id_generator", initialValue= 1, allocationSize=1000)
	   @Column(name = "id", updatable = true, nullable = false, length = 25)
	   private Long id;

	    @Column(name = "name")
	    private String autor;

	    @Column(name= "user")
	    private String user;
	
	    @Column(name= "proyectoname")
	    private String proyectoName;
	    
	    @Column(name= "packageNames")
		private String packageNames;
	    
	    @Column(name= "description")
		private String description;
	    
	    @Lob
	    @Column(name= "proyectojson")
		private String entidades;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getAutor() {
			return autor;
		}

		public void setAutor(String autor) {
			this.autor = autor;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public String getProyectoName() {
			return proyectoName;
		}

		public void setProyectoName(String proyectoName) {
			this.proyectoName = proyectoName;
		}

		public String getPackageNames() {
			return packageNames;
		}

		public void setPackageNames(String packageNames) {
			this.packageNames = packageNames;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getEntidades() {
			return entidades;
		}

		public void setEntidades(String entidades) {
			this.entidades = entidades;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		@Override
		public String toString() {
			return "SalveProyect [id=" + id + ", autor=" + autor + ", user=" + user + ", proyectoName=" + proyectoName
					+ ", packageNames=" + packageNames + ", description=" + description + ", entidades=" + entidades
					+ "]";
		}
	    
	
	
}
