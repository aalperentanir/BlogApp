import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../interface/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private BASE_URL = "http://localhost:8080/api/users"

  constructor(private http:HttpClient) { }

  getUsers():Observable<User[]>{
    const url = `${this.BASE_URL}/all`;
    return this.http.get<User[]>(url);
  }

  getUser(id: string):Observable<User>{
    const url = `${this.BASE_URL}/${id}`;
    return this.http.get<User>(url);
  }

  create(user:User):Observable<string>{
    const url = `${this.BASE_URL}/create`;
    return this.http.post<string>(url,user);
  }

  update(id:string, user:User):Observable<string>{
    const url = `${this.BASE_URL}/${id}`;
    return this.http.patch<string>(url,user);
  }
}
