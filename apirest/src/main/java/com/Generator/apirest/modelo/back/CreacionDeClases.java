package com.Generator.apirest.modelo.back;


import com.Generator.apirest.core.Creador;
import com.Generator.apirest.notas.AnotacionesJava;
import com.Generator.apirest.pojos.back.AtributoPojo;
import com.Generator.apirest.pojos.back.EntidadesPojo;
import com.Generator.apirest.pojos.back.RelacionPojo;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import com.Generator.apirest.services.builders.IImportModel;

import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import com.google.common.collect.Lists;


@Component
public class CreacionDeClases implements IImportModel {

	 protected static final Log logger = LogFactory.getLog(CreacionDeClases.class);
	 
    public CreacionDeClases() {
    }

    public void startCreacionDeClases(ArchivoBaseDatosPojo archivo, Creador creadors) throws InterruptedException {
        createClass(archivo, creadors);
    }

    public void createClass(ArchivoBaseDatosPojo archivo, Creador creadors) throws InterruptedException {

        logger.info("inicia la creacion de la clase");
        if (archivo.getEntidades().size() > 0) {
            for (EntidadesPojo entidad : archivo.getEntidades()) {

                logger.info("Inicia la creacion de: " + "Es una entidad: " + entidad.getIsEntity() + "  NOMBRE = " + entidad.getNombreClase());
                StringBuilder sb = new StringBuilder(BREAK_LINE);
                sb.append(new AnotacionesJava(archivo).creatNotaClase());
                
                this.createImport(entidad, sb, archivo.getPackageNames());
                this.createTitleClass(entidad, sb);
                this.generateAtributosRelaciones(entidad, sb, creadors,archivo);
                this.generateSetterGetter(entidad, sb);
                this.generateEqualsmetodo(entidad, sb);
                this.createFileClass(entidad.getNombreClase(), entidad.getPaquete(), sb, archivo, creadors);
                            
            }
        }
    }


    private void createFileClass(String entidad_getNombreClase, String entidad_paquete, StringBuilder sb, ArchivoBaseDatosPojo archivo, Creador creador) throws InterruptedException {
    	
    	sb.append("}");
        sb.append(BREAK_LINE);
        sb.append(AnotacionesJava.apacheSoftwareLicensed());

        String direction = this.path(Lists.newArrayList(
        		creador.getDireccionDeCarpeta() + archivo.getProyectoName(),"src","main","java",
                           creador.getCom(),creador.getPackageNames1()
        	    		,creador.getArtifact(),entidad_paquete));
        creador.crearArchivo(direction, sb.toString(), entidad_getNombreClase + ".java");
    }

    private void createTitleClass(EntidadesPojo entidad , StringBuilder sb) throws InterruptedException {

        if (entidad.getIsEntity()) {
            sb.append(DOUBLEBREAK_LINE);
            sb.append("@Entity");
            sb.append(BREAK_LINE);
            sb.append("@Table(name = \"" + entidad.getNombreTabla() + "\")");
        }

        if(entidad.getSerializable() !=null && entidad.getSerializable()){
            Random rand = new Random();
            long rand_lub1 = rand.nextLong();
            sb.append(BREAK_LINE);
            sb.append("public class " + entidad.getNombreClase() + " implements Serializable {");
            sb.append(BREAK_LINE);
            sb.append("private static final long serialVersionUID = " + rand_lub1 + "L;");
            sb.append(DOUBLEBREAK_LINE);
        }else{
            sb.append(BREAK_LINE);
            sb.append("public class " + entidad.getNombreClase() + "{");
            sb.append(DOUBLEBREAK_LINE);
        }

//        if (entidad.getIsEntity()) {
//            sb.append(DOUBLEBREAK_LINE);
////            sb.append(BREAK_LINE);
//            sb.append("@Entity");
//            sb.append(BREAK_LINE);
//            sb.append("@Table(name = \"" + entidad.getNombreTabla() + "\")");
//            sb.append(BREAK_LINE);
//            sb.append("public class " + entidad.getNombreClase() + " implements Serializable {");
//            sb.append(BREAK_LINE);
//            long rand_lub1 = rand.nextLong();
//            sb.append(BREAK_LINE);
//            sb.append("private static final long serialVersionUID = " + rand_lub1 + "L;");
//            sb.append(DOUBLEBREAK_LINE);
////            sb.append(BREAK_LINE);
//        } else {
//            sb.append(DOUBLEBREAK_LINE);
////            sb.append(BREAK_LINE);
//            sb.append("public class " + entidad.getNombreClase() + " implements Serializable {"+BREAK_LINE);
//            long rand_lub2 = rand.nextLong();
//            sb.append(BREAK_LINE);
//            sb.append("private static final long serialVersionUID = " + rand_lub2 + "L;" + BREAK_LINE);
//            sb.append(BREAK_LINE);
//        }
 
    }


