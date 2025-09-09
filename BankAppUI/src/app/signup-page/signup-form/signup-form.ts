import { Component, inject } from '@angular/core';

import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Service } from '../../user/UserServices';
@Component({
  selector: 'app-signup-form',
  imports: [
    ReactiveFormsModule,
  ],
  templateUrl: './signup-form.html',
  standalone: true,
  styleUrl: './signup-form.css'
})
export class SignupForm {
  service = inject(Service);

  name: any;
  user_email: any;
  user_password: any;

  userSignupForm = new FormGroup({
    name: new FormControl(''),
    user_email: new FormControl(''),
    user_password: new FormControl(''),
  });

  onSubmitApplication(){
    if (this.userSignupForm.invalid) return;

    this.service.onSubmitApplication(
      this.userSignupForm.value.name ?? '',
      this.userSignupForm.value.user_email ?? '',
      this.userSignupForm.value.user_password ?? ''
    );
  }
}
