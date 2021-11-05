import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ClarityModule} from '@clr/angular';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HomeComponent} from './home/home.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {RestService} from "./services/rest.service";

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ClarityModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [RestService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
