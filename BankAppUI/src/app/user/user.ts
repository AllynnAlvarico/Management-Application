import { Component } from '@angular/core';
import { Dashboard } from './dashboard/dashboard';

@Component({
  selector: 'app-user',
  imports: [
    Dashboard,
  ],
  templateUrl: './user.html',
  standalone: true,
  styleUrl: './user.css'
})
export class User {

}
