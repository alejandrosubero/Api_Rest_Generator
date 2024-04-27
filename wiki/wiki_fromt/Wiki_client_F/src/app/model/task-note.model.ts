

export class TaskNote {

    private idTaskNote: number;
    private note: string;
    private titleTaskNote: string;
    private addToDocument: boolean;
  
    constructor() {
        this.idTaskNote = 0; 
        this.note = ''; 
        this.titleTaskNote = ''; 
        this.addToDocument  = false;
    }
  
   
    get _idTaskNote(): number {
      return this.idTaskNote;
    }
  
    get _note(): string {
      return this.note;
    }
  
    get _titleTaskNote(): string {
      return this.titleTaskNote;
    }
  
    get _addToDocument(): boolean {
      return this.addToDocument;
    }
  
    set _idTaskNote(value: number) {
      this.idTaskNote = value;
    }
  
    set _note(value: string) {
      this.note = value;
    }
  
    set _titleTaskNote(value: string) {
      this.titleTaskNote = value;
    }
  
    set _addToDocument(value: boolean) {
      this.addToDocument = value;
    }

  }