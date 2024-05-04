export class PaquetePackage {

    private _idPaquetePackage: number;
    private _packageName: string;
   

    constructor() {
        this._idPaquetePackage = 0;
        this._packageName = '';
    }

    public get idPaquetePackage(): number {
        return this._idPaquetePackage;
    }
    public set idPaquetePackage(value: number) {
        this._idPaquetePackage = value;
    }

    public get packageName(): string {
        return this._packageName;
    }
    public set packageName(value: string) {
        this._packageName = value;
    }
}