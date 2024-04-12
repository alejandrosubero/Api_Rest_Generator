
export class Help {

    helpAll: string[];
    helpP: string[];
    helpA: string[];

    constructor() {

        this.helpAll = [
            '-g ---helpAll',
            '-g ---helpP',
            '-g ---helpA',
            '-g ---proyect --ProyectName',
            '-g ---entidad --entityName',
            '-g ---entidad --entityName --tableName',
            '-g ---pojo --pojoName',
            
            '-g ---g',  
            '-g ---G',  
            '-g ---generate', 
            '-g ---Generate', 
            '-g ---GENERATE', 
            '-g ---generar', 
            '-g ---Generar', 
            '-g ---GENERAR',  
            '-g ---d',  
            '-g ---D', 
            '-g ---download', 
            '-g ---Download', 
            '-g ---DOWNLOAD', 
            '-g ---descarga',  
            '-g ---Descarga', 
            '-g ---DESCARGA',

            '---add --entidad --entityNameactual --entityName --Etiname',
            '---add --entidad --entityNameactual --tableName --tablname',
            
            '---add --atributo --AtributoName --tipo Dato(string)',
            '---add --atributo --AtributoName1, AtributoName2, AtributoName3, tipo Dato(string), tipo Dato(int), tipo Dato(long)',
            '---add --relation --nameClassRelacionar  --nameRelacion  --relation',
            '---add --relation --nameClassRelacionar1, nameClassRelacionar, nameClassRelacionar, --nameRelacion1, nameRelacion2, nameRelacion3 --relation1, relation2, relation3',
            
            '---add --proyect --proyectoName --MYNAME',
            '---add --proyect --autor --IVAN',
            '---add --proyect --user --ANA',
            '---add --proyect --context --IVANCODE',
            '---add --proyect --packageNames --com.alejandro.codePrueba',
            '---add --proyect --description --prueba code',
            '---add --proyect --wihtSegurity --false',
            '---add --proyect --dataBase --true',
            '---add --proyect --databaseTest --true',
            '---add --proyect --nativeMysql --false',
            '---add --proyect --javaVersion --1.8',
            '---add --proyect --databaseName --code',
            '---add --proyect --tipoDatabase --1',
            '---add --proyect --createCapaPojoForEntitys --true',
            '---add --proyect --getPostCreateTool --true',
            '---add --proyect --archivosManamentTool --true'
        ];
       
        this.helpP = [
            '-g ---proyect --proyectoName  --autor  --user  --context  --packageNames  --description  --wihtSegurity --dataBase  --databaseTest  --nativeMysql --javaVersion  --databaseName  --tipoDatabase --createCapaPojoForEntitys  --getPostCreateTool   --archivosManamentTool',
            'ejemplo of proyect = -g  ---proyect --proyecto --aleja --ale --protex --com.protex.valor --valor del proyecto --false --true --true --false --1.8 --databaseName  --1 --true  --true --true',
            '-add --proyect --proyectoName --MYNAME',
            '-add --proyect --autor --IVAN',
            '-add --proyect --user --ANA',
            '-add --proyect --context --IVANCODE',
            '-add --proyect --packageNames --com.alejandro.codePrueba',
            '-add --proyect --description --prueba code',
            '-add --proyect --wihtSegurity --false',
            '-add --proyect --dataBase --true',
            '-add --proyect --databaseTest --true',
            '-add --proyect --nativeMysql --false',
            '-add --proyect --javaVersion --1.8',
            '-add --proyect --databaseName --code',
            '-add --proyect --tipoDatabase --1',
            '-add --proyect --createCapaPojoForEntitys --true',
            '-add --proyect --getPostCreateTool --true',
            '-add --proyect --archivosManamentTool --true'
        ];


    }
}