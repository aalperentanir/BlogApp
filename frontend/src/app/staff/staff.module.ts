import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserManagmentComponent } from './user-managment/user-managment.component';
import { FeedComponent } from './feed/feed.component';
import { RouterModule, Routes } from '@angular/router';
import { PostDetailsComponent } from '../component/post-details/post-details/post-details.component';
import { PrimengModule } from '../primerg.module';
import { FormsModule } from '@angular/forms';
import { StaffComponent } from './staff.component';
import { ComponentModule } from '../component/component.module';

const routes: Routes = [
  {
    path: '',
    component: FeedComponent
  },
  {
    path: 'user-management',
    component: UserManagmentComponent
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
    StaffComponent,
    FeedComponent,
    UserManagmentComponent,
  ],
  imports: [
    ComponentModule,
    CommonModule,
    PrimengModule,
    FormsModule,
    RouterModule.forChild(routes)
  ]
})
export class StaffModule { }
