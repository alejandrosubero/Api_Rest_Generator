import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-experiencia',
  template: `
    <section class="resume-section p-3 p-lg-5 d-flex justify-content-center" id="experience">
              <div class="w-100">
                <h2 class="mb-5">Experience</h2>

                <div class="resume-item d-flex flex-column flex-md-row justify-content-between mb-5">
                  <div class="resume-content">
                    <h3 class="mb-0">{{ 'app_experiencia_titulo_1' | translate }}</h3>
                    <div class="subheading mb-3">{{ 'app_experiencia_lugar_1' | translate }} <p class="subheading1">{{ 'app_experiencia_paias_1' | translate }}</p></div>
                    
                    <p>{{ 'app_experiencia_Descripcion_1' | translate }}</p>
                  </div>
                  <div class="resume-date text-md-right">
                    <span class="text-primary">{{ 'app_experiencia_Fecha_1' | translate }}</span>
                  </div>
                </div>

                <div class="resume-item d-flex flex-column flex-md-row justify-content-between mb-5">
                <div class="resume-content">
                  <h3 class="mb-0">{{ 'app_experiencia_titulo_2' | translate }}</h3>
                  <div class="subheading mb-3">{{ 'app_experiencia_lugar_2' | translate }} <p class="subheading1">{{ 'app_experiencia_paias_2' | translate }}</p></div>
                  
                  <p>{{ 'app_experiencia_Descripcion_2' | translate }}</p>
                </div>
                <div class="resume-date text-md-right">
                  <span class="text-primary">{{ 'app_experiencia_Fecha_2' | translate }}</span>
                </div>
              </div>

              <div class="resume-item d-flex flex-column flex-md-row justify-content-between mb-5">
              <div class="resume-content">
                <h3 class="mb-0">{{ 'app_experiencia_titulo_3' | translate }}</h3>
                <div class="subheading mb-3">{{ 'app_experiencia_lugar_3' | translate }} <p class="subheading1">{{ 'app_experiencia_paias_3' | translate }}</p></div>
                
                <p>{{ 'app_experiencia_Descripcion_3a' | translate }}</p>
                <p>{{ 'app_experiencia_Descripcion_3b' | translate }}</p>
                <p>{{ 'app_experiencia_Descripcion_3c' | translate }}</p>
              </div>
              <div class="resume-date text-md-right">
                <span class="text-primary">{{ 'app_experiencia_Fecha_3' | translate }}</span>
              </div>
            </div>


            <div class="resume-item d-flex flex-column flex-md-row justify-content-between mb-5">
            <div class="resume-content">
              <h3 class="mb-0">{{ 'app_experiencia_titulo_4' | translate }}</h3>
              <div class="subheading mb-3">{{ 'app_experiencia_lugar_4' | translate }} <p class="subheading1">{{ 'app_experiencia_paias_4' | translate }}</p></div>
              
              <p>{{ 'app_experiencia_Descripcion_4' | translate }}</p>
            </div>
            <div class="resume-date text-md-right">
              <span class="text-primary">{{ 'app_experiencia_Fecha_4' | translate }}</span>
            </div>
          </div>






              </div>
            </section>
  `,
  styleUrls: ['./experiencia.component.css']
})
export class ExperienciaComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}

/*
COTO Argentina (Enero 2020 – Actualidad)
 Cargo: Desarrollador Java
Descripción: Desarrollo y mantenimiento de software en java, angular y JavaScript en el grupo de Bpm.

Trabajos de desarrollador como FreeLance, (Noviembre 2018 – Diciembre 2018) Resumen de trabajos:
Desarrollado web:
Annittas Cake, pagina web con Css, framework fullpage’s, Bootstrap, implementando servicios gratuitos de envió de correos de Formspree.

Aplicaciones:
•	Aplicación de escritorio para envió de correos masivos, desarrollado con java utilizando el API de Java Mail de Java JEE, java swing y bases de datos en Mysql.
•	Aplicación para cálculo de costos de productos de pastelería, e inventario para Annittas Cake, con java, java swing, bases de datos Mysql.
•	Aplicación de escritorio para manejo de inventarios de almacén desarrollado en java, con swing, utilizando bases de datos relacional Mysql de forma local y remota.

Servicios Halliburton de Venezuela S.A (Halliburton) / Rubro de Servicios Petroleros. (Mayo-2007 – Marzo 2018) País: Venezuela
Cargo: Technical Engineer (S405-ESG-Tech Prof-Technical Serv) en el grupo de tecnología.
Descripción:  Funciones relacionadas a tecnologías informáticas, Realización de pruebas funcionales a softwares a implementar su uso en el país,  servicio técnico a equipos de pc y antenas satelitales.
Experiencia en el área de petróleos en el link: link del cv
*/