import { Entidades } from 'src/Model/entidad.model';
import { Atributo } from 'src/Model/atributo.model';
import { Relacion } from 'src/Model/relacion.model';
import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class PojoToEntityServices {

    generatePojosOfEntity(isEntity: boolean, nombreClase: string, nombreTabla: string,
                          atributos: Array<Atributo>, relaciones: Array<Relacion>): Entidades {
        const en = new Entidades();
        en.isEntity = false;
        en.nombreClase = nombreClase + 'Pojo';
        en.nombreTabla = nombreTabla;
        en.paquete = 'pojo';
        en.atributos = atributos;
        en.relaciones = this.generarPojosRelaciones(relaciones);
        return en;
    }

    generarPojosRelaciones(relaciones: Array<Relacion>) {
        let relac: Array<Relacion> = new Array<Relacion>();
        relaciones.forEach(x => {
            let relax: Relacion = new Relacion();
            relax = this.generarPojosRelacion(x.mappedByRelacion, x.mappedBy,
                x.bidireccional, x.nameClassRelacionar, x.nameClassRelacion,
                x.nameRelacion, x.relation, x.joinColumn, x.fetchType, x.fetchTypes,
                x.joinColumnName, x.isJoinTable, x.jointabaleTipo, x.joinColumnNameReferencedColumnName,
                x.JoinTableName, x.joinColumnName2, x.cascadeType, x.orphanRemoval);
            relac.push(relax);
        });
        return relac;
    }


    generarPojosRelacion(mappedByRelacion: boolean,
                         mappedBy: string,
                         bidireccional: boolean,
                         nameClassRelacionar: string,
                         nameClassRelacion: string,
                         nameRelacion: string,
                         relation: string,
                         joinColumn: boolean,
                         fetchType: boolean,
                         fetchTypes: string,
                         joinColumnName: string,
                         isJoinTable: boolean,
                         jointabaleTipo: boolean,
                         joinColumnNameReferencedColumnName: string,
                         JoinTableName: string,
                         joinColumnName2: string,
                         cascadeType: string,
                         orphanRemoval: boolean): Relacion {

        let rela: Relacion = new Relacion();
        rela.mappedByRelacion = mappedByRelacion;
        rela.mappedBy = mappedBy;
        rela.bidireccional = bidireccional;
        rela.nameClassRelacionar = nameClassRelacionar + 'Pojo';
        rela.nameClassRelacion = nameClassRelacion + 'Pojo';
        rela.nameRelacion = nameRelacion;
        rela.relation = relation;
        rela.joinColumn = joinColumn;
        rela.fetchType = fetchType;
        rela.fetchTypes = fetchTypes;
        rela.joinColumnName = joinColumnName;
        rela.isJoinTable = isJoinTable;
        rela.jointabaleTipo = jointabaleTipo;
        rela.joinColumnNameReferencedColumnName = joinColumnNameReferencedColumnName;
        rela.JoinTableName = JoinTableName;
        rela.joinColumnName2 = joinColumnName2;
        rela.cascadeType = cascadeType;
        rela.orphanRemoval = orphanRemoval;
        return rela;
    }


}