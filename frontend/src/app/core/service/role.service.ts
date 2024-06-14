import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Role } from '../interface/role';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  private BASE_URL = "http://localhost:8080/api/roles"

  constructor(private http:HttpClient) { }


  getRoles():Observable<Role[]>{
    const url = `${this.BASE_URL}/all`;
    return this.http.get<Role[]>(url);
  }
}
