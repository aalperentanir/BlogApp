import { Component } from '@angular/core';
import { User } from '../../core/interface/user';
import { Role } from '../../core/interface/role';
import { RoleService } from '../../core/service/role.service';
import { UserService } from '../../core/service/user.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-user-managment',
  templateUrl: './user-managment.component.html',
  styleUrl: './user-managment.component.scss'
})
export class UserManagmentComponent {

  roles!: Role[];
  users!: User[];
  isVisible: boolean = false;
  isEdit: boolean = false;
  user!: User;

  constructor( private roleService:RoleService, private userService:UserService, private messageService: MessageService){}

  ngOnInit(){
    this.getRoles();
    this.getUsers();
  }


  getRoles(){
    this.roleService.getRoles().subscribe({
      next: (roles) => {
        this.roles = roles;
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

  getUsers(){
    this.userService.getUsers().subscribe({
      next: (users) => {
        this.users = users;
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

  getRole(roleId: number){
    return this.roles?.find(role => role.id === roleId)?.name;
  }

  openDialogEdit(user: any) {
    this.isEdit = true;
    this.isVisible = true;
    this.user = {...user};
  }

  openDialogNew() {
    this.isEdit = false;
    this.isVisible = true;
    this.user = {
      roleId: 1,
      active: true
    };
  }

  save() {
    if(this.user.id) {

      this.userService.update(this.user.id, this.user).subscribe({
        next: (id) => {
          this.isVisible = false;
          
          const index = this.users.findIndex(user => user.id === this.user.id);
 
          if(index != -1) {
            this.users[index] = this.user;
          }
          this.messageService.add({ severity: 'success', summary: 'Success', detail: 'User is updated' });
        },
        error: (error) => {
          console.log(error);
        }
      })
    }
    else {
      this.userService.create(this.user).subscribe({
        next: (id) => {
          this.user.id = id;
          this.isVisible = false;
          this.users.unshift({
            ...this.user,
            id: id
          });
          this.messageService.add({ severity: 'success', summary: 'Success', detail: 'User is added' });
        },
        error: (error) => {
          console.log(error);
        }
      })
    }
  }

}
