import { Component } from '@angular/core';
import { Post } from '../../../core/interface/post';
import { PostService } from '../../../core/service/post.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-post-details',
  templateUrl: './post-details.component.html',
  styleUrl: './post-details.component.scss'
})
export class PostDetailsComponent {

  postId:string = "";
  post:Post = {};

  constructor(private postService:PostService, private activatedRoute:ActivatedRoute){}


  ngOnInit(){
    this.postId = this.activatedRoute.snapshot.paramMap.get("postId") || "";
    this.getPost(this.postId);
  }

  getPost(id:string){
    this.postService.getPost(id).subscribe({
      next: (post) => {
        this.post = post;
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

}
