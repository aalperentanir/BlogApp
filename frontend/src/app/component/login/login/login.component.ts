import { Component } from '@angular/core';
import { AuthService } from '../../../core/service/auth.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  loginForm: any = {email:"", password: ""};
  message : string = "";
  type: string = "";

  constructor(private authService:AuthService, private router:Router, private route:ActivatedRoute){}

  ngOnInit(){
    this.route.queryParams.subscribe(params => {
      this.message = params["message"];
      this.type = params["type"];
    })
  }

  login(){
    this.authService.removeCurrentUsername();
    this.authService.removeToken();

    this.authService.login(this.loginForm).subscribe({
      next:(response) => {
        this.authService.setCurrentUsername(this.loginForm.email);
        this.authService.setToken(response.token);
        this.router.navigate(["/"]);
      },
      error: (error) => {
        console.log(error);
        this.message = error.error.message;
      }
    })
  }

}
