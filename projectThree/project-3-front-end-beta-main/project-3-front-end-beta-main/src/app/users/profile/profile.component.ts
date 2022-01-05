import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user.model';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {


  mainUser: User = {
    userId: 0,
    userEmail: '',
    userName: '',
    userPassword: '',
    userFirstName: '',
    userLastName: '',
    userAddress: '',
    userContact: '',
    userType: '',
    userRemoved: false
  }
​
  editUser: User ={
    userId: 0,
    userEmail: '',
    userName: '',
    userPassword: '',
    userFirstName: '',
    userLastName: '',
    userAddress: '',
    userContact: '',
    userType: '',
    userRemoved: false
  }
  private roles: string[] = [];
  isLoggedIn = false;
  username?: string;
  first_name?: string;
  last_name?: string;
  password?: string;
  email?: string;
  address?: string;
  contact?: string;
  showAdmin = false;
  currentUser: any;  
  errorMsg = "";
  

  constructor(private token: TokenStorageService, private userService: UserService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.token.getToken();
    if (this.token.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.token.getUser().roles;
      this.currentUser = this.token.getUser();
    }
    if (this.isLoggedIn) {
      const user = this.token.getUser();
      this.roles = user.roles;
      this.username = user.username;
      this.email = user.email;
      this.first_name = user.first_name;
      this.last_name = user.last_name;
      this.address = user.address;
      this.contact = user.contact;
      this.password = user.password;
​


      this.showAdmin = this.roles.includes('ROLE_ADMIN');
    }
    this.currentUser = this.token.getUser();
  }

  // getuserInfo() {
  //   this.userService.getUserInfo(Number(sessionStorage.getItem("userId"))).subscribe(
  //     (response) => {
  //       this.mainUser = response;
  //       this.editUser.userId = response.id;
  //       this.editUser.userEmail = response.email;
  //       this.editUser.userPassword = response.password;
  //       console.log(response);
  //     },
  //     (error) => {
  //       console.log(error);
  //       this.errorMsg = "ERROR GETTING USER INFOMATION!!!";
  //     }
  //   );
  // }

}