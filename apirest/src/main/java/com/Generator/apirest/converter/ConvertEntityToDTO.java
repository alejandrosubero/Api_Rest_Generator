package com.Generator.apirest.converter;


import com.Generator.apirest.pojos.back.EntidadesPojo;
import com.Generator.apirest.pojos.back.RelacionPojo;
import com.Generator.apirest.pojos.master.ArchivoBaseDatosPojo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Scope("singleton")
@Component
public class ConvertEntityToDTO {

    protected static final Log logger = LogFactory.getLog(ConvertEntityToDTO.class);

    public List<EntidadesPojo> startConvertEntityToDTO(ArchivoBaseDatosPojo archivo) {

        List<EntidadesPojo> newList = new ArrayList<>();
        for (EntidadesPojo entidad : archivo.getEntidades()) {
            logger.info("Se crea el pojo de la entidad  " +entidad+"DTO");
            if (entidad.getIsEntity()) {
                EntidadesPojo dto = new EntidadesPojo();
                dto.setIsEntity(false);
                dto.setNombreClase(entidad.getNombreClase() + "dto");
                dto.setNombreTabla(entidad.getNombreTabla());
                dto.setPaquete("dto");
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


    private List<RelacionPojo> convertRelacion(List<RelacionPojo> relacionList) {
        List<RelacionPojo> relacionPojoList = new ArrayList<>();
        for (RelacionPojo relacion : relacionList) {
            RelacionPojo relacionPojo = new RelacionPojo();
            relacionPojo.setNameClassRelacion(relacion.getNameClassRelacion()+"Dto");
            relacionPojo.setNameClassRelacionar(relacion.getNameClassRelacionar()+"Dto");
            relacionPojo.setMappedByRelacion(relacion.getMappedByRelacion());
            relacionPojo.setMappedBy(relacion.getMappedBy());
            relacionPojo.setBidireccional(relacion.getBidireccional());
            relacionPojo.setNameRelacion(relacion.getNameRelacion());
            relacionPojo.setRelation(relacion.getRelation());
            relacionPojo.setJoinColumn(relacion.getJoinColumn());
            relacionPojo.setFetchType(relacion.getFetchType());
            relacionPojo.setFetchTypes(relacion.getFetchTypes());
            relacionPojo.setJoinColumnName(relacion.getJoinColumnName());
            relacionPojo.setIsJoinTable(relacion.getIsJoinTable());
            relacionPojo.setJointabaleTipo(relacion.getJointabaleTipo());
            relacionPojo.setJoinColumnNameReferencedColumnName(relacion.getJoinColumnNameReferencedColumnName());
            relacionPojo.setJoinTableName(relacion.getJoinTableName());
            relacionPojo.setJoinColumnName2(relacion.getJoinColumnName2());
            relacionPojo.setCascadeType(relacion.getCascadeType());
            relacionPojo.setOrphanRemoval(relacion.getOrphanRemoval());
            relacionPojoList.add(relacionPojo);
        }
        return relacionPojoList;
    }




}