    private void createImport(EntidadesPojo entidad, StringBuilder sb, String packageNames) {
        sb.append("package " + packageNames + "." + entidad.getPaquete() + ";");
        sb.append(DOUBLEBREAK_LINE);
        sb.append(JAVAX_PERSISTENCE_IMPORT);
        sb.append(IO_SEREABILIZABLE_IMPORT);
        sb.append(importGroupOne());
        sb.append(UTIL_OBJECTS_IMPORT);
        sb.append(BREAK_LINE);
      
        if (entidad.getRelaciones().size() > 0) {
            List<RelacionPojo> relacionesImport = entidad.getRelaciones();
            for (RelacionPojo relacion : relacionesImport) {
                sb.append("import " + packageNames + "." + entidad.getPaquete() + "."+ relacion.getNameClassRelacion()+ ";");
                sb.append(BREAK_LINE);
            }
        }
    }


    private void generateAtributosRelaciones(EntidadesPojo entidad, StringBuilder sb, Creador creador, ArchivoBaseDatosPojo archivo) throws InterruptedException {
        Boolean isEntity = entidad.getIsEntity();
        String nombreEntidad = entidad.getNombreClase();
        if (entidad.getAtributos().size() > 0) {
            for (AtributoPojo atributo : entidad.getAtributos()) {
                this.generateAtributo(atributo, isEntity, sb, nombreEntidad, creador, archivo);
            }
        }
        if (entidad.getRelaciones().size() > 0) {
            for (RelacionPojo relacion : entidad.getRelaciones()) {
                this.generateRelacion(relacion, isEntity, sb);
            }
        }
    }

    private void generateAtributo(AtributoPojo atributo, Boolean entidad_getIsEntity, StringBuilder sb, String nombreEntidad,Creador creador,ArchivoBaseDatosPojo archivo) throws InterruptedException {

    	atributo.isNullArray();
    	atributo.isNullList();    
    	
        logger.info("Inicia la creacion de GenerateAtributos" + "  NOMBRE = " + atributo.getAtributoName());
        if (entidad_getIsEntity) {

            if (atributo.getsId()) {
                sb.append(TAB+"@Id"+BREAK_LINE);

                if (atributo.getSequenceGenerator()) {
                    atributo.setGeneratedValue(false);
                    sb.append(TAB+"@GeneratedValue(generator = \"sequence_" + atributo.getNameSequenceTable() + "_generator\")");
                    sb.append(BREAK_LINE);
                    sb.append(TAB+"@SequenceGenerator(name=\"sequence_");
                    sb.append(atributo.getNameSequenceTable());
                    sb.append("_generator\", " + "initialValue");
                    sb.append("= " + atributo.getInitialValue());
                    sb.append(", " + "allocationSize=" + atributo.getAllocationSize());
                    sb.append(")" );
                    sb.append(BREAK_LINE);
                }

                if (atributo.getTipoGenerador()) {
                    atributo.setGeneratedValue(false);
                    atributo.setSequenceGenerator(false);
                    sb.append(TAB+"@TableGenerator(name = \"" + atributo.getNameSequenceTable() + "\", ");
                    sb.append("table = \"" + archivo.getDatabaseName() + "." + creador.getArtifact() + "_SEQUENCE\", ");
                    sb.append("pkColumnName = \"SEQUENCE_NAME\", ");
                    sb.append("valueColumnName = \"SEQUENCE_COUNT\", ");
                    sb.append("pkColumnValue = \t\"P" + nombreEntidad + "\", ");
                    sb.append("initialValue = " + atributo.getInitialValue() + ", allocationSize ");
                    sb.append("= " + atributo.getAllocationSize() + ")\n");
                    sb.append("\t@GeneratedValue(strategy = GenerationType.TABLE, ");
                    sb.append("generator = \"" + atributo.getNameSequenceTable() + "\")\n");
                    sb.append( "\t");
                    sb.append(BREAK_LINE);
                }

                if (atributo.getGeneratedValue()) {  
                    if (archivo.getTipoDatabase() == 1 && archivo.getNativeMysql()) {
                        sb.append(TAB+"@GeneratedValue(strategy = GenerationType." + atributo.getTipoGeneratedValor() + ", generator = \"native\" )");
                        sb.append(BREAK_LINE);
                        sb.append(TAB+"@GenericGenerator(name = \"native\", strategy = \"native\")");
                        sb.append(BREAK_LINE);
                    } else {
                        sb.append(TAB+"@GeneratedValue(strategy = GenerationType." + atributo.getTipoGeneratedValor() + ")");
                        sb.append(BREAK_LINE);
                    }
                } 
            }

            if (atributo.getTransiente()) {
                sb.append(TAB+"@Transient");
                sb.append(BREAK_LINE);
            }
            sb.append(TAB+"@Column(name = \"" + atributo.getNameColum() + "\", ");
            sb.append("updatable = " + atributo.getAtributoUpdatable() + ", ");
            sb.append("nullable = " + atributo.getAtributoNullable());
            sb.append(", length = " + atributo.getLength() + ")");
            sb.append(BREAK_LINE);
            sb.append(TAB+PRIVATE+TAB);

            if (atributo.getIsList()) {
            	sb.append(LISTAS +TAB);
            }
            
            sb.append( atributo.getTipoDato());
            
            if (atributo.getIsList()) {
            	sb.append(" >");
            }
          
            if (atributo.getIsArray()) {
            	sb.append(ARRAY +TAB);
            }
            
            sb.append(TAB);
            sb.append(atributo.getAtributoName() + ";");
            sb.append(BREAK_LINE);
            sb.append(DOUBLEBREAK_LINE);
        } else {
            sb.append("		private " + atributo.getTipoDato() + " " + atributo.getAtributoName() + ";");
            sb.append(DOUBLEBREAK_LINE);
        }
    }


    private void generateRelacion(RelacionPojo relacion, Boolean entidad_getIsEntity, StringBuilder sb) throws InterruptedException {

        logger.info("Inicia la creacion de GenerateRelaciones" + "  NOMBRE = " + relacion.getNameRelacion());
        if (entidad_getIsEntity) {
            if (relacion.getBidireccional()) {
                if (relacion.getMappedByRelacion()) {
                    if (relacion.getRelation().equals("ManyToMany")) {
                        sb.append(TAB+"@JsonIgnore");
                        sb.append(BREAK_LINE);
                        sb.append(DOUBLETAB+"@" + relacion.getRelation() + "(cascade = CascadeType." + relacion.getCascadeType());
                        sb.append(", fetch = FetchType.LAZY");
                        sb.append(", mappedBy = \"" + relacion.getMappedBy() + "\"");
                        sb.append(", orphanRemoval = " + relacion.getOrphanRemoval() + ")");
                        sb.append(DOUBLEBREAK_LINE);
                    }
                } else {
                    if (relacion.getRelation().equals("ManyToMany")) {
                        sb.append(DOUBLETAB+"@" + relacion.getRelation() + "(cascade = CascadeType." + relacion.getCascadeType());
                        sb.append(", fetch = FetchType.LAZY");
                        sb.append(", orphanRemoval = " + relacion.getOrphanRemoval() + ")");
                        sb.append(DOUBLEBREAK_LINE);
                    }
                }
                if (relacion.getRelation().equals("ManyToOne")) {
                    sb.append(TAB+"@JsonIgnore");
                    sb.append(BREAK_LINE);
                    sb.append(DOUBLETAB+"@" + relacion.getRelation() + "(cascade = CascadeType." + relacion.getCascadeType());
                    sb.append(", fetch = FetchType.EAGER");
                    sb.append(", orphanRemoval = " + relacion.getOrphanRemoval() + ")");
                    sb.append(DOUBLEBREAK_LINE);
                }
            }


            if (relacion.getRelation().equals("OneToOne") || relacion.getRelation().equals("ManyToOne")) {
                sb.append(DOUBLETAB+"@" + relacion.getRelation() + "(cascade = CascadeType." + relacion.getCascadeType());
                sb.append(", fetch = FetchType.EAGER");
                sb.append(" )");
                sb.append(DOUBLEBREAK_LINE);
            }

            if (relacion.getRelation().equals("OneToMany") || relacion.getRelation().equals("ManyToMany")) {
                sb.append(DOUBLETAB+"@" + relacion.getRelation() + "(cascade = CascadeType." + relacion.getCascadeType());
                sb.append(", fetch = FetchType.LAZY");
                sb.append(", orphanRemoval = " + relacion.getOrphanRemoval() + ")");
                sb.append(DOUBLEBREAK_LINE);
            }

            if (relacion.getIsJoinTable()) {
                if (relacion.getJointabaleTipo()) {
                    sb.append(DOUBLETAB+"@JoinTable(name = \"" + relacion.getJoinTableName() + "\", ");
                    sb.append( "joinColumns = @JoinColumn(name= \"" + relacion.getJoinColumnName() + "\"), ");
                    sb.append("inverseJoinColumns = @JoinColumn(name=\"" + relacion.getJoinColumnName2() + "\"))");
                    sb.append(BREAK_LINE);
                } else {
                    sb.append(DOUBLETAB+"@JoinTable(name=\"" + relacion.getJoinTableName() + "\",  ");
                    sb.append("joinColumns = @JoinColumn(name= \"" + relacion.getJoinColumnName() + "_id\",  ");
                    sb.append("referencedColumnName=\"" + relacion.getJoinColumnNameReferencedColumnName() + "\"),  ");
                    sb.append("inverseJoinColumns = @JoinColumn(name= \"" + relacion.getJoinColumnName2() + "\"))\n");
                }
            }
            // si se escoje joinColumn para colocar en la relacion.
            if (relacion.getJoinColumn()) {
                sb.append(DOUBLETAB+"@JoinColumn(name = \"id_" + relacion.getNameClassRelacion() + "\")");
                sb.append(BREAK_LINE);
            }

            if (relacion.getRelation().equals("OneToOne") || relacion.getRelation().equals("ManyToOne")) {
                sb.append(DOUBLETAB+"private " + relacion.getNameClassRelacion() + " " + relacion.getNameRelacion() + ";");
                sb.append(BREAK_LINE);
            } else {
                sb.append(DOUBLETAB+"private   List<" + relacion.getNameClassRelacion() + ">" + relacion.getNameRelacion() + "= new ArrayList<>();");
            }
        } else {

            if (relacion.getRelation().equals("OneToOne") || relacion.getRelation().equals("ManyToOne")) {
                sb.append(DOUBLETAB+"private " + relacion.getNameClassRelacion() + " " + relacion.getNameRelacion() + ";");
                sb.append(BREAK_LINE);
            } else {
                sb.append(DOUBLETAB+"private   List<" + relacion.getNameClassRelacion() + ">" + relacion.getNameRelacion() + "= new ArrayList<>();");
                sb.append(BREAK_LINE);
            }
        }
        sb.append(DOUBLEBREAK_LINE);
    }

    private void generateSetterGetter(EntidadesPojo entidad, StringBuilder sb) throws InterruptedException {
        if (entidad.getAtributos().size() > 0) {
            for (AtributoPojo atributo : entidad.getAtributos()) {
                this.setterGetterAtributo(atributo, sb);
            }
        }

        if (entidad.getRelaciones().size() > 0) {
            for (RelacionPojo relacions : entidad.getRelaciones()) {
                this.setterGetterRelacion(relacions, sb);
            }
        }
    }


    private void setterGetterAtributo(AtributoPojo atributo, StringBuilder sb) {
    	 String atributoName = atributo.getAtributoName().substring(0, 1).toUpperCase() + atributo.getAtributoName().substring(1);
        sb.append(DOUBLETAB+"public " + atributo.getTipoDato() + " get" + atributoName + "() { ");
        sb.append(BREAK_LINE);
        sb.append(DOUBLETAB+"return " + atributo.getAtributoName() + ";");
        sb.append(BREAK_LINE);
        sb.append(DOUBLETAB+"}");
        sb.append(BREAK_LINE);
        sb.append(DOUBLETAB+"public void set" + atributoName + "(" + atributo.getTipoDato() + "  " + atributo.getAtributoName() + ") {\r\n");
        sb.append(DOUBLETAB+TAB+"this." + atributo.getAtributoName() + " = " + atributo.getAtributoName() + ";");
        sb.append(BREAK_LINE);
        sb.append(DOUBLETAB+"}");
        sb.append(BREAK_LINE);
    }


    public void setterGetterRelacion(RelacionPojo relacions, StringBuilder sb) {
        if (relacions.getRelation().equals("OneToOne") || relacions.getRelation().equals("ManyToOne")) {
            sb.append(DOUBLETAB+"public " + relacions.getNameClassRelacion() + " get" + relacions.getNameRelacion() + "() { ");
            sb.append(BREAK_LINE);
            sb.append(DOUBLETAB+"return " + relacions.getNameRelacion() + ";");
            sb.append(BREAK_LINE);
            sb.append(DOUBLETAB+"}");
            sb.append(BREAK_LINE);
            sb.append(DOUBLETAB+"public void set" + relacions.getNameRelacion() + "(" + relacions.getNameClassRelacion()+ "  " + relacions.getNameRelacion() + ") {\r\n");
            sb.append(DOUBLETAB+TAB+"this." + relacions.getNameRelacion() + " = " + relacions.getNameRelacion() + ";");
            sb.append(BREAK_LINE);
            sb.append(DOUBLETAB+"}");
            sb.append(BREAK_LINE);
        } else {
            sb.append(DOUBLETAB+"public List<" + relacions.getNameClassRelacion() + "> get" + relacions.getNameRelacion()+ "() { ");
            sb.append(BREAK_LINE);
            sb.append(DOUBLETAB+TAB+"return " + relacions.getNameRelacion() + ";");
            sb.append(BREAK_LINE);
            sb.append(DOUBLETAB+"}");
            sb.append(BREAK_LINE);
            sb.append(DOUBLETAB+"public void set" + relacions.getNameRelacion() + "( List<" + relacions.getNameClassRelacion() + ">  " + relacions.getNameRelacion() + ") {");
            sb.append(BREAK_LINE);
            sb.append(DOUBLETAB+TAB+"this." + relacions.getNameRelacion() + " = " + relacions.getNameRelacion() + ";");
            sb.append(BREAK_LINE);
            sb.append(DOUBLETAB+"}");
            sb.append(BREAK_LINE);
        }
    }

    private void generateEqualsmetodo(EntidadesPojo entidad, StringBuilder sb) throws InterruptedException {
      
        sb.append(DOUBLETAB+"public boolean equals" + entidad.getNombreClase() + "(Object o) {");
        sb.append(BREAK_LINE);
        sb.append(DOUBLETAB+TAB+"if (this == o) return true;");
        sb.append(BREAK_LINE);
        sb.append(DOUBLETAB+DOUBLETAB+"if (o == null || getClass() != o.getClass()) return false;" );
        sb.append(BREAK_LINE);
        sb.append(DOUBLETAB+DOUBLETAB+TAB + entidad.getNombreClase() + TAB + entidad.getNombreClase().toLowerCase() + " = (" + entidad.getNombreClase() + ") o;");
        sb.append(BREAK_LINE);
        sb.append(DOUBLETAB+DOUBLETAB+TAB+"return ");
     
        for (int i = 0; i < entidad.getAtributos().size(); i++) {
            sb.append(DOUBLETAB+TAB+"Objects.equals(" + entidad.getAtributos().get(i).getAtributoName() + ", ");
            sb.append(entidad.getNombreClase().toLowerCase()+ ".");
            sb.append(entidad.getAtributos().get(i).getAtributoName() + ")");

            if (i < entidad.getAtributos().size() - 1) {
                sb.append(" ||");
                sb.append(BREAK_LINE);
            }
        }
        
        sb.append(";" + BREAK_LINE);
        sb.append(BREAK_LINE);
        sb.append("}");
    }
}


