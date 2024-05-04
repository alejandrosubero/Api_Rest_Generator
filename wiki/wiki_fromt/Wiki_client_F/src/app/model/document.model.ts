export class Document {

    private _idDocument: number;
    private _title: string;
    private _content: string;
    private _reateDate: Date;
    private _docReferentNumber: string;
    private _referentVersion: string;
    private _actualVersion: string;
    private _active: boolean;
  
    constructor(){
        this._active = true;
        this._idDocument=0;
        this._actualVersion='';
        this._content='';
        this._docReferentNumber='';
        this._reateDate= new Date();
        this._title='';
        this._referentVersion='';
    }

    public get active(): boolean {
        return this._active;
    }
    public set active(value: boolean) {
        this._active = value;
    }
    public get actualVersion(): string {
        return this._actualVersion;
    }
    public set actualVersion(value: string) {
        this._actualVersion = value;
    }

    public get referentVersion(): string {
        return this._referentVersion;
    }
    public set referentVersion(value: string) {
        this._referentVersion = value;
    }
    public get docReferentNumber(): string {
        return this._docReferentNumber;
    }
    public set docReferentNumber(value: string) {
        this._docReferentNumber = value;
    }

    public get reateDate(): Date {
        return this._reateDate;
    }
    public set reateDate(value: Date) {
        this._reateDate = value;
    }
    public get content(): string {
        return this._content;
    }
    public set content(value: string) {
        this._content = value;
    }

    public get title(): string {
        return this._title;
    }
    public set title(value: string) {
        this._title = value;
    }

    public get idDocument(): number {
        return this._idDocument;
    }
    public set idDocument(value: number) {
        this._idDocument = value;
    }

}