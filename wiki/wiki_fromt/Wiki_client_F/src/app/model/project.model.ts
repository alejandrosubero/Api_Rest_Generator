import { Task } from "./task.model";
import { VersionControl } from "./version-control.model";

export class Project {

    private idProject: number;
    private name: string;
    private repositoryLink: string;
    private description: string;
    private time: number;
    private versionControlList: Array<VersionControl>;
    private taskList: Array<Task>;
   

    constructor() {
        this.idProject = 0;
        this.name = '';
        this.repositoryLink = '';
        this.description = '';
        this.time = 0;
        this.versionControlList = new Array<VersionControl>();
        this.taskList = new Array<Task>();
    }


    public get _repositoryLink(): string {
        return this.repositoryLink;
    }
    public set _repositoryLink(value: string) {
        this.repositoryLink = value;
    }
    public get _name(): string {
        return this.name;
    }
    public set _name(value: string) {
        this.name = value;
    }
    public get _idProject(): number {
        return this.idProject;
    }
    public set _idProject(value: number) {
        this.idProject = value;
    }

    public get _description(): string {
        return this.description;
    }
    public set _description(value: string) {
        this.description = value;
    }

    public get _time(): number {
        return this.time;
    }
    public set _time(value: number) {
        this.time = value;
    }

    public get _versionControlList(): Array<VersionControl> {
        return this.versionControlList;
    }

    public set _versionControlList(value: Array<VersionControl>) {
        this.versionControlList = value;
    }

    public updateVersionControlList(value:VersionControl){
        this.versionControlList.push(value);
    }

    public get _taskList(): Array<Task> {
        return this.taskList;
    }
    public set _taskList(value: Array<Task>) {
        this.taskList = value;
    }

    public updateTaskList(value:Task){
        this.taskList.push(value);
    }

}