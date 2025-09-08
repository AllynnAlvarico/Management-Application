import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Navigation } from '../navigation/navigation';

@Component({
  selector: 'app-landing-page',
  imports: [
    RouterLink,
    Navigation
  ],
  templateUrl: './landing-page.html',
  standalone: true,
  styleUrl: './landing-page.css'
})
export class LandingPage {

}
