package com.Generator.apirest.converter;



import com.Generator.apirest.core.pojos.back.EntityPojo;
import com.Generator.apirest.core.pojos.back.RelationshipPojo;
import com.Generator.apirest.core.pojos.ArchivoBaseDatosPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Scope("singleton")
@Component
public class ConvertEntityToPojo {

    protected static final Log logger = LogFactory.getLog(ConvertEntityToPojo.class);

    public List<EntityPojo> startConvertEntityToPojo(ArchivoBaseDatosPojo archivo) {

        List<EntityPojo> newList = new ArrayList<>();
        for (EntityPojo entidad : archivo.getEntidades()) {
            logger.info("Se crea el pojo de la entidad  " +entidad+"Pojo");
            if (entidad.getIsEntity()) {
                EntityPojo pojo = new EntityPojo();
                pojo.setIsEntity(false);
                pojo.setDelete(entidad.getDelete());
                pojo.setNombreClase(entidad.getNombreClase() + "Pojo");
                pojo.setNombreTabla(entidad.getNombreTabla());
                pojo.setPaquete("pojo");
                pojo.setAtributos(entidad.getAtributos());
                if (entidad.getRelaciones().size() > 0) {
                	  pojo.setRelaciones(convertRelacion(entidad.getRelaciones()));
                }
                newList.add(pojo);
                newList.add(entidad);
            } else {
                newList.add(entidad);
            }
        }
        return newList;
    }


    private List<RelationshipPojo> convertRelacion(List<RelationshipPojo> relacionList) {
        List<RelationshipPojo> relationshipPojoList = new ArrayList<>();
        for (RelationshipPojo relacion : relacionList) {
            RelationshipPojo relationshipPojo = new RelationshipPojo();
            relationshipPojo.setNameClassRelacion(relacion.getNameClassRelacion()+"Pojo");
            relationshipPojo.setNameClassRelacionar(relacion.getNameClassRelacionar()+"Pojo");
            relationshipPojo.setMappedByRelacion(relacion.getMappedByRelacion());
            relationshipPojo.setMappedBy(relacion.getMappedBy());
            relationshipPojo.setBidireccional(relacion.getBidireccional());
            relationshipPojo.setNameRelacion(relacion.getNameRelacion());
            relationshipPojo.setRelation(relacion.getRelation());
            relationshipPojo.setJoinColumn(relacion.getJoinColumn());
            relationshipPojo.setFetchType(relacion.getFetchType());
            relationshipPojo.setFetchTypes(relacion.getFetchTypes());
            relationshipPojo.setJoinColumnName(relacion.getJoinColumnName());
            relationshipPojo.setIsJoinTable(relacion.getIsJoinTable());
            relationshipPojo.setJointabaleTipo(relacion.getJointabaleTipo());
            relationshipPojo.setJoinColumnNameReferencedColumnName(relacion.getJoinColumnNameReferencedColumnName());
            relationshipPojo.setJoinTableName(relacion.getJoinTableName());
            relationshipPojo.setJoinColumnName2(relacion.getJoinColumnName2());
            relationshipPojo.setCascadeType(relacion.getCascadeType());
            relationshipPojo.setOrphanRemoval(relacion.getOrphanRemoval());
            relationshipPojoList.add(relationshipPojo);
        }
        return relationshipPojoList;
    }

}
