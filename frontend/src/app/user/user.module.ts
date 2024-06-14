import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PostDetailsComponent } from '../component/post-details/post-details/post-details.component';
import { FeedComponent } from './feed/feed.component';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ComponentModule } from '../component/component.module';
import { UserComponent } from './user/user.component';
import { PrimengModule } from '../primerg.module';
import { PostComponent } from '../component/post/post/post.component';



const routes: Routes = [
  {
    path: '',
    component: FeedComponent
  },
  {
    path: 'posts', children: [
      {
        path: ':postId',
        component: PostDetailsComponent
      }
    ]
  }
];

@NgModule({
  declarations: [
    UserComponent,
    FeedComponent
  ],
  imports: [
    ComponentModule,
    CommonModule,
    PrimengModule,
    FormsModule,
    RouterModule.forChild(routes),
    
  ]
})
export class UserModule { }
