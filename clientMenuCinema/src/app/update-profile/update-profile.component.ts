import { Component, OnInit } from '@angular/core';
import {ClientService} from '../client.service';
import {Client} from '../client-data/Client';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {HttpErrorResponse} from '@angular/common/http';

@Component({
  selector: 'app-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.scss','../../../node_modules/bootstrap/dist/css/bootstrap.min.css']
})
export class UpdateProfileComponent implements OnInit {
  isSubmited: boolean;
  updateCliForm: FormGroup;
  numRue: string;
  constructor(private cs: ClientService, private formBuilder: FormBuilder, private route: Router) {
    this.isSubmited = false;
    this.numRue = this.isNulltoString(this.client.adresse.numero);
    this.updateCliForm = this.formBuilder.group({
      nom: new FormControl(this.isNulltoString(this.client.nom), [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(20),
        Validators.pattern('(([A-Z])([A-Z]|[a-z]|\\s|-|à|â|ç|è|é|ê|ë|î|ï|ù|û|ü|ÿ)*)')
        ]),
      prenom: new FormControl(this.isNulltoString(this.client.prenom), [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(20),
        Validators.pattern('(([A-Z])([A-Z]|[a-z]|\\s|-|à|â|ç|è|é|ê|ë|î|ï|ù|û|ü|ÿ)*)')
      ]),
      tel: new FormControl(this.isNulltoString(this.client.tel), [
        Validators.required,
        Validators.pattern('^((\\+)33|0)[1-9](\\d{2}){4}$')
      ]),
      mail: new FormControl(this.isNulltoString(this.client.mail), [
        Validators.required,
        Validators.email,
        Validators.maxLength(40)
      ]),
      numero: new FormControl(this.isNulltoString(this.client.adresse.numero), [
        Validators.required,
        Validators.pattern('[0-9]{1,6}(bis|ter|quater)?'),
      ]),
      rue: new FormControl(this.isNulltoString(this.client.adresse.rue), [
        Validators.required,
        Validators.maxLength(50)
      ]),
      cp: new FormControl(this.isNulltoString(this.client.adresse.cp), [
        Validators.required,
        Validators.pattern('[0-9]{5,6}')
      ]),
      ville: new FormControl(this.isNulltoString(this.client.adresse.ville), [
        Validators.required,
        Validators.maxLength(30)
      ])
    });
  }

  hasValidationError(formControlName: string): boolean {
    return (this.isSubmited ? this.updateCliForm.get(formControlName).invalid : false);
  }

  getValidationError(formControlName: string, errorCode: string): boolean {
    return this.updateCliForm.get(formControlName).hasError(errorCode);
  }

  ngOnInit(): void {
  }

  get client(): Client {
    return this.cs.getClient();
  }

  isNulltoString(value: string): string {
    return value === 'null' ? '' : value;
  }
  onSubmit(infosClient) {
    this.isSubmited = true;
    const cli: Client = {
      id: this.cs.getClient().id,
      nom: infosClient.nom,
      prenom: infosClient.prenom,
      mail: infosClient.mail,
      tel: infosClient.tel,
      photo: this.cs.getClient().photo,
      adresse: {
        numero: infosClient.numero,
        rue: infosClient.rue,
        cp: infosClient.cp,
        ville: infosClient.ville
      }
    }
    if (!this.updateCliForm.invalid) {
      this.cs.POST('/api/update', {
        id: this.cs.getClient().id,
        nom: infosClient.nom,
        prenom: infosClient.prenom,
        mail: infosClient.mail,
        tel: infosClient.tel,
        photo: this.cs.getClient().photo,
        numero: infosClient.numero,
        rue: infosClient.rue,
        cp: infosClient.cp,
        ville: infosClient.ville
      }).then(
        (reponse) => {
          this.cs.setClient(cli);
          this.route.navigate(['/mon-compte']);
        },
        (error: HttpErrorResponse) => {
          console.log('Erreur : ' + error.message);
        }
      );
    }
  }
}
