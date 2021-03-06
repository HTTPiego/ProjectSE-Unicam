import { DEFAULT_CURRENCY_CODE, LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; // <-- NgModel lives here
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RigaAttiviaComponent } from './attivita/riga-attivita/riga-attivita.component';
import { RigaAttivitaEditorComponent } from './attivita/riga-attivita-editor/riga-attivita-editor.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthenticationService } from './service/authentication.service';
import { AuthenticationComponent } from './authentication/component/authentication.component';
import { GestoreHomeComponent } from './gestore-home/gestore-home.component';
import { ClienteHomeComponent } from './cliente-home/cliente-home.component';
import { PrenotazioneOmbrelloneComponent } from './prenotazione-ombrellone/prenotazione-ombrellone.component';
import { PrenotazioneAttivitaComponent } from './prenotazione-attivita/prenotazione-attivita.component';
import { OrdinazioneBarComponent } from './ordinazione-bar/ordinazione-bar.component';
import { DefaultHttpInterceptor } from './helpers/http-interceptor';
import { CassiereHomeComponent } from './cassiere-home/cassiere-home.component';
import { AddettoBarHomeComponent } from './addetto-bar-home/addetto-bar-home.component';
import { LoginGuard } from './helpers/login-guard';
// Per impostare la data in italiano
import localeIt from '@angular/common/locales/it';
import { registerLocaleData } from '@angular/common';
import { RigaBarComponent } from './bar/riga-bar/riga-bar.component';
import { RigaBarEditorComponent } from './bar/riga-bar-editor/riga-bar-editor.component';
import { RigaOmbrelloneComponent } from './spiaggia/riga-ombrellone/riga-ombrellone.component';
import { RigaOmbrelloneEditorComponent } from './spiaggia/riga-ombrellone-editor/riga-ombrellone-editor.component';
import { PrenotazioneOmbrelloneService } from './service/prenotazione-ombrellone.service';
import { HomeGuard } from './helpers/home-guard';
import { VenditaComponent } from './vendita/vendita.component';
import { OrdinazioneBarStoricoComponent } from './ordinazione-bar/ordinazione-bar-storico/ordinazione-bar-storico.component';
import { CasottoNavbarComponent } from './casotto-navbar/casotto-navbar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
//import {MatFormFieldModule} from '@angular/material/form-field';
import { MatDatepickerModule } from '@angular/material/datepicker'
import { MatInputModule } from '@angular/material/input';
import { MatNativeDateModule } from '@angular/material/core';
registerLocaleData(localeIt, 'it');
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LeMiePrenotazioniOmbrelloniComponent } from './prenotazione-ombrellone/le-mie-prenotazioni-ombrelloni/le-mie-prenotazioni-ombrelloni.component';
import { LeMiePrenotazioniAttivitaComponent } from './prenotazione-attivita/le-mie-prenotazioni-attivita/le-mie-prenotazioni-attivita.component';
import { ProfiloComponent } from './profilo/profilo.component';
import { NotificaComponent } from './notifica/notifica.component';
import { AddettoBarOrdinazioniComponent } from './addetto-bar-home/addetto-bar-ordinazioni/addetto-bar-ordinazioni.component';
import { HomeComponent } from './home/home.component';
import { CasottoFooterComponent } from './casotto-footer/casotto-footer.component';
import { InfrastrutturaChaletComponent } from './infrastruttura-chalet/infrastruttura-chalet.component';
import { InfoComponent } from './home/info/info.component';
import { AboutComponent } from './about/about.component';

@NgModule({
  declarations: [
    AppComponent,
    RigaAttiviaComponent,
    RigaAttivitaEditorComponent,
    AuthenticationComponent,
    GestoreHomeComponent,
    ClienteHomeComponent,
    PrenotazioneOmbrelloneComponent,
    PrenotazioneAttivitaComponent,
    OrdinazioneBarComponent,
    CassiereHomeComponent,
    AddettoBarHomeComponent,
    RigaBarComponent,
    RigaBarEditorComponent,
    RigaOmbrelloneComponent,
    RigaOmbrelloneEditorComponent,
    VenditaComponent,
    OrdinazioneBarStoricoComponent,
    CasottoNavbarComponent,
    LeMiePrenotazioniOmbrelloniComponent,
    LeMiePrenotazioniAttivitaComponent,
    ProfiloComponent,
    NotificaComponent,
    AddettoBarOrdinazioniComponent,
    HomeComponent,
    CasottoFooterComponent,
    InfrastrutturaChaletComponent,
    InfoComponent,
    AboutComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatNativeDateModule,
    MatInputModule,
    MatDatepickerModule,
    NgbModule
  ],
  providers: [
    AuthenticationService,
    LoginGuard,
    HomeGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: DefaultHttpInterceptor,
      multi: true
    },
    [{ provide: LOCALE_ID, useValue: 'it' }  // BISOGNA INSERIRE LA DATA NEL PROVIDER!
      , { provide: DEFAULT_CURRENCY_CODE, useValue: 'EUR' }], // Per visualizzare in euro
    PrenotazioneOmbrelloneService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
