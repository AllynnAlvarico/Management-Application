import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Backicon } from '../common/backicon/backicon';
import { LoginForm } from './login-form/login-form';
import { NgIf } from '@angular/common';
@Component({
  selector: 'app-login-page',
  imports: [
    RouterLink,
    Backicon,
    LoginForm,
    // NgIf,
  ],
  templateUrl: './login-page.html',
  standalone: true,
  styleUrl: './login-page.css'
})
export class LoginPage {

  // showOverlay = false;

}


