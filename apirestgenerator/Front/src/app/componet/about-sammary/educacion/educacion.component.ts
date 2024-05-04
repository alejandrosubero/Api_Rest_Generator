import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-educacion',
  template: `
     <section class="resume-section p-3 p-lg-5 d-flex align-items-center" id="education">
              <div class="w-100">
                <h3 class="mb-5">Education</h3>

                <div class="resume-item d-flex flex-column flex-md-row justify-content-between">
                  <div class="resume-content">
                    <h4 class="mb-0">Universidad de Oriente</h4>
                    <h5 class="mb-0">Núcleo Monagas en Venezuela</h5>
                    <div class="subheading mb-3">Ingeniería de Petróleo</div>
                  </div>
                  <div class="resume-date text-md-right">
                    <span class="text-primary">Graduado en el año 2007</span>
                  </div>
                </div>
                <!-- <h3 class="text_titulo">Formación Complementaria:</h3> -->
                <div class="resume-item d-flex flex-column flex-md-row justify-content-between">
                  <div class="resume-content">
                    <h4 class="mb-0">Instituto Capacitech Fundesco</h4>
                    <h5 class="mb-0">Argentina Buenos Aires</h5>
                    <div class="subheading mb-3">Programación en Java</div>
                    <p> Duración 100 horas</p>
                  </div>
                  <div class="resume-date text-md-right">
                    <span class="text-primary">Marzo 2019 – Julio 2019</span>
                  </div>
                </div>
                 <div>
                      <div class="resume-item d-flex flex-column flex-md-row justify-content-between">
                          <div class="resume-content">
                                <h4 class="mb-0">NEORIS Argentina Buenos Aires</h4>
                                <div class="subheading mb-3">LAB JAVA de Programación</div>
                                <p>Duración 80 horas - Tenas: Java J2E, JEE, Spring Boot, Anglar</p>
                          </div>
                          <div class="resume-date text-md-right">
                                <span class="text-primary">Septiembre 2019</span>
                          </div>
                      </div>
                      <div class="resume-item d-flex flex-column flex-md-row justify-content-between">
                          <div class="resume-content">
                                <h4 class="mb-0">Bionnix Argentina Buenos Aires</h4>
                                <div class="subheading mb-3">Curso de HTML, CSS en Bionnix Academy</div>
                                <p>Duración 10 horas</p>
                          </div>
                          <!-- <div class="resume-date text-md-right">
                                <span class="text-primary">Septiembre 2019</span>
                          </div> -->
                      </div>
                      <div class="resume-item d-flex flex-column flex-md-row justify-content-between">
                          <div class="resume-content">
                                <h4 class="mb-0">Bionnix Argentina Buenos Aires</h4>
                                <div class="subheading mb-3">Curso de Software Tester QA en Bionnix Academy</div>
                                <p>Duración 8 horas</p>
                          </div>
                          <!-- <div class="resume-date text-md-right">
                                <span class="text-primary">Septiembre 2019</span>
                          </div> -->
                      </div>
                  </div>
                  
              </div>
            </section>
  `,
  styleUrls: ['./educacion.component.css']
})
export class EducacionComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
