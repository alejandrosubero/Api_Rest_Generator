export class VersionControl {

    private idVersionControl: number;
    private version: string;
    private description: string;
    private documentList: Array<Document>;
    
    constructor() {
        this.description = '';
        this.idVersionControl = 0;
        this.version = '';
        this.documentList = new Array<Document>();
    }

    public get _idVersionControl(): number {
        return this.idVersionControl;
    }
    public set _idVersionControl(value: number) {
        this.idVersionControl = value;
    }
    public get _version(): string {
        return this.version;
    }
    public set _version(value: string) {
        this.version = value;
    }

    public get _description(): string {
        return this.description;
    }
    public set _description(value: string) {
        this.description = value;
    }

    public get _documentList(): Array<Document> {
        return this.documentList;
    }
    public set _documentList(value: Array<Document>) {
        this.documentList = value;
    }

    public updateDocumentList(value:Document){
        this.documentList.push(value);
    }

}