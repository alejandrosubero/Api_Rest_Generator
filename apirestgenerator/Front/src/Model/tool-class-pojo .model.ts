export class ToolClassPojo {

      getPostCreateTool: boolean;
      archivosManamentTool: boolean;
      serverTcp: boolean;
      serverUdp: boolean;
      converterHex: boolean;
      socketClientTcp: boolean;
      socketClientUdp: boolean;
      leerExcel: boolean;
      crearExcel: boolean;

      constructor(){
        this.getPostCreateTool = false;
        this.archivosManamentTool= false;
        this.serverTcp = false;
        this.serverUdp = false;
        this.converterHex = false;
        this.socketClientTcp = false;
        this.socketClientUdp = false;
        this.leerExcel= false;
        this.crearExcel= false;
      }
}