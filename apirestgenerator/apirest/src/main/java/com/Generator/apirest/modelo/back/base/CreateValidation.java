package com.Generator.apirest.modelo.back.base;



import com.Generator.apirest.core.Creador;
import com.Generator.apirest.notas.AnotacionesJava;
import com.Generator.apirest.pojos.back.AttributePojo;
import com.Generator.apirest.pojos.back.LayerPojo;
import com.Generator.apirest.pojos.back.EntityPojo;
import com.Generator.apirest.pojos.back.RelationshipPojo;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;



@Component
public class CreateValidation {

    private String proyectoName;
    private String packageNames;
    private List<EntityPojo> entidades;
    private Creador creador;
    private ArchivoBaseDatosPojo archivo;
    private String barra =  java.nio.file.FileSystems.getDefault().getSeparator();

    private String clave;
    private List<EntityPojo> toPojos = new ArrayList<>();
    private List<EntityPojo> toEntidad = new ArrayList<>();
    private AnotacionesJava anotacionesJava = new AnotacionesJava();

    protected static final Log logger = LogFactory.getLog(CreateValidation.class);

    public void startCreacion(ArchivoBaseDatosPojo archivo, Creador creadors) {
        this.archivo = archivo;
        this.creador = creadors;
        this.setData();
    }

    private void setData(){
        this.entidades = this.archivo.getEntidades();
        this.proyectoName = this.archivo.getProyectoName();
        this.packageNames = this.archivo.getPackageNames();
        this.clave = this.archivo.getCapaPojo().getModelT();
        this.anotacionesJava.activateAnotacionesJava(this.archivo);
        this.create(this.entidades);
    }

    private void create(List<EntityPojo> entidadesList) {

        if (entidadesList.size() > 0) {
            try {
                this.separateEntidadToPojos(entidadesList);
            } catch (Exception e) {
                e.printStackTrace();
            }

            for (EntityPojo entidad : entidadesList) {
                if (entidad.getIsEntity()) {
                    String nameOfClass = entidad.getNombreClase() + "Validation";
                    try {
                        if (entidad.getIsEntity()) {
                            String escritos = metods(entidad, archivo.getCapaPojo()).toString();
                            createArchivo(escritos, nameOfClass);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    private void separateEntidadToPojos(List<EntityPojo> entidadesList) {
        logger.info("Inicia la separacion de entidades");
        for (EntityPojo entidad : entidadesList) {
            if (entidad.getPaquete().equals(clave) && !entidad.getIsEntity()) {
                toPojos.add(entidad);
            } else {
                toEntidad.add(entidad);
            }
        }
    }


    private  StringBuffer metods(EntityPojo entidad , LayerPojo layerPojo) throws InterruptedException {

        StringBuffer validations = new StringBuffer ();
        validations.append(this.anotacionesJava.creatNotaClase()+"\r\n");
        validations.append(this.createImport(entidad, layerPojo));
        validations.append(this.cabeceraClase(entidad));
        validations.append(this.metodoValidad(entidad, layerPojo));
        validations.append(this.metodovalida_id(entidad));
        validations.append(this.metodovalidation(entidad));
        validations.append("}"+"\r\n");
        validations.append(AnotacionesJava.apacheSoftwareLicensed()+"\r\n");
        return  validations;
    }

    private void createArchivo(String escrito, String nameOfClass) {
        try {
            String nombreArchivo = nameOfClass+".java";
            String entidad_paquete = "validation";
            String direction = creador.getDireccionDeCarpeta() + proyectoName +barra +"src"+barra+"main"+barra+"java"+barra + creador.getCom()
                    + barra + creador.getPackageNames1() + barra + creador.getArtifact()+ barra +"validation";
            creador.crearArchivo(direction, escrito, nombreArchivo);
        } catch (Exception e) {
            logger.error(e);
        }
    }


    private StringBuffer createImport(EntityPojo entidad, LayerPojo layerPojo){

    StringBuffer sb0 = new StringBuffer ();
    logger.info("createService" + "  for Entity:  " + entidad.getNombreClase());

    sb0.append("package " + packageNames + ".validation ;\r\n");
    sb0.append("\r\n");
    sb0.append("import java.util.Optional;");
    sb0.append("\r\n");
    sb0.append("import java.util.ArrayList;");
    sb0.append("\r\n");
    sb0.append("import java.util.List;");
    sb0.append( "import java.util.Date;"+"\r\n");
    sb0.append("\r\n");
    sb0.append("import " + packageNames + "." + entidad.getPaquete()+"."+ entidad.getNombreClase() + ";");
    sb0.append("import java.util.regex.Pattern;");
    sb0.append("\r\n");
    sb0.append("import org.springframework.stereotype.Service;");
    sb0.append("\r\n");

    for (EntityPojo entidadPojo : toPojos) {
        String[] clavePojo = entidadPojo.getNombreClase().split(layerPojo.getModelM());
        if (entidad.getNombreClase().equals(clavePojo[0])) {
            sb0.append("import " + packageNames + "." + entidadPojo.getPaquete()+"."+ entidadPojo.getNombreClase() + ";");
        }
    }

    for (RelationshipPojo relacion : entidad.getRelaciones()) {
        sb0.append("import " + packageNames + "." + entidad.getPaquete() + "." + relacion.getNameClassRelacion() + ";" + "\r\n");
        sb0.append("import " + packageNames + "."+ layerPojo.getModelT()+"."+ relacion.getNameClassRelacion() + layerPojo.getModelM()+ ";" + "\r\n");
    }
    return sb0;
}


    private StringBuffer cabeceraClase(EntityPojo entidad){
    StringBuffer sb1 = new StringBuffer ();
    String nameClass = entidad.getNombreClase()+"Validation";
    sb1.append("\r\n");
    sb1.append("\r\n");
    sb1.append("\r\n");
    sb1.append("    @Service" + "\r\n");
    sb1.append("    public class "+nameClass+" {" + "\r\n");
    sb1.append("\r\n");
    return sb1;
}


private StringBuffer metodoValidad(EntityPojo entidad, LayerPojo layerPojo){

    StringBuffer sb2 = new StringBuffer ();
    String variable = entidad.getNombreClase().toLowerCase();
    sb2.append("        public "+entidad.getNombreClase()+ layerPojo.getModelM()+" valida("+entidad.getNombreClase()+ layerPojo.getModelM()+" "+variable+") {" + "\r\n");
    sb2.append("        "+entidad.getNombreClase()+ layerPojo.getModelM()+" pojo = null;"+"\r\n");
    sb2.append("        try {"+"\r\n");
    sb2.append("             if ("+variable+" != null) {"+"\r\n");
    sb2.append("              if ("+"\r\n");

	for (int i = 0; i < entidad.getAtributos().size(); i++) {
		if (!entidad.getAtributos().get(i).getsId()) {
			String cadenaOriginal = entidad.getAtributos().get(i).getAtributoName();
			String atributoName = cadenaOriginal.substring(0, 1).toUpperCase()+ cadenaOriginal.substring(1);

			sb2.append("        " + variable + ".get" + atributoName + "() != null");
			if (i < entidad.getAtributos().size() - 1) {
				sb2.append(" &&" + "\r\n");
			}
		}
	}

    sb2.append("        ) {"+"\r\n");
    sb2.append("        pojo = "+variable+";"+"\r\n");
    sb2.append("         }"+"\r\n");
    sb2.append("        }"+"\r\n");
    sb2.append("            return pojo;"+"\r\n");
    sb2.append("        } catch (Exception e) {"+"\r\n");
    sb2.append("            e.printStackTrace();"+"\r\n");
    sb2.append("            return pojo;"+"\r\n");
    sb2.append("        }"+"\r\n");
    sb2.append("    }"+"\r\n");
    return sb2;
}


    private StringBuffer metodovalida_id(EntityPojo entidad){

        String datoTipo ="";
        StringBuffer sb3 = new StringBuffer ();
        String variable = entidad.getNombreClase().toLowerCase();
        for (AttributePojo atributoID : entidad.getAtributos()) {
            if (atributoID.getsId()) { datoTipo = atributoID.getTipoDato(); }
        }
        sb3.append("// remplace de name of variable for you proyecte"+"\r\n");
        sb3.append("    public "+datoTipo+" valida_id( String poder) {"+"\r\n");
        
        if (datoTipo.equals("Long")) { sb3.append("             Long id_Delete = 0l;");}
        if (datoTipo.equals("Integer")){ sb3.append("       "+ datoTipo+" id_Delete = 0;"); }
        if (datoTipo.equals("Double")){sb3.append( "        "+ datoTipo+" id_Delete = 0.0;"); }
     
        sb3.append("        try {"+"\r\n");
        sb3.append("          if (poder != null) {"+"\r\n");
        sb3.append("          if (poder.length() > 0 && !Pattern.matches(\"[a-zA-Z]+\", poder)) {"+"\r\n");
        
        if (datoTipo.equals("Long")) { sb3.append("         id_Delete = Long.parseLong(poder);"); }
        if (datoTipo.equals("Integer")){ sb3.append("       id_Delete = Integer.parseInt(poder);"); }
        if (datoTipo.equals("Double")){ sb3.append("        id_Delete = Double.parseDouble(poder);"); }
        
        sb3.append("            }"+"\r\n");
        sb3.append("        }"+"\r\n");
        sb3.append("            return id_Delete;"+"\r\n");
        sb3.append("        } catch (Exception e) {"+"\r\n");
        sb3.append("            e.printStackTrace();"+"\r\n");
        sb3.append("            return id_Delete;"+"\r\n");
        sb3.append("        }"+"\r\n");
        sb3.append("    }"+"\r\n");
      
        return sb3;
    }

    private StringBuffer metodovalidation(EntityPojo entidad){

        StringBuffer sb4 = new StringBuffer ();
        sb4.append("    public <T> Object validation(T t) {"+"\r\n");
        sb4.append("         T elemento = null;"+"\r\n");
        sb4.append("        try {"+"\r\n");
        sb4.append("        if (t != null) {"+"\r\n");
        sb4.append("            elemento = t;"+"\r\n");
        sb4.append("        }"+"\r\n");
        sb4.append("            return elemento;"+"\r\n");
        sb4.append("        } catch (Exception e) {"+"\r\n");
        sb4.append("            e.printStackTrace();"+"\r\n");
        sb4.append("            return elemento;"+"\r\n");
        sb4.append("        }"+"\r\n");
        sb4.append("    }"+"\r\n");
        return sb4;
    }
}

















