import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { jwtDecode } from 'jwt-decode';
import { Token } from '../interface/token';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private BASE_URL = "http://localhost:8080/api/auth"


  constructor(private http:HttpClient, private router: Router) { }

  login(credentials: {email:any, password:any}):Observable<any>{
    return this.http.post<any>(`${this.BASE_URL}/login`,credentials)
  }


  signUp(credentials: {email:any, password:any, fullName:any}):Observable<any>{
    return this.http.post<any>(`${this.BASE_URL}/signup`,credentials)
  }


  setToken(token:string){
    window.localStorage.setItem("auth_token", token);
  }


  getToken():string{
    return window.localStorage.getItem("auth_token") || "";
  }


  removeToken():void{
    window.localStorage.removeItem("auth_token");
  }


  setCurrentUsername(username: string) {
    window.localStorage.setItem("current_username", username);
  }


  getCurrentUsername(): string {
    return window.localStorage.getItem("current_username") || '';
  }

 
  removeCurrentUsername(): void {
    window.localStorage.removeItem("current_username");
  }


  canActive(): boolean{
    const token = this.getToken();
    const decodedToken = jwtDecode<Token>(token);
    const role = decodedToken.role

    if(role !== "ROLE_USER"){
      return true;
    }else{
      this.router.navigate(["/login"], {
        queryParams: {message: "Unauthorized"}
      });
      return false;
    }
  }
}
