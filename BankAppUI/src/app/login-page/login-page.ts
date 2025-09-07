import { Component } from '@angular/core';
import {RouterLink} from '@angular/router';


@Component({
  selector: 'app-login-page',
  imports: [
    RouterLink,
  ],
  templateUrl: './login-page.html',
  standalone: true,
  styleUrl: './login-page.css'
})
export class LoginPage {

}
