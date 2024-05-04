import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-skills',
  template: `
     <section class="resume-section p-3 p-lg-5 d-flex align-items-center" id="skills">
          <div class="w-100">
            <h2 class="mb-5">Skills</h2>
            <ul>
                <li>{{ 'Skills1' | translate }}</li>
                <li>{{ 'Skills2' | translate }}</li>
                <li>{{ 'Skills3' | translate }}</li>
                <li>{{ 'Skills4' | translate }}</li>
                <li>{{ 'Skills5' | translate }}</li>
                <li>{{ 'Skills6' | translate }}</li>
                <li>{{ 'Skills7' | translate }}</li>
                <li>{{ 'Skills8' | translate }}</li>
                <li>{{ 'Skills9' | translate }}</li>
                <li>{{ 'Skills10' | translate }}</li>
                <li>{{ 'Skills11' | translate }}</li>
             </ul>
      
              </div>
            </section>
  `,
  styleUrls: ['./skills.component.css']
})
export class SkillsComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
