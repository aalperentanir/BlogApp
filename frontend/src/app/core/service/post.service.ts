import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from '../interface/post';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private BASE_URL = "http://localhost:8080/api/posts"

  constructor(private http:HttpClient) { }


  getPosts():Observable<Post[]>{
    const url = `${this.BASE_URL}/all`;
    return this.http.get<Post[]>(url);
  }

  getPost(id: string):Observable<Post>{
    const url = `${this.BASE_URL}/${id}`;
    return this.http.get<Post>(url);
  }

  create(post:Post):Observable<string>{
    const url = `${this.BASE_URL}/create`;
    return this.http.post<string>(url,post);
  }

  update(id:string, post:Post):Observable<string>{
    const url = `${this.BASE_URL}/${id}`;
    return this.http.patch<string>(url,post);
  }

  deletePost(id: string): Observable<void> {
    const url = `${this.BASE_URL}/${id}`;
    return this.http.delete<void>(url);
  }
}
