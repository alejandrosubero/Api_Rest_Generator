export class Document {

    private idDocument: number;
    private title: string;
    private content: string;
    private reateDate: Date;
    private docReferentNumber: string;
    private referentVersion: string;
    private actualVersion: string;
    private active: boolean;
  
    constructor(){
        this.active = true;
        this.idDocument=0;
        this.actualVersion='';
        this.content='';
        this.docReferentNumber='';
        this.reateDate= new Date();
        this.title='';
        this.referentVersion='';
    }

    public get _active(): boolean {
        return this.active;
    }
    public set _active(value: boolean) {
        this.active = value;
    }
    public get _actualVersion(): string {
        return this.actualVersion;
    }
    public set _actualVersion(value: string) {
        this.actualVersion = value;
    }

    public get _referentVersion(): string {
        return this.referentVersion;
    }
    public set _referentVersion(value: string) {
        this.referentVersion = value;
    }
    public get _docReferentNumber(): string {
        return this.docReferentNumber;
    }
    public set _docReferentNumber(value: string) {
        this.docReferentNumber = value;
    }

    public get _reateDate(): Date {
        return this.reateDate;
    }
    public set _reateDate(value: Date) {
        this.reateDate = value;
    }
    public get _content(): string {
        return this.content;
    }
    public set _content(value: string) {
        this.content = value;
    }

    public get _title(): string {
        return this.title;
    }
    public set _title(value: string) {
        this.title = value;
    }

    public get _idDocument(): number {
        return this.idDocument;
    }
    public set _idDocument(value: number) {
        this.idDocument = value;
    }

}