export class DeveloperDTO {
    name: string;
    lastName: string;

    constructor() {
        this.name = '';
        this.lastName = '';
    }

   public setUser( name: string, lastName: string) {
        this.name = name;
        this.lastName = lastName;
    }

    public getFullName(){
        return `${this.name} ${this.lastName}`; 
    }
}