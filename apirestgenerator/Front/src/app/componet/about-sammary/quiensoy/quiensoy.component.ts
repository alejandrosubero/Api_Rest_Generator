import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-quiensoy',
  template: `
    <section class="resume-section p-3 p-lg-5 d-flex align-items-center" id="about">
              <div class="w-100">
                <h2 class="mb-0">
                  <span class="text-primary">Alejandro Subero</span>
                </h2>
                <h5>Argentina Buenos Aires Capital Federal, C.A.B.A</h5>
                <div class="subheading mb-3"> 
                  <p>(054) 11-537451-97. <a href="mailto:Alejandrosubero.ar@gmail.com">Alejandrosubero.ar@gmail.com</a></p>
                </div>
                <p class="lead mb-3"> {{ 'profecionalDescripcion' | translate }} </p>
              </div>
            </section>
  `,
  styleUrls: ['./quiensoy.component.css']
})
export class QuiensoyComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
