

export class TaskNote {

    private _idTaskNote: number;
    private _note: string;
    private _titleTaskNote: string;
    private _addToDocument: boolean;
  
    constructor() {
        this._idTaskNote = 0; 
        this._note = ''; 
        this._titleTaskNote = ''; 
        this._addToDocument  = false;
    }
  
   
    get idTaskNote(): number {
      return this._idTaskNote;
    }
  
    get note(): string {
      return this._note;
    }
  
    get titleTaskNote(): string {
      return this._titleTaskNote;
    }
  
    get addToDocument(): boolean {
      return this._addToDocument;
    }
  
    set idTaskNote(value: number) {
      this._idTaskNote = value;
    }
  
    set note(value: string) {
      this._note = value;
    }
  
    set titleTaskNote(value: string) {
      this._titleTaskNote = value;
    }
  
    set addToDocument(value: boolean) {
      this._addToDocument = value;
    }

  }