import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../core/service/auth.service';

@Component({
  selector: 'app-staff',
  templateUrl: './staff.component.html',
  styleUrl: './staff.component.scss'
})
export class StaffComponent {
  menus = [
    {
      name: 'Posts',
      link: ''
    },
    {
      name: "User management",
      link: 'user-management'
    }
  ];

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}


  logout() {
    this.authService.removeToken();
    this.authService.removeCurrentUsername();
    this.router.navigate(['/login']);
  }

}
