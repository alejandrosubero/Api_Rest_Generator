package com.Generator.apirest.converter;


import com.Generator.apirest.pojos.back.EntityPojo;
import com.Generator.apirest.pojos.back.RelationshipPojo;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Scope("singleton")
@Component
public class ConvertEntityToModelT {

    private String model;
    private String modelM;


    protected static final Log logger = LogFactory.getLog(ConvertEntityToModelT.class);

    public List<EntityPojo> startConvertEntityToModelT(ArchivoBaseDatosPojo archivo) {

        List<EntityPojo> newList = new ArrayList<>();
        model = archivo.getCapaPojo().getModelT();
        modelM =  archivo.getCapaPojo().getModelM();

        for (EntityPojo entidad : archivo.getEntidades()) {

            logger.info(this.stringEnsamble(List.of("Create the entity: ",entidad+model.toUpperCase())));

            if (entidad.getIsEntity()) {
                EntityPojo dto = new EntityPojo();
                dto.setIsEntity(false);
                dto.setNombreClase(this.stringEnsamble(List.of(entidad.getNombreClase(),modelM)));
                dto.setNombreTabla(entidad.getNombreTabla());
                dto.setPaquete(model.toLowerCase());
                dto.setAtributos(entidad.getAtributos());
                dto.setRelaciones(convertRelacion(entidad.getRelaciones()));
                newList.add(dto);
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
            relationshipPojo.setNameClassRelacion(this.stringEnsamble(List.of(relacion.getNameClassRelacion(),modelM)));
            relationshipPojo.setNameClassRelacionar(this.stringEnsamble(List.of(relacion.getNameClassRelacionar(),modelM)));
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


    private String stringEnsamble(List<String> stringPaths) {
        StringBuilder newString = new StringBuilder();
        for (String part : stringPaths) {
            newString.append(part);
        }
        return newString.toString();
    }



}
