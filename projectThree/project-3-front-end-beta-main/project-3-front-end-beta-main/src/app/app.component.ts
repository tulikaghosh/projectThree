import { Component } from '@angular/core';
import { TokenStorageService } from './services/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})

export class AppComponent {
  title = 'rev-tech';
  USER_KEY = 'auth-user';


  constructor(private tokenstorage: TokenStorageService) {
    if (window.sessionStorage.getItem(this.USER_KEY)!=null){
     this.tokenstorage.isLoggedIn=true;
    }   
  }
}
