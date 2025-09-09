import { Component } from '@angular/core';
import {Sidebar} from './sidebar/sidebar';

@Component({
  selector: 'app-dashboard',
  imports: [
    Sidebar
  ],
  templateUrl: './dashboard.html',
  standalone: true,
  styleUrl: './dashboard.css'
})
export class Dashboard {

}
