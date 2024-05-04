import { Task } from "./task.model";
import { VersionControl } from "./version-control.model";

export class Project {

    idProject: number;
    private _name: string;
    private _repositoryLink: string;
    private _startDate: Date;
    private _time: number;
    private _description: string;
    private _versionControlList: Array<VersionControl>;
    private _taskList: Array<Task>;


    constructor() {
        this.idProject = 0;
        this._name = '';
        this._repositoryLink = '';
        this._description = '';
        this._time = 0;
        this._versionControlList = new Array<VersionControl>();
        this._taskList = new Array<Task>();
        this._startDate = new Date();
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
    public get _idProject(): number {
        return this.idProject;
    }
    public set _idProject(value: number) {
        this.idProject = value;
    }

    public get description(): string {
        return this._description;
    }
    public set description(value: string) {
        this._description = value;
    }

    public get time(): number {
        return this._time;
    }
    public set time(value: number) {
        this._time = value;
    }

    public get versionControlList(): Array<VersionControl> {
        return this._versionControlList;
    }

    public set versionControlList(value: Array<VersionControl>) {
        this._versionControlList = value;
    }

    public updateVersionControlList(value:VersionControl){
        this.versionControlList.push(value);
    }

    public get taskList(): Array<Task> {
        return this._taskList;
    }
    public set taskList(value: Array<Task>) {
        this._taskList = value;
    }

    public updateTaskList(value:Task){
        this.taskList.push(value);
    }

}