package com.Generator.apirest.modelo.back.java07;



import com.Generator.apirest.core.Creador;
import com.Generator.apirest.notas.AnotacionesJava;
import com.Generator.apirest.pojos.back.AttributePojo;
import com.Generator.apirest.pojos.back.EntityPojo;
import com.Generator.apirest.pojos.back.RelationshipPojo;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import com.Generator.apirest.services.builders.FileCreateService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

//@Scope("singleton")
@Component
public class CreateServiceImp07 {

	@Autowired
    FileCreateService fileCreateService;
	
	private ArchivoBaseDatosPojo archivo;
    private String proyectoName;
    private String packageNames;
    private List<EntityPojo> entidades;
    private Creador creador;
    private String barra = "";

    private AnotacionesJava anotacionesJava = new AnotacionesJava();

    protected static final Log logger = LogFactory.getLog(CreateServiceImp07.class);


    public void startCreacionImplement07(ArchivoBaseDatosPojo archivo, Creador creador) {
    	this.creador=creador;
        this.entidades = archivo.getEntidades();
        this.proyectoName = archivo.getProyectoName();
        this.packageNames = archivo.getPackageNames();
        this.archivo = archivo;
        this.anotacionesJava.activateAnotacionesJava(archivo);
        try {
            this.crearImplemet();
        } catch (InterruptedException e) {
            logger.error(" ERROR : " + e);
        }
    }


    private void crearImplemet() throws InterruptedException {
        for (EntityPojo entidad : entidades) {
            if (entidad.getIsEntity()) {
                logger.info("Inicia la creacion de  Implementacion de Servicio" + " Entidad Nombre: " + entidad.getNombreClase());
                this.createServiceImpl(entidad);

            }
        }
    }


    private void createServiceImpl(EntityPojo entidad) throws InterruptedException {

        StringBuilder sbh = new StringBuilder("\r\n");
        String entidadNombre = entidad.getNombreClase();
        String nameOfClass = entidad.getNombreClase() + "ServiceImplement";
        String repositorieName = entidad.getNombreClase() + "Repository";
        String repositorieNameOjecte = repositorieName.toLowerCase();
        String serviceName = entidad.getNombreClase() + "Service";
        sbh.append(this.anotacionesJava.creatNotaClase() + "\r\n");
        sbh.append(this.createImport(serviceName, repositorieName, entidad));
        sbh.append(this.createTitulo(nameOfClass, serviceName, repositorieName, repositorieNameOjecte));
        
        if(this.archivo.getMethodManager().isMethodFindByOrLoop()) {
        sbh.append(this.crearMetodoloop(entidad, repositorieNameOjecte));
        }
        
        sbh.append(this.metods(entidad, repositorieNameOjecte, entidadNombre));
        sbh.append(AnotacionesJava.apacheSoftwareLicensed() + "\r\n");
        
       fileCreateService.createFileClassJava(nameOfClass, "serviceImplement",sbh, this.creador.directionForJava());
      //  fileCreateService.createFileClassJavaNoAddres(nameOfClass, "serviceImplement",sbh);
    }



    public StringBuilder createImport(String serviceName, String repositorieName, EntityPojo entidad) {
        StringBuilder sb = new StringBuilder("\r\n");

        sb.append("package " + packageNames + ".serviceImplement ;\r\n");
        sb.append("\r\n");
        sb.append("import " + packageNames + ".service." + serviceName + ";\r\n");
        sb.append("import " + packageNames + ".repository." + repositorieName + ";\r\n");
        sb.append("import java.util.ArrayList;" + "\r\n");
        sb.append("import java.util.List;" + "\r\n");
        sb.append("import java.util.Date;" + "\r\n");
        sb.append("import org.apache.commons.logging.Log;" + "\r\n");
        sb.append("import org.apache.commons.logging.LogFactory;" + "\r\n");
        sb.append("import org.springframework.beans.factory.annotation.Autowired;" + "\r\n");
        sb.append("import org.springframework.dao.DataAccessException;" + "\r\n");
        sb.append("import org.springframework.stereotype.Service;" + "\r\n");
        sb.append("import " + packageNames + "." + entidad.getPaquete() + "." + entidad.getNombreClase() + ";" + "\r\n");

        for (RelationshipPojo relacion : entidad.getRelaciones()) {
            sb.append("import " + packageNames + "." + entidad.getPaquete() + "." + relacion.getNameClassRelacion() + ";" + "\r\n");
        }
        sb.append("\r\n");
        return sb;
    }


    private StringBuilder createTitulo(String nameOfClass, String serviceName, String repositorieName, String repositorieNameOjecte) {

        StringBuilder sb1 = new StringBuilder("\r\n");
        // logger.info("createServiceImplement" + " paso 3 clase titulo  for Entity:  " + nameOfClass);
        sb1.append("\r\n");
        sb1.append("\r\n");
        sb1.append("@Service" + "\r\n");
        sb1.append("public class " + nameOfClass + " implements " + serviceName + " {" + "\r\n");
        sb1.append("\r\n");
        sb1.append("protected static final Log logger = LogFactory.getLog(" + nameOfClass + ".class);");
        sb1.append("\r\n");
        sb1.append("@Autowired" + "\r\n");
        sb1.append("private " + repositorieName + " " + repositorieNameOjecte + ";" + "\r\n");
        return sb1;
    }


    private StringBuilder crearMetodoloop(EntityPojo entidad, String repositorieNameOjecte) {
        logger.info(" inicia la creacion del metodo loop for Entity:  " + entidad.getNombreClase());

        StringBuilder sbp = new StringBuilder("\r\n");
        List<AttributePojo> listAtributos = entidad.getAtributos();

        for (AttributePojo atributos : listAtributos) {
            int cont = 1;
            if (!atributos.getsId()) {
                String atributoName = atributos.getAtributoName().substring(0, 1).toUpperCase() + atributos.getAtributoName().substring(1);
                sbp.append("		@Override" + "\r\n");
                sbp.append("		public " + entidad.getNombreClase() + " findBy" + atributoName + "(" + atributos.getTipoDato() + " " + atributos.getAtributoName() + "){" + "\r\n");
                sbp.append("\r\n");
                sbp.append("		logger.info(\"Starting get" + entidad.getNombreClase() + "\");" + "\r\n");
                sbp.append("			" + entidad.getNombreClase() + " " + entidad.getNombreClase().toLowerCase() + "Entity = new " + entidad.getNombreClase() + "();" + "\r\n");
                String numeraly = String.valueOf(cont);
                sbp.append("		" + entidad.getNombreClase() + " fileOptional" + numeraly + " = " + repositorieNameOjecte + ".findBy" + atributoName + "(" + atributos.getAtributoName() + ");" + "\r\n");
                sbp.append("\r\n");
                sbp.append("		if (null != fileOptional" + numeraly + ") { " + "\r\n");
                String operacionu = "			" + entidad.getNombreClase().toLowerCase() + "Entity = fileOptional" + numeraly + ";" + "\r\n";
                String operacionElseu = "\r\n";
                sbp.append(this.metodTrycath(operacionu, operacionElseu));
                sbp.append("		}" + "\r\n");
                sbp.append("		return " + entidad.getNombreClase().toLowerCase() + "Entity;");
                sbp.append("	}" + "\r\n");
                cont += 1;
            }
        }
        return sbp;
    }


    private StringBuilder metods(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {

        StringBuilder sb = new StringBuilder("\r\n");
        logger.info("**createServiceImplement  metodos  for Entity:  " + entidad.getNombreClase() + "**");
        
        sb.append("\r\n");
        if(this.archivo.getMethodManager().isMethodgetAll()) {
        sb.append(metodgetAll(entidad, repositorieNameOjecte, entidadNombre));
        }
        
        sb.append("\r\n");
        if(this.archivo.getMethodManager().isMethodgetAll()) {
        sb.append(this.metodSave(entidad, repositorieNameOjecte, entidadNombre));
        }
        
        sb.append("\r\n");
        sb.append(this.metodDelete(entidad, repositorieNameOjecte, entidadNombre));
       
        
        sb.append("\r\n");
        if(this.archivo.getMethodManager().isMethodfindById()) {
        sb.append(this.metodfindById(entidad, repositorieNameOjecte, entidadNombre));
        }
        
        if(this.archivo.checkAtributos(entidad)) {
	        sb.append("\r\n");
	        sb.append(this.metodSearch(entidad, repositorieNameOjecte, entidadNombre));
        }
        
        sb.append("\r\n");
        if(this.archivo.getMethodManager().isMethodContaining()) {
        sb.append(this.metodContaining(entidad, repositorieNameOjecte, entidadNombre));
        }
        
        sb.append("\r\n");
        if(this.archivo.getMethodManager().isMethodContainingRelacion()) {
        sb.append(this.ContainingRelacion(entidad, repositorieNameOjecte, entidadNombre));
        }
        
        sb.append("\r\n");
        if(this.archivo.getMethodManager().isMethodContainingRelacionNoBiDirectional()) {
        sb.append(this.ContainingRelacionNoBiDirectional(entidad, repositorieNameOjecte, entidadNombre));
        }
        
        sb.append("\r\n");
        sb.append("}\r\n");
        return sb;
    }


    private StringBuilder metodgetAll(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {

        StringBuilder ty = new StringBuilder("\r\n");
        String getNombreClase = entidad.getNombreClase();
        ty.append("\r\n");
        ty.append("		@Override" + "\r\n");
        ty.append("		public List<" + getNombreClase + "> getAll" + getNombreClase + "(){");
        ty.append("\r\n");
        ty.append("		logger.info(\"Get allProyect\");" + "\r\n");
        ty.append("			List<" + getNombreClase + "> lista" + getNombreClase + " = " + repositorieNameOjecte + ".findAll();" + "\r\n");
        ty.append("			return lista" + getNombreClase + ";" + "\r\n");
        ty.append("}" + "\r\n");
        return ty;
    }


    private StringBuilder metodSave(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {

        StringBuilder sbg = new StringBuilder("\r\n");
        sbg.append("		@Override" + "\r\n");
        sbg.append("		public boolean saveOrUpdate" + entidad.getNombreClase() + "(" + entidad.getNombreClase() + " " + entidad.getNombreClase().toLowerCase() + "){" + "\r\n");
        sbg.append("		logger.info(\"Save Proyect\");" + "\r\n");
        sbg.append("\r\n");
        String operacion = "				" + repositorieNameOjecte + ".save(" + entidad.getNombreClase().toLowerCase() + ");" + "\r\n" + "				return true;\r\n";
        String operacionElse = "				return false;\r\n";
        sbg.append(this.metodTrycath(operacion, operacionElse));
        sbg.append("		}\r\n");
        return sbg;
    }


    private StringBuilder metodDelete(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {

        StringBuilder sbt = new StringBuilder("\r\n");

        if (entidad.getDelete()) {
            sbt.append("		@Override" + "\r\n");
            sbt.append("		public boolean delete" + entidad.getNombreClase() + "( " + this.idTipoDato(entidad) + " id){" + "\r\n");
            sbt.append("		logger.info(\"Delete Proyect\");" + "\r\n");
            sbt.append("		boolean clave = false;\r\n");
            sbt.append("\r\n");
            String operacionA = "				" + repositorieNameOjecte + ".delete(id);" + "\r\n" + "				clave = true;\r\n";
            String operacionElseA = "				clave = false;\r\n";
            sbt.append(this.metodTrycath(operacionA, operacionElseA));
            sbt.append("		return clave;\r\n");
            sbt.append("	}\r\n");
        }
        return sbt;
    }


    private StringBuilder metodfindById(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {
        StringBuilder sf = new StringBuilder("\r\n");
        sf.append("\r\n");
        sf.append("		@Override" + "\r\n");
        sf.append("		public " + entidad.getNombreClase() + " findById( " + this.idTipoDato(entidad) + " id){" + "\r\n");
        sf.append("				return  " + repositorieNameOjecte + ".findByIdQuery(id);" + "\r\n");
        sf.append("		}" + "\r\n");
        return sf;
    }

    
	    
    private StringBuilder metodSearch(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {
        StringBuilder sf = new StringBuilder("\r\n");
        sf.append("\r\n");
        sf.append("		@Override" + "\r\n");
        sf.append("		public List<" + entidad.getNombreClase() + "> search(String search ){" + "\r\n");
        sf.append("				return  " + repositorieNameOjecte + ".finBySearch(search);" + "\r\n");
        sf.append("		}" + "\r\n");
        return sf;
    }
    
    
    private StringBuilder ContainingRelacion(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {
        StringBuilder sbx = new StringBuilder();
        String getNombreClase = entidadNombre;
        String getNombre = entidad.getNombreClase().toLowerCase();
        logger.info("  " + entidad.getNombreClase());
        for (RelationshipPojo relacion : entidad.getRelaciones()) {
            if (relacion.getBidireccional()) {
                if (relacion.getRelation().equals("ManyToMany") || relacion.getRelation().equals("OneToMany")) {
                    this.Relacionx(entidad, entidadNombre, relacion);
                }
            }
        }
        return sbx;
    }


    private StringBuilder Relacionx(EntityPojo entidad, String entidadNombre, RelationshipPojo relacion) {
        StringBuilder sbw = new StringBuilder();
        String getNombreClase = entidadNombre;
        String getNombre = entidad.getNombreClase().toLowerCase();
        sbw.append("\r\n");
        sbw.append("		@Override" + "\r\n");
        sbw.append("		public List<" + getNombreClase + "> findBy" + relacion.getNameClassRelacion() + "Containing(" + relacion.getNameClassRelacion() + " " + relacion.getNameRelacion() + "){" + "\r\n");
        sbw.append("			logger.info(\"get all "+relacion.getNameClassRelacion() + "Containing(" + relacion.getNameRelacion()+"\");" + "\r\n");
        sbw.append(" 			List<" + getNombreClase + "> lista" + getNombreClase + " = new ArrayList<" + getNombreClase + ">();" + "\r\n");
        sbw.append("			for (" + getNombreClase + " " + getNombre + " : this.getAll" + getNombreClase + "()) {" + "\r\n");
        sbw.append("			for (" + relacion.getNameClassRelacion() + " " + relacion.getNameRelacion() + "x : " + getNombre + ".get" + relacion.getNameRelacion() + "()) { " + "\r\n");
        sbw.append("				if(" + getNombreClase + ".get" + relacion.getNameRelacion() + "().contains(" + relacion.getNameRelacion() + ".get" + relacion.getNameRelacion() + "())) {	" + "\r\n");
        sbw.append("					lista" + getNombreClase + ".add(" + getNombre + "x);	" + "\r\n");
        sbw.append("				}" + "\r\n");
        sbw.append("	  	 	}" + "\r\n");
        sbw.append("		}" + "\r\n");
        sbw.append("					return lista" + getNombreClase + ";	" + "\r\n");
        sbw.append("	}" + "\r\n");
        return sbw;
    }


    private StringBuilder ContainingRelacionNoBiDirectional(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {
        StringBuilder sv = new StringBuilder();
        for (RelationshipPojo relacion: entidad.getRelaciones()) {
            if (!relacion.getRelation().equals("ManyToMany") && !relacion.getRelation().equals("OneToMany")) {
                sv.append("\r\n");
                sv.append("			@Override" + "\r\n");
                sv.append("			public List<" + entidad.getNombreClase() + "> findByRelacion" + relacion.getNameClassRelacion() + "(" + relacion.getNameClassRelacion() + " " + relacion.getNameClassRelacion().toLowerCase() + "){" + "\r\n");
                sv.append("				List<" + entidad.getNombreClase() + "> lista" + entidad.getNombreClase() + " = new ArrayList<" + entidad.getNombreClase() + ">();" + "\r\n");
                sv.append("				for (" + entidad.getNombreClase() + " " + entidad.getNombreClase().toLowerCase() + " : this.getAll" + entidad.getNombreClase() + "()) {" + "\r\n");
                sv.append("					if(" + entidad.getNombreClase().toLowerCase() + ".get" + relacion.getNameRelacion() + "().equals" +  "(" + relacion.getNameClassRelacion().toLowerCase() + ")){" + "\r\n");
//                sv.append("					if(" + entidad.getNombreClase().toLowerCase() + ".get" + relacion.getNameRelacion() + "().equals" + relacion.getNameClassRelacion() + "(" + relacion.getNameClassRelacion().toLowerCase() + ")){" + "\r\n");
                sv.append("						lista" + entidad.getNombreClase() + ".add(" + entidad.getNombreClase().toLowerCase() + ");" + "\r\n");
                sv.append("					}" + "\r\n");
                sv.append("				}" + "\r\n");
                sv.append("				return lista" + entidad.getNombreClase() + ";" + "\r\n");
                sv.append("			}" + "\r\n");
            }

            if (relacion.getRelation().equals("ManyToMany") || relacion.getRelation().equals("OneToMany")) {
                String getNombreClase = entidad.getNombreClase();
                String getNombre = entidad.getNombreClase().toLowerCase();
                sv.append("\r\n");
                sv.append("\r\n");
                sv.append("		@Override" + "\r\n");
                sv.append("		public List<" + getNombreClase + "> findBy" + relacion.getNameClassRelacion() + "Containing(" + relacion.getNameClassRelacion() + " " + relacion.getNameRelacion() + "){" + "\r\n");
                sv.append("			logger.info(\"get all "+relacion.getNameClassRelacion() + "Containing(" + relacion.getNameRelacion()+"\");" + "\r\n");
                sv.append(" 			List<" + getNombreClase + "> lista" + getNombreClase + " = new ArrayList<" + getNombreClase + ">();" + "\r\n");
                sv.append("			for (" + getNombreClase + " " + getNombre + " : this.getAll" + getNombreClase + "()) {" + "\r\n");
                sv.append("				for (" + relacion.getNameClassRelacion() + " " + relacion.getNameRelacion() + "x : " + getNombre + ".get" + relacion.getNameRelacion() + "()) { " + "\r\n");
                sv.append("						if("+relacion.getNameRelacion()+"x.equals"+"(" + relacion.getNameRelacion() + ")) {"+ "\r\n");
//                sv.append("						if("+relacion.getNameRelacion()+"x.equals"+relacion.getNameClassRelacion()+"(" + relacion.getNameRelacion() + ")) {"+ "\r\n");
                sv.append("						lista" + getNombreClase + ".add(" + getNombre + ");	" + "\r\n");
                sv.append("				}" + "\r\n");
                sv.append("	  	 	}" + "\r\n");
                sv.append("		}" + "\r\n");
                sv.append("			return lista" + getNombreClase + ";	" + "\r\n");
                sv.append("	}" + "\r\n");
            }
        }
        return sv;
    }



    private StringBuilder metodContaining(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {
        StringBuilder sbx = new StringBuilder();
        String getNombreClase = entidadNombre;
       // String getNombre = entidad.getNombreClase().toLowerCase();
        List<AttributePojo> listAtributos = entidad.getAtributos();

        for (AttributePojo atributo : listAtributos) {
            String cadenaOriginal = atributo.getAtributoName();
            String primeraLetra = cadenaOriginal.substring(0, 1).toUpperCase();
            String restoDeLaCadena = cadenaOriginal.substring(1);
            String atributoName = primeraLetra + restoDeLaCadena;

            if (!atributo.getsId()) {
                sbx.append("\r\n");
                sbx.append("		@Override" + "\r\n");
                sbx.append("		public List<" + getNombreClase + "> findBy"+atributoName+"Containing(" + atributo.getTipoDato()+ " " + atributoName.toLowerCase()+"){" + "\r\n");
                sbx.append("			logger.info(\"get all "+getNombreClase + " donde "+atributoName+" Containing " + atributoName.toLowerCase()+"\");" + "\r\n");
                sbx.append(" 			List<"+getNombreClase+"> lista"+getNombreClase+" = new ArrayList<"+getNombreClase+">();"+ "\r\n");
                sbx.append("			lista"+getNombreClase+" = "+repositorieNameOjecte+".findBy"+atributoName +"Containing("+atributoName.toLowerCase()+");"+ "\r\n");
                sbx.append("  			return lista"+getNombreClase+";"+ "\r\n");
                sbx.append("		}" + "\r\n");
            }
        }
        return sbx;
    }



    private String idTipoDato(EntityPojo entidad) {
        List<AttributePojo> listAtributos = entidad.getAtributos();
        String datoTipo = "Integer";
        for (AttributePojo atributoID : listAtributos) {
            if (atributoID.getsId()) {
                datoTipo = atributoID.getTipoDato();
            }
        }
        return datoTipo;
    }


    private String metodTrycath(String operacion, String operacionElse) {
        StringBuilder sb2 = new StringBuilder("\r\n");
        sb2.append("				try {" + "\r\n");
        sb2.append(operacion);
        sb2.append("				} catch (DataAccessException e) {" + "\r\n");
        sb2.append("				logger.error(\" ERROR : \" + e);" + "\r\n");
        sb2.append(operacionElse);
        sb2.append("				}\r\n");
        return sb2.toString();
    }


    @SuppressWarnings("unused")
 	private StringBuilder metodSaveOrUpdate(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {
         StringBuilder sf = new StringBuilder("\r\n");
         String numeraly = String.valueOf(2);
         sf.append("\r\n");
         sf.append("		@Override" + "\r\n");
         sf.append("		public boolean saveOrUpdate" + entidad.getNombreClase() + "(" + entidad.getNombreClase() + "  " + entidad.getNombreClase().toLowerCase() + " ){" + "\r\n");
         sf.append("			logger.info(\"Update Proyect\");" + "\r\n");
         sf.append("			boolean clave = false;\r\n");
         sf.append("			    " + entidad.getNombreClase() + " fileOptional" + numeraly + " = " + repositorieNameOjecte + ".findByIdQuery(" + entidad.getNombreClase().toLowerCase() + ".getId());" + "\r\n");
         sf.append("			if (null != fileOptional" + numeraly + ") { " + "\r\n");
         sf.append("				clave = this.update" + entidad.getNombreClase() + "(" + entidad.getNombreClase().toLowerCase() + ");" + "\r\n");
         sf.append("				logger.info(\" is update\");" + "\r\n");
         sf.append("			} else {" + "\r\n");
         sf.append("					clave = this.save" + entidad.getNombreClase() + "(" + entidad.getNombreClase().toLowerCase() + ");" + "\r\n");
         sf.append("					logger.info(\" is save\");" + "\r\n");
         sf.append(" 				}" + "\r\n");
         sf.append(" 		return clave;" + "\r\n");
         sf.append("		}");
         return sf;
     }
    
    
    @SuppressWarnings("unused")
  	private StringBuilder metodUpdate(EntityPojo entidad, String repositorieNameOjecte, String entidadNombre) {
          StringBuilder sf = new StringBuilder("\r\n");
          sf.append("\r\n");
          sf.append("		@Override" + "\r\n");
          sf.append("		public boolean update" + entidad.getNombreClase() + "(" + entidad.getNombreClase() + "  " + entidad.getNombreClase().toLowerCase() + " ){" + "\r\n");
          sf.append("			logger.info(\"Update Proyect\");" + "\r\n");
          sf.append("			boolean clave = false;" + "\r\n");
          sf.append("		" + entidad.getNombreClase() + " empre = findById(" + entidad.getNombreClase().toLowerCase() + ".getId());" + "\r\n");
          sf.append("			empre = " + entidad.getNombreClase().toLowerCase() + ";" + "\r\n");
          String operacionc = "				" + repositorieNameOjecte + ".save(empre);" + "\r\n" + 	"						clave = true;" + "\r\n";
          String operacionElsec = "				clave = false;" + "\r\n";
          sf.append(this.metodTrycath(operacionc, operacionElsec));
          sf.append("\r\n");
          sf.append("					return clave;" + "\r\n");
          sf.append("	}\r\n");
          return sf;
      }
    
}
