import { PaquetePackage } from "./paquete-package.model";
import { SubTask } from "./sub-task.model";
import { TaskNote } from "./task-note.model";

export class Task {

    private _taskId: number;
    private _time: number;
    private _state: string;
    private _solution: string;
    private _createDate: Date;
    private _endDate: Date;
    private _titleTask: string;          // requerd
    private _description: string;       // requerd
    private _taskType: string;          // requerd
    private _personCreate: string;      // requerd
    private _personWorked: string;      // requerd
    private _idProject: number;     // requerd
    private _taskNote: Array<TaskNote>;
    private _subTasks: Array<SubTask>;
    private _packages: Array<PaquetePackage>;
   
    constructor() {
        this._taskId = 0;
        this._titleTask= ''; 
        this._idProject = 0;
        this._state = '';
        this._time = 0;
        this._solution = '';
        this._createDate = new Date();
        this._endDate = new Date();
        this._taskType= ''; 
        this._description= ''; 
        this._personCreate= ''; 
        this._personWorked= '';
        this._packages= new Array<PaquetePackage>();
        this._taskNote = new Array<TaskNote>();
        this._subTasks = new Array<SubTask>();
    }


    public get packages(): Array<PaquetePackage> {
        return this._packages;
    }
    public set packages(value: Array<PaquetePackage>) {
        this._packages = value;
    }

    public get subTasks(): Array<SubTask> {
        return this._subTasks;
    }
    public set subTasks(value: Array<SubTask>) {
        this._subTasks = value;
    }

    public get taskNote(): Array<TaskNote> {
        return this._taskNote;
    }
    public set taskNote(value: Array<TaskNote>) {
        this._taskNote = value;
    }

    public get idProject(): number {
        return this._idProject;
    }
    public set idProject(value: number) {
        this._idProject = value;
    }

    public get personWorked(): string {
        return this._personWorked;
    }
    public set personWorked(value: string) {
        this._personWorked = value;
    }
    
    public get personCreate(): string {
        return this._personCreate;
    }
    public set personCreate(value: string) {
        this._personCreate = value;
    }

    public get taskType(): string {
        return this._taskType;
    }
    public set taskType(value: string) {
        this._taskType = value;
    }

    public get description(): string {
        return this._description;
    }
    public set description(value: string) {
        this._description = value;
    }

    public get titleTask(): string {
        return this._titleTask;
    }
    public set titleTask(value: string) {
        this._titleTask = value;
    }

    public get state(): string {
        return this._state;
    }
    public set state(value: string) {
        this._state = value;
    }
    public get taskId(): number {
        return this._taskId;
    }
    public set taskId(value: number) {
        this._taskId = value;
    }

    public get time(): number {
        return this._time;
    }
    public set time(value: number) {
        this._time = value;
    }

    public get solution(): string {
        return this._solution;
    }
    public set solution(value: string) {
        this._solution = value;
    }

    public get createDate(): Date {
        return this._createDate;
    }
    public set createDate(value: Date) {
        this._createDate = value;
    }
    public get endDate(): Date {
        return this._endDate;
    }
    public set endDate(value: Date) {
        this._endDate = value;
    }
}