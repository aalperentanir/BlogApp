import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Like } from '../interface/like';

@Injectable({
  providedIn: 'root'
})
export class LikeService {

    private BASE_URL = "http://localhost:8080/api/likes"


  constructor(private http:HttpClient) { }


  toggleLike(postId:string):Observable<Like>{
    const url = `${this.BASE_URL}/${postId}`;
    return this.http.post<Like>(url,null);
  }

  isLiked(postId: string):Observable<boolean>{
    const url = `${this.BASE_URL}/${postId}`;
    return this.http.get<boolean>(url);
  }

  countLikes(postId:string):Observable<number>{
    const url = `${this.BASE_URL}/count/${postId}`;
    return this.http.get<number>(url);
  }
}
