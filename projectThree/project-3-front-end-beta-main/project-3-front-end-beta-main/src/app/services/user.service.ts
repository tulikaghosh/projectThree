import { Injectable } from '@angular/core';
import { User } from '../models/user.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import {Instance} from "../models/Instance";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  baseUrl = Instance.url + "/api";

  constructor(private http: HttpClient) { }

  updateUserService(updateUser: User):Observable <User>{
    return this.http.put<User>(this.baseUrl+"/users/"+updateUser.user_id,updateUser);
   }
   
}
