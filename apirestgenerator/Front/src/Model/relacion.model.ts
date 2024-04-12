export class Relacion {

    mappedByRelacion: boolean;
    mappedBy: string; // a un no esta en uso
    bidireccional: boolean; /// no esta en uso aun
    nameClassRelacionar: string; // clase padre
    nameClassRelacion: string; // clase a relacionar con el padre
    nameRelacion: string;
    relation: string; // @OneToOne, @OneToMany, @ManyToOne, @OneToMany, @ManyToMany
    joinColumn: boolean;
    fetchType: boolean;
    fetchTypes: string; // FetchType.LAZY, FetchType.EAGER
    joinColumnName: string;
    isJoinTable: boolean;
    jointabaleTipo: boolean;
    joinColumnNameReferencedColumnName: string;
    JoinTableName: string;
    joinColumnName2: string;
    cascadeType: string; // all, delete, persist,
    orphanRemoval: boolean;


    constructor() {

        this.mappedByRelacion = false;
        this.bidireccional = false;
        this.joinColumn = false;
        this.fetchType = false;
        this.isJoinTable = false;
        this.jointabaleTipo = false;
        this.orphanRemoval = false;

        this.mappedBy = '';
        this.nameClassRelacion = '';
        this.nameRelacion = '';
        this.relation = '';
        this.fetchTypes = '';
        this.joinColumnName = '';
        this.joinColumnNameReferencedColumnName = '';
        this.JoinTableName = '';
        this.joinColumnName2 = '';

        this.nameClassRelacionar = '';
        this.cascadeType = 'ALL';

    }
}


