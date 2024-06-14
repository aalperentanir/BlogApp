import { Component } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { PostService } from '../../core/service/post.service';
import { Post } from '../../core/interface/post';


@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.scss'],
  providers: [MessageService, ConfirmationService]
})

export class FeedComponent {
  posts: Post[] = [];
  isVisible: boolean = false; // if the dialog is open
  isEdit: boolean = false; // id the dialog is Edit or New
  post: Post = {};

  constructor(
    private postService: PostService,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
  ) { }

  ngOnInit() {
    this.getPosts();
  }

  getPosts() {
    this.postService.getPosts().subscribe({
      next: (posts) => {
        this.posts = posts;
        console.log(posts);
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

  openDialogNew() {
    this.isEdit = false;
    this.isVisible = true;
    this.post = {};
  }

  openDialogEdit(post: any) {
    this.isEdit = true;
    this.isVisible = true;
    this.post = {...post};
  }

  save() {
    if(this.post.id) {

      this.postService.update(this.post.id, this.post).subscribe({
        next: (id) => {
          this.isVisible = false; 
         
          const index = this.posts.findIndex(post => post.id === this.post.id);
 
          if(index != -1) {
            this.posts[index] = this.post;
          }
          this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Post is updated' });
        },
        error: (error) => {
          console.log(error);
        }
      })
    }
    else {
      
      this.postService.create(this.post).subscribe({
        next: (id) => {
          this.post.id = id;
          this.isVisible = false;
          this.posts.unshift({
            ...this.post,
            id: id,
            createdDate: new Date().toJSON()
          });
          this.posts = [...this.posts];
          this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Post is added' });
        },
        error: (error) => {
          console.log(error);
        }
      })
    }
  }

  delete(event: Event) {
    this.confirmationService.confirm({
      target: event.target as EventTarget,
      message: 'Do you want to delete this post?',
      header: 'Delete Confirmation',
      icon: 'pi pi-info-circle',
      acceptButtonStyleClass:"p-button-danger p-button-text",
      rejectButtonStyleClass:"p-button-text p-button-text",
      acceptIcon:"none",
      rejectIcon:"none",

      accept: () => {
        if(!this.post.id) return;

        this.postService.deletePost(this.post.id).subscribe({
          next: () => {
            this.posts = this.posts.filter(post => post.id !== this.post.id);
            this.isVisible = false;
            this.messageService.add({ severity: 'info', summary: 'Confirmed', detail: 'Post deleted' });
          },
          error: (error) => {
            console.log(error);
          }
        })
      },
      reject: () => {
          // this.messageService.add({ severity: 'error', summary: 'Rejected', detail: 'You have rejected' });
      }
    });
  }

}
