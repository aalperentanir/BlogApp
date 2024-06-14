import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LikeButtonComponent } from './like-button/like-button/like-button.component';
import { LoginComponent } from './login/login/login.component';
import { SignupComponent } from './signup/signup/signup.component';
import { NotFoundComponent } from './not-found/not-found/not-found.component';
import { PostComponent } from './post/post/post.component';
import { PostDetailsComponent } from './post-details/post-details/post-details.component';
import { CoreModule } from '../core.module';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PrimengModule } from '../primerg.module';



@NgModule({
  declarations: [
    PostComponent,
    PostDetailsComponent,
    LoginComponent,
    NotFoundComponent,
    LikeButtonComponent,
    SignupComponent
  ],
  imports: [
    CommonModule,
    CoreModule,
    PrimengModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule
  ],
  exports: [
    PostComponent
  ]
})
export class ComponentModule { }
