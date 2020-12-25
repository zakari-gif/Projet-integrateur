import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './login/login.component';
import {MonCompteComponent} from './mon-compte/mon-compte.component';
import {ListeFilmsComponent} from './liste-films/liste-films.component';
import {DetailsFilmComponent} from './details-film/details-film.component';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {PanierComponent} from './panier/panier.component';
import {UpdateProfileComponent} from './update-profile/update-profile.component';
import {ListePlatsComponent} from './liste-plats/liste-plats.component';
import {MesCommandesComponent} from './mes-commandes/mes-commandes.component';

const routes: Routes = [
  { path: '', component: LoginComponent},
  { path: 'films', component: ListeFilmsComponent},
  { path: 'carte', component: ListePlatsComponent},
  { path: 'mon-compte', component: MonCompteComponent},
  { path: 'film/:id', component: DetailsFilmComponent},
  { path: 'panier', component: PanierComponent},
  { path: 'mon-compte/modifier', component: UpdateProfileComponent},
  { path: 'mes-commandes', component: MesCommandesComponent},
  { path: '404', component: PageNotFoundComponent},
  { path: '**', redirectTo: '/404'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
