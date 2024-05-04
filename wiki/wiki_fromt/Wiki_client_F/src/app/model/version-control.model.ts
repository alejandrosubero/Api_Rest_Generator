export class VersionControl {

    private _idVersionControl: number;
    private _version: string;
    private _description: string;
    private _documentList: Array<Document>;
    
    constructor() {
        this._description = '';
        this._idVersionControl = 0;
        this._version = '';
        this._documentList = new Array<Document>();
    }

    public get idVersionControl(): number {
        return this._idVersionControl;
    }
    public set idVersionControl(value: number) {
        this._idVersionControl = value;
    }
    public get version(): string {
        return this._version;
    }
    public set version(value: string) {
        this._version = value;
    }

    public get description(): string {
        return this._description;
    }
    public set description(value: string) {
        this._description = value;
    }

    public get documentList(): Array<Document> {
        return this._documentList;
    }
    public set documentList(value: Array<Document>) {
        this._documentList = value;
    }

    public updateDocumentList(value:Document){
        this._documentList.push(value);
    }

}