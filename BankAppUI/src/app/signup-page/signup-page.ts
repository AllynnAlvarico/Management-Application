import { Component } from '@angular/core';
import {RouterLink} from '@angular/router';
import {Backicon} from '../common/backicon/backicon';

@Component({
  selector: 'app-signup-page',
  imports: [
    RouterLink,
    Backicon,
  ],
  templateUrl: './signup-page.html',
  standalone: true,
  styleUrl: './signup-page.css'
})
export class SignupPage {

}
