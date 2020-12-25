import { Component, OnInit } from '@angular/core';
import {ClientService} from '../client.service';
import {MovieResponse} from '../tmdb-data/Movie';
import {Plat} from '../client-data/Plat';
import {Panier} from '../client-data/Panier';
import {PanierService} from '../panier.service';
import {MAT_RADIO_DEFAULT_OPTIONS} from '@angular/material/radio';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {Adresse} from '../client-data/Client';

@Component({
  selector: 'app-panier',
  templateUrl: './panier.component.html',
  styleUrls: ['./panier.component.scss'],
  providers: [{
    provide: MAT_RADIO_DEFAULT_OPTIONS,
    useValue: { color: 'accent' },
  }]
})
export class PanierComponent implements OnInit {
  panier: Panier;
  radioButtonChecking;
  adrFormGroup: FormGroup;
  isSubmited: boolean;

  constructor(private cs: ClientService, private panierService: PanierService, private fb: FormBuilder) {
    this.isSubmited = false;
    this.cs.routingNotConnected();
    this.panierService.panierObs.subscribe(
      (p) => {
        this.panier = p;
        this.panier.plats.forEach(
          (test) => {
            if (test.quantite < 1) {
              this.deletePlat(test.nom);
            }
          }
        );
      }
    );
    if (this.cliAdrIsDefined) {
      this.radioButtonChecking = 'cliAdr';
    } else {
      this.radioButtonChecking = 'customAdr';
    }

    this.adrFormGroup = this.fb.group({
      numero: new FormControl('', [
        Validators.required,
        Validators.pattern('[0-9]{1,6}(bis|ter|quater)?'),
      ]),
      rue: new FormControl('', [
        Validators.required,
        Validators.maxLength(50)
      ]),
      cp: new FormControl('', [
        Validators.required,
        Validators.pattern('[0-9]{5,6}')
      ]),
      ville: new FormControl('', [
        Validators.required,
        Validators.maxLength(30)
      ])
    });
  }

  hasValidationError(formControlName: string): boolean {
    return (this.isSubmited ? this.adrFormGroup.get(formControlName).invalid : false);
  }

  getValidationError(formControlName: string, errorCode: string): boolean {
    return this.adrFormGroup.get(formControlName).hasError(errorCode);
  }

  delIfZero(idPlat: string, quantite: number) {
    if (quantite === 0) {
      this.deletePlat(idPlat);
    }
  }

  passerCommande() {
    if (this.radioButtonChecking !== 'cliAdr') {
      this.isSubmited = true;
    }
    if (this.peutCommander) {
      let adress;
      if (this.radioButtonChecking === 'cliAdr') {
        adress = this.cs.getClient().adresse;
      } else {
        adress = this.adrFormGroup.value as Adresse;
      }
      this.panierService.passerCommande(adress);
    }
  }

  get domicileDisplay(): string {
    return this.cs.adrDisplay;
  }


  ngOnInit(): void {
  }

  deleteFilm(id: number) {
    this.panierService.removeFilm(id);
  }

  videPanier() {
    this.panierService.videPanier();
  }
  deletePlat(id: string) {
    this.panierService.removePlat(id);
  }
  get films(): MovieResponse[] {
    return this.panier.films;
  }

  get plats(): Plat[] {
    return this.panier.plats;
  }

  get prixPanier() {
    return this.panierService.prixPanier;
  }

  get isVide(): boolean {
      return (this.panier.films.length === 0 && this.panier.plats.length === 0);
  }

  updateRBVal(val: string) {
    this.radioButtonChecking = val;
  }

  get peutCommander(): boolean {
    let res = false;
    if (this.radioButtonChecking === 'cliAdr' && this.cliAdrIsDefined) {
      res = true;
    } else {
      if (this.adrFormGroup.valid)  {
       res = true;
    }
    }
    return (this.panierService.peutPasserCommandeFilmPlat()
      && this.panierService.peutPasserCommandeInfosClient()
    && res);
  }

  get peutCommanderFilmPlat(): boolean {
    return this.panierService.peutPasserCommandeFilmPlat();
  }

  get peutPasserCommandeInfosClient(): boolean {
    return this.panierService.peutPasserCommandeInfosClient();
  }

  get cliAdrIsDefined(): boolean {
    return this.cs.clientAdresseIsDefined();
  }

  get client() {
    return this.cs.getClient();
  }
}
