import { Routes } from '@angular/router';
import { LandingPage } from './landing-page/landing-page';
import { LoginPage } from './login-page/login-page';
import { SignupPage } from './signup-page/signup-page';
import { User } from './user/user';

export const routes: Routes = [
  {path: '', component: LandingPage},
  {path: 'home', component: LandingPage},
  {path: 'login', component: LoginPage},
  {path: 'signup', component: SignupPage},
  {path: 'user', component: User},
];
