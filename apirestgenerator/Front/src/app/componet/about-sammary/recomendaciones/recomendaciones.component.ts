import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-recomendaciones',
  template: `
   

    <section class="resume-section p-3 p-lg-5 d-flex align-items-center" id="skills">
    <div class="w-100">
       <h2 class="mb-5">{{ 'Competences0' | translate }}</h2>
      <ul>
          <li>{{ 'Competences1' | translate }}</li>
          <li>{{ 'Competences2' | translate }}</li>
          <li>{{ 'Competences3' | translate }}</li>
          <li>{{ 'Competences4' | translate }}</li>
       </ul>

        </div>
      </section>



<section class="resume-section p-3 p-lg-5 d-flex align-items-center" id="awards">
              <div class="w-100">
                <h2 class="mb-5">{{ 'Personalreferences' | translate }}</h2>
               
                <h5 class="mb-5 mt-1"> Pablo Copa   email: pablo.copa@sdc.com.ar  Teléfono celular: +54 9 249 434-9046. </h5>
              </div>
            </section>

  `,
  styleUrls: ['./recomendaciones.component.css']
})
export class RecomendacionesComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
