package com.generator.model.tempate.repositories;



import com.generator.core.build.interfaces.models.IModelBuilder;
import com.generator.core.build.models.ModelOup;
import com.generator.core.design.BodyMethodDesign;
import com.generator.core.design.ClassDesign;
import com.generator.core.design.reference.ClassType;
import com.generator.core.design.reference.Modifier;
import com.generator.core.format.formatter.Formatter;
import com.generator.core.pojos.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class TemplateRepository implements IModelBuilder {

    private Creador creador;
    private ArchivoBaseDatosPojo archivo;
    private String packageNames;
    private List<EntityPojo> entidades;
    private AnotacionesJava anotacionesJava = new AnotacionesJava();
    protected static final Log logger = LogFactory.getLog(TemplateRepository.class);
    private static TemplateRepository instance;

    private TemplateRepository() {
    }

    public static TemplateRepository getInstance(){
        if( instance == null){
            return new TemplateRepository();
        }else {
            return instance;
        }
    }


    @Override
    public Set<ModelOup> createModel(ArchivoBaseDatosPojo baseFilePojo, Creador creator) {

        Set<ModelOup> response = new HashSet<>();
        this.archivo = baseFilePojo;
        this.creador = creator;
        this.entidades = baseFilePojo.getEntidades();
        this.packageNames = baseFilePojo.getPackageNames();
        this.anotacionesJava.activateAnotacionesJava(archivo);

        if (this.entidades.size() > 0) {
            for (EntityPojo entidad : this.entidades) {
                if (entidad.getIsEntity()) {
                    try {
                        response.add(createRepository(entidad));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return response;
    }

    private ModelOup createRepository(EntityPojo entidad) throws InterruptedException {

        List<String> body = new ArrayList<>();
        String datoTipo = "";
        List<AttributePojo> listAtributos = entidad.getAtributos();
        String nameOfClass = entidad.getNombreClase() + "Repository";

        for (AttributePojo atributoID : listAtributos) {
            if (atributoID.getsId()) {
                datoTipo = atributoID.getTipoDato();
            }
        }


        for (AttributePojo atributos : listAtributos) {

            String cadenaOriginal = atributos.getAtributoName();
            String primeraLetra = cadenaOriginal.substring(0, 1).toUpperCase();
            String restoDeLaCadena = cadenaOriginal.substring(1);
            String atributoName = primeraLetra + restoDeLaCadena;

            if (atributos.getsId()) {
                String metodoSearch = "";
                if (this.archivo.checkAtributos(entidad)) {
                    metodoSearch = stringEnsamble(metodoSearch(entidad), "\r\n");
                }
                body.add(stringEnsamble("public ", entidad.getNombreClase(), " findBy", atributoName, "(" + atributos.getTipoDato(), " ", atributos.getAtributoName(), ");"));
                body.add(stringEnsamble("public List<", entidad.getNombreClase(), "> findBy", atributoName, "Containing(", atributos.getTipoDato(), " ", atributos.getAtributoName(), ");"));
                body.add(stringEnsamble("@Query(value = \"SELECT t FROM ", entidad.getNombreClase(), " t WHERE t.id =?1\")"));
                body.add(stringEnsamble(" public ", entidad.getNombreClase(), " findByIdQuery(", datoTipo, " id);"));
                body.add(metodoSearch);
            }

            if (!atributos.getsId()) {
                body.add(stringEnsamble("public ", entidad.getNombreClase(), " findBy", atributoName, "(", atributos.getTipoDato(), " ", atributos.getAtributoName(), ");"));
                body.add("\r\n");
                body.add(stringEnsamble("public List<", entidad.getNombreClase(), "> findBy", atributoName, "Containing(", atributos.getTipoDato(), " " + atributos.getAtributoName(), ");"));
                body.add("\r\n");
            }
        }

        body.add(AnotacionesJava.apacheSoftwareLicensed() + "\r\n");
//        imports.add("public interface "+nameOfClass+" extends JpaRepository< " + entidad.getNombreClase() + ", "+ datoTipo + "> { ");

        ClassDesign classTemplate = ClassDesign.builder()
                .packagePaht(archivo.getPackageNames())
                .packageName("repository")
                .imports(this.getImports(entidad))
                .annotation(List.of("@Repository"))
                .modifier(Modifier.Public)
                .classType(ClassType.INTERFACE)
                .className(stringEnsamble(nameOfClass))
                .classParameterClassMethods(null)
                .isClassIsImplement(false)
                .classImplement(null)
                .isClassIsInheritance(true)
                .classInheritance(stringEnsamble("JpaRepository< " + entidad.getNombreClase() + ", " + datoTipo + ">"))
                .content(new Formatter().simpleFormat(BodyMethodDesign.builder().bodyLines(body).build().toString()))
                .build();


        return ModelOup.builder()
                .packageNane("repository")
                .nameOfClass(nameOfClass)
                .classInString(classTemplate.toString())
                .directoryForJava(creador.directionForJava())
                .build();
    }


    private List<String> getImports(EntityPojo entidad) {
        List<String> imports = new ArrayList<>();

//        imports.add("package " + packageNames + ".repository;");
        imports.add(this.anotacionesJava.creatNotaClase().toString());
        imports.add("import java.util.List;");
        imports.add("import java.util.Date;");
        imports.add("import org.springframework.data.repository.CrudRepository;");
        imports.add("import org.springframework.data.jpa.repository.JpaRepository;");
        imports.add("import org.springframework.data.jpa.repository.Query;");
        imports.add("import org.springframework.data.repository.query.Param;");
        imports.add("import org.springframework.stereotype.Repository;");
        imports.add("import " + packageNames + "." + entidad.getPaquete() + "." + entidad.getNombreClase() + ";");
        return imports;
    }


    private String metodoSearch(EntityPojo entidad) {

        boolean concat = false;
        List<String> atributosName = new ArrayList<String>();
        StringBuffer search = new StringBuffer("@Query(value = \"SELECT p FROM " + entidad.getNombreClase() + " p WHERE CONCAT(");

        for (AttributePojo atributos : entidad.getAtributos()) {
            if (!atributos.getsId()) {
                atributosName.add(atributos.getAtributoName());
            }
        }

        for (int i = 0; i < atributosName.size(); i++) {
            if (atributosName.size() != (i + 1)) {
                search.append(" p." + atributosName.get(i) + ", ' ',");
                concat = true;
            } else {
                search.append(" p." + atributosName.get(i) + ") LIKE %?1%\")");
            }
        }

        search.append("\r\n");
        search.append("public List<" + entidad.getNombreClase() + "> finBySearch(String keyword);" + "\r\n");
        search.append("\r\n");
        return search.toString();
    }


}









 




