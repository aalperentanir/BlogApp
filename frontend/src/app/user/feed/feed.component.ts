import { Component } from '@angular/core';
import { Post } from '../../core/interface/post';
import { PostService } from '../../core/service/post.service';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrl: './feed.component.scss'
})
export class FeedComponent {

  posts: Post[]= [];
  isVisible: boolean = false;
  isEdit: boolean = false;
  post: Post = {};

  constructor(private postService:PostService){}

  ngOnInit(){
    this.getPosts();
  }


  getPosts(){
    this.postService.getPosts().subscribe({
      next: (posts) => {
        this.posts = posts;
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

}
