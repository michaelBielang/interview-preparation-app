import {NgModule} from '@angular/core';
import {RegisterComponent} from './components/register/register.component';
import {RouterModule} from '@angular/router';

const routes = [{
  path: 'register', component: RegisterComponent
},

];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
