import { PaquetePackage } from "./paquete-package.model";
import { SubTask } from "./sub-task.model";
import { TaskNote } from "./task-note.model";

export class Task {

    private taskId: number;
    private time: number;
    private state: string;
    private solution: string;
    private createDate: Date;
    private endDate: Date;
    private titleTask: string;          // requerd
    private description: string;       // requerd
    private taskType: string;          // requerd
    private personCreate: string;      // requerd
    private personWorked: string;      // requerd
    private idProject: number;     // requerd
    private taskNote: Array<TaskNote>;
    private subTasks: Array<SubTask>;
    private packages: Array<PaquetePackage>;
   
    constructor() {
        this.taskId = 0;
        this.titleTask= ''; 
        this.idProject = 0;
        this.state = '';
        this.time = 0;
        this.solution = '';
        this.createDate = new Date();
        this.endDate = new Date();
        this.taskType= ''; 
        this.description= ''; 
        this.personCreate= ''; 
        this.personWorked= '';
        this.packages= new Array<PaquetePackage>();
        this.taskNote = new Array<TaskNote>();
        this.subTasks = new Array<SubTask>();
    }


    public get _packages(): Array<PaquetePackage> {
        return this.packages;
    }
    public set _packages(value: Array<PaquetePackage>) {
        this.packages = value;
    }

    public get _subTasks(): Array<SubTask> {
        return this.subTasks;
    }
    public set _subTasks(value: Array<SubTask>) {
        this.subTasks = value;
    }

    public get _taskNote(): Array<TaskNote> {
        return this.taskNote;
    }
    public set _taskNote(value: Array<TaskNote>) {
        this.taskNote = value;
    }

    public get _idProject(): number {
        return this.idProject;
    }
    public set _idProject(value: number) {
        this.idProject = value;
    }

    public get _personWorked(): string {
        return this.personWorked;
    }
    public set _personWorked(value: string) {
        this.personWorked = value;
    }
    
    public get _personCreate(): string {
        return this.personCreate;
    }
    public set _personCreate(value: string) {
        this.personCreate = value;
    }

    public get _taskType(): string {
        return this.taskType;
    }
    public set _taskType(value: string) {
        this.taskType = value;
    }

    public get _description(): string {
        return this.description;
    }
    public set _description(value: string) {
        this.description = value;
    }

    public get _titleTask(): string {
        return this.titleTask;
    }
    public set _titleTask(value: string) {
        this.titleTask = value;
    }

    public get _state(): string {
        return this.state;
    }
    public set _state(value: string) {
        this.state = value;
    }
    public get _taskId(): number {
        return this.taskId;
    }
    public set _taskId(value: number) {
        this.taskId = value;
    }

    public get _time(): number {
        return this.time;
    }
    public set _time(value: number) {
        this.time = value;
    }

    public get _solution(): string {
        return this.solution;
    }
    public set _solution(value: string) {
        this.solution = value;
    }

    public get _createDate(): Date {
        return this.createDate;
    }
    public set _createDate(value: Date) {
        this.createDate = value;
    }
    public get _endDate(): Date {
        return this.endDate;
    }
    public set _endDate(value: Date) {
        this.endDate = value;
    }
}