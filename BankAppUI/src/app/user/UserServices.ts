import {inject, Injectable} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface User {
  id?: number;
  userId?: string;
  user_email: string;
  password: string;
}

@Injectable({providedIn: 'root'})
export class Service {
  private http = inject(HttpClient);
  // Backend API base URL locally set up
  private apiUrl = 'http://localhost:5000/api/users';

  addUser(user: { user_email: string; password: string; }): Observable<User> {
    return this.http.post<User>(`${this.apiUrl}/adduser`, user);
  }

  onSubmitApplication(name: string, user_email: string, user_password: string): void {
    const userPayload = { user_email, password: user_password };

    this.addUser(userPayload).subscribe({
      next: (response) => {
        console.log('User successfully added', response);
      },
      error: (err) => {
        console.error('Error adding user:', err);
      }
    });
    console.log(name, user_email, user_password);
  }
}
