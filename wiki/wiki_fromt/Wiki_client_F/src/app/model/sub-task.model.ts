import { PaquetePackage } from "./paquete-package.model";
import { TaskNote } from "./task-note.model";

export class SubTask{

        private idSubTask: number;
        private idProject: number;
        private taskReferenceId: number;
        private state: string;
        private time: number; 
        private solution: string;
        private createDate: Date;
        private endDate: Date;
        private titleSubTask: string;   //r
        private taskType: string; //r
        private description: string; //r
        private personCreate: string; //r
        private personWorked: string; //r
        private taskNote: Array<TaskNote>;
        private packages: Array<PaquetePackage>;
   
      
        constructor() {
            this.idSubTask = 0;
            this.idProject = 0;
            this.taskReferenceId = 0;
            this.state = '';
            this.time = 0;
            this.solution = '';
            this.createDate = new Date();
            this.endDate = new Date();
            this.titleSubTask= ''; 
            this.taskType= ''; 
            this.description= ''; 
            this.personCreate= ''; 
            this.personWorked= '';
            this.packages= new Array<PaquetePackage>();
            this.taskNote = new Array<TaskNote>();
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
        public get _description(): string {
            return this.description;
        }
        public set _description(value: string) {
            this.description = value;
        }
        public get _taskType(): string {
            return this.taskType;
        }
        public set _taskType(value: string) {
            this.taskType = value;
        }

        public get _titleSubTask(): string {
            return this.titleSubTask;
        }
        public set _titleSubTask(value: string) {
            this.titleSubTask = value;
        }
        
        public get _packages(): Array<PaquetePackage> {
            return this.packages;
        }
        public set _packages(value: Array<PaquetePackage>) {
            this.packages = value;
        }

        public get _taskNote(): Array<TaskNote> {
            return this.taskNote;
        }
        public set _taskNotes(value: Array<TaskNote>) {
            this.taskNote = value;
        }

        public get _endDate(): Date {
            return this.endDate;
        }
        public set _endDate(value: Date) {
            this.endDate = value;
        }

        public get _createDate(): Date {
            return this.createDate;
        }
        public set _createDate(value: Date) {
            this.createDate = value;
        }

        public get _solution(): string {
            return this.solution;
        }
        public set _solution(value: string) {
            this.solution = value;
        }

        public get _time(): number {
            return this.time;
        }
        public set _time(value: number) {
            this.time = value;
        }
        
        public get _state(): string {
            return this.state;
        }
        public set _state(value: string) {
            this.state = value;
        }

        public get _taskReferenceId(): number {
            return this.taskReferenceId;
        }
        public set _taskReferenceId(value: number) {
            this.taskReferenceId = value;
        }

        public get _idProject(): number {
            return this.idProject;
        }
        public set _idProject(value: number) {
            this.idProject = value;
        }
        public get _idSubTask(): number {
            return this.idSubTask;
        }
        public set _idSubTask(value: number) {
            this.idSubTask = value;
        }

      }


     