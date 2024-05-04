import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { EntidadService } from 'src/app/services/entidad.service';
import { Contac } from 'src/Model/contact.model';
import { ContactRecibeMail } from 'src/Model/contactRecibeMail.model';

@Component({
  selector: 'app-contactame',
  templateUrl: './contactame.component.html',
  styleUrls: ['./contactame.component.css']
})
export class ContactameComponent implements OnInit {

  meFormGroup: FormGroup;
  contacto:Contac;
  contacME: ContactRecibeMail = new ContactRecibeMail();
  
  // email = '';
  // nombre = '';
  // comentarios = '';

  constructor(
    private service: EntidadService,
    private _formBuilder: FormBuilder,
    private dialogRef: MatDialogRef<ContactameComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.builderform();
     }

  ngOnInit(): void { }


  builderform() {
    this.meFormGroup = this._formBuilder.group({
      email: ['', Validators.required],
      nombre: ['', Validators.required],
      comentarios: ['', Validators.required],
    });

    this.meFormGroup.valueChanges.subscribe(x => {
    //   console.log(this.meFormGroup.valid);
    });

   //  this.meFormGroup.get('email').valueChanges.subscribe();

  }



  /**TODO: MANEJO DE LAS RESPUESTAS DEL SERVICIO */
  send(){
    if(this.meFormGroup.valid){
      this.contacto = new Contac();
      this.contacto.email = this.meFormGroup.value.email;
      this.contacto.nombre = this.meFormGroup.value.nombre;
      this.contacto.comentarios = this.meFormGroup.value.comentarios;
      this.contacME.asunto = 'Contacto desde Ana Code Frond';
      
      this.contacME.mensage = JSON.stringify(this.contacto);
  
      this.service.postContactMe(this.contacME).subscribe(x=>{
        console.log('respuesta: ', x);
      }, error => {
        console.log('Error Ocurre: ', error);
      });
      this.dialogRef.close();
    }else{
      console.log('Para enviar un contacto todos los campos deben de estar con valores');
    }
    
  }


  close(){
    this.dialogRef.close();
  }

  emailValidator(control) {
    var EMAIL_REGEXP = /^[a-z0-9!#$%&'*+\/=?^_`{|}~.-]+@[a-z0-9]([a-z0-9-]*[a-z0-9])?(\.[a-z0-9]([a-z0-9-]*[a-z0-9])?)*$/i;

    if (!EMAIL_REGEXP.test(control.value)) {
      return {invalidEmail: true};
    }
}

}
