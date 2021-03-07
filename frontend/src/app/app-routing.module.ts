import {NgModule} from '@angular/core';
import {RegisterComponent} from './components/register/register.component';
import {RouterModule} from '@angular/router';
import {LoginComponent} from './components/login/login.component';

const routes = [
  {path: 'register', component: RegisterComponent},
  {path: 'login', component: LoginComponent}
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
