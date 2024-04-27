export class PaquetePackage {

    private idPaquetePackage: number;
    private packageName: string;
   

    constructor() {
        this.idPaquetePackage = 0;
        this.packageName = '';
    }

    public get _idPaquetePackage(): number {
        return this.idPaquetePackage;
    }
    public set _idPaquetePackage(value: number) {
        this.idPaquetePackage = value;
    }

    public get _packageName(): string {
        return this.packageName;
    }
    public set _packageName(value: string) {
        this.packageName = value;
    }
}