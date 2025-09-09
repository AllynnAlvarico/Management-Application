import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators, ReactiveFormsModule} from '@angular/forms';
import { Router } from '@angular/router';

import {NgIf} from '@angular/common';
import { SuccessOverlay } from '../../common/success-overlay/success-overlay';

@Component({
  selector: 'app-login-form',
  imports: [
    ReactiveFormsModule,
    NgIf,
    SuccessOverlay,
  ],
  templateUrl: './login-form.html',
  standalone: true,
  styleUrl: './login-form.css'
})
export class LoginForm {

  constructor(private router: Router) {}

  email: string = "demo@bank.com"
  password: string = "qwerty1234"

  showOverlay = false;

  loginForm = new FormGroup({
    email: new FormControl<string>('', [Validators.required, Validators.email]),
    password: new FormControl<string>('', [Validators.required]),
  });

  onSubmit(): void {
    if (this.loginForm.valid) {
      const { email  , password } = this.loginForm.value;
      this.showConsole(this.checkCredential(email, password));
      this.router.navigate(['/user'], { queryParams: { user: email } });
    }
  }

  checkCredential(emailAdd: string | null | undefined, password: string | null | undefined): boolean {
    if (emailAdd === this.email) {
      if (password === this.password) {
        return true;
      }
    }
    return false;
  }

  showConsole(condition: boolean): void {
    if (condition) {
      console.log("Sign in Successful");
      this.showOverlay = true;
    } else {
      console.log("Sign in Unsuccessful");
    }
  }
}
