import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './component/signup/signup/signup.component';
import { LoginComponent } from './component/login/login/login.component';
import { StaffComponent } from './staff/staff.component';
import { UserComponent } from './user/user/user.component';
import { authGuard } from './core/guard/auth-guard.guard';
import { NotFoundComponent } from './component/not-found/not-found/not-found.component';

const routes: Routes = [
  {
    path: '',
    component: UserComponent,
    title: 'PostShare',
    loadChildren: () => 
      import('./user/user.module').then(m => m.UserModule)
  },
  {
    path: 'staff',
    component: StaffComponent,
    title: 'Management | PostShare',
    canActivate: [authGuard],
    loadChildren: () => 
      import('./staff/staff.module').then(m => m.StaffModule)
  },
  {
    path: 'login',
    component: LoginComponent,
    title: 'Login | PostShare'
  },
  {
    path: 'signup',
    component: SignupComponent,
    title: 'Signup | PostShare'
  },
  {
    path: '404',
    component: NotFoundComponent,
    title: '404 | PostShare'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }

