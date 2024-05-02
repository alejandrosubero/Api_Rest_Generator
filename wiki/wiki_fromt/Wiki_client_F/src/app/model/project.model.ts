import { Task } from "./task.model";
import { VersionControl } from "./version-control.model";

export class Project {

     idProject: number;
    private  _name: string;
    private  _repositoryLink: string;
    private _startDate: Date;
     description: string;
     time: number;
     versionControlList: Array<VersionControl>;
     taskList: Array<Task>;
   

    constructor() {
        this.idProject = 0;
        this._name = '';
        this._repositoryLink = '';
        this.description = '';
        this.time = 0;
        this.versionControlList = new Array<VersionControl>();
        this.taskList = new Array<Task>();
        this._startDate= new Date();
    }

    public get startDate(): Date {
        return this._startDate;
    }
    public set startDate(value: Date) {
        this._startDate = value;
    }

    public get repositoryLink(): string {
        return this._repositoryLink;
    }
    public set repositoryLink(value: string) {
        this._repositoryLink = value;
    }
    public get name(): string {
        return this._name;
    }
    public set name(value: string) {
        this._name = value;
    }
    // public get _idProject(): number {
    //     return this.idProject;
    // }
    // public set _idProject(value: number) {
    //     this.idProject = value;
    // }

    // public get _description(): string {
    //     return this.description;
    // }
    // public set _description(value: string) {
    //     this.description = value;
    // }

    // public get _time(): number {
    //     return this.time;
    // }
    // public set _time(value: number) {
    //     this.time = value;
    // }

    // public get _versionControlList(): Array<VersionControl> {
    //     return this.versionControlList;
    // }

    // public set _versionControlList(value: Array<VersionControl>) {
    //     this.versionControlList = value;
    // }

    // public updateVersionControlList(value:VersionControl){
    //     this.versionControlList.push(value);
    // }

    // public get _taskList(): Array<Task> {
    //     return this.taskList;
    // }
    // public set _taskList(value: Array<Task>) {
    //     this.taskList = value;
    // }

    // public updateTaskList(value:Task){
    //     this.taskList.push(value);
    // }

}