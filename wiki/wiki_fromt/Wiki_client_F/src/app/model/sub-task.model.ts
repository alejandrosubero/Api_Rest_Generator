import { PaquetePackage } from "./paquete-package.model";
import { TaskNote } from "./task-note.model";

export class SubTask{

        private _idSubTask: number;
        private _idProject: number;
        private _taskReferenceId: number;
        private _state: string;
        private _time: number; 
        private _solution: string;
        private _createDate: Date;
        private _endDate: Date;
        private _titleSubTask: string;   //r
        private _taskType: string; //r
        private _description: string; //r
        private _personCreate: string; //r
        private _personWorked: string; //r
        private _packages: Array<PaquetePackage>;
        private _taskNote: Array<TaskNote>;
   
      
        constructor() {
            this._idSubTask = 0;
            this._idProject = 0;
            this._taskReferenceId = 0;
            this._state = '';
            this._time = 0;
            this._solution = '';
            this._createDate = new Date();
            this._endDate = new Date();
            this._titleSubTask= ''; 
            this._taskType= ''; 
            this._description= ''; 
            this._personCreate= ''; 
            this._personWorked= '';
            this._packages= new Array<PaquetePackage>();
            this._taskNote = new Array<TaskNote>();
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
        public get description(): string {
            return this._description;
        }
        public set description(value: string) {
            this._description = value;
        }
        public get taskType(): string {
            return this._taskType;
        }
        public set taskType(value: string) {
            this._taskType = value;
        }

        public get titleSubTask(): string {
            return this._titleSubTask;
        }
        public set titleSubTask(value: string) {
            this._titleSubTask = value;
        }
        
        public get packages(): Array<PaquetePackage> {
            return this._packages;
        }
        public set packages(value: Array<PaquetePackage>) {
            this._packages = value;
        }

        public get taskNote(): Array<TaskNote> {
            return this._taskNote;
        }
        public set taskNotes(value: Array<TaskNote>) {
            this._taskNote = value;
        }

        public get endDate(): Date {
            return this._endDate;
        }
        public set endDate(value: Date) {
            this._endDate = value;
        }

        public get createDate(): Date {
            return this._createDate;
        }
        public set createDate(value: Date) {
            this._createDate = value;
        }

        public get solution(): string {
            return this._solution;
        }
        public set solution(value: string) {
            this._solution = value;
        }

        public get time(): number {
            return this._time;
        }
        public set time(value: number) {
            this._time = value;
        }
        
        public get state(): string {
            return this._state;
        }
        public set state(value: string) {
            this._state = value;
        }

        public get taskReferenceId(): number {
            return this._taskReferenceId;
        }
        public set taskReferenceId(value: number) {
            this._taskReferenceId = value;
        }

        public get idProject(): number {
            return this._idProject;
        }
        public set idProject(value: number) {
            this._idProject = value;
        }
        public get idSubTask(): number {
            return this._idSubTask;
        }
        public set idSubTask(value: number) {
            this._idSubTask = value;
        }

      }


     