export interface Client {
  id: string;
  mail: string;
  nom: string;
  prenom: string;
  tel: string;
  photo: string;
  adresse?: Adresse;
}

export interface Adresse {
  ville: string;
  cp: string;
  rue: string;
  numero: string;
}

