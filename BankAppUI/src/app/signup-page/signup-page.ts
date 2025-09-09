import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Backicon } from '../common/backicon/backicon';
import { SignupForm } from './signup-form/signup-form';

@Component({
  selector: 'app-signup-page',
  imports: [
    RouterLink,
    Backicon,
    SignupForm,
  ],
  templateUrl: './signup-page.html',
  styleUrls: ['./signup-page.css'],
  standalone: true
})
export class SignupPage {

}

