import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {TmdbService} from './tmdb.service';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AngularFireModule } from '@angular/fire';
import { AngularFirestoreModule } from '@angular/fire/firestore';
import {environment} from '../environments/environment';
import {AngularFireAnalyticsModule} from '@angular/fire/analytics';
import {AngularFireAuthModule} from '@angular/fire/auth';
import { LoginComponent } from './login/login.component';
import { AfficheFilmComponent } from './affiche-film/affiche-film.component';
import {ClientService} from './client.service';
import { MonCompteComponent } from './mon-compte/mon-compte.component';
import { ListeFilmsComponent } from './liste-films/liste-films.component';
import { DetailsFilmComponent } from './details-film/details-film.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { NavbarComponent } from './navbar/navbar.component';
import {Router} from '@angular/router';
import { PanierComponent } from './panier/panier.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSnackBarModule} from '@angular/material/snack-bar';
import {MatFormFieldModule} from '@angular/material/form-field';
import { UpdateProfileComponent } from './update-profile/update-profile.component';
import { ListePlatsComponent } from './liste-plats/liste-plats.component';
import {ListeFilmsService} from './liste-films.service';
import {ListePlatsService} from './liste-plats.service';
import {PanierService} from './panier.service';
import { MesCommandesComponent } from './mes-commandes/mes-commandes.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AfficheFilmComponent,
    MonCompteComponent,
    ListeFilmsComponent,
    DetailsFilmComponent,
    PageNotFoundComponent,
    NavbarComponent,
    PanierComponent,
    UpdateProfileComponent,
    ListePlatsComponent,
    MesCommandesComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        AngularFireModule.initializeApp(environment.firebase),
        AngularFireAuthModule,
        AngularFireAnalyticsModule,
        AngularFirestoreModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
        MatSnackBarModule,
        MatFormFieldModule,
    ],
  providers: [TmdbService, HttpClient, ClientService, ListeFilmsService, ListePlatsService, PanierService],
  bootstrap: [AppComponent]
})
export class AppModule { }
