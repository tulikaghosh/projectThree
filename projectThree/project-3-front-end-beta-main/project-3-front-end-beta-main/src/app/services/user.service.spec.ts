import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { User } from '../models/user.model';
import { ProfileComponent } from '../users/profile/profile.component';

import { UserService } from './user.service';

describe('UserService', () => {
  let component: UserService;
  let fixture: ComponentFixture<ProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientTestingModule
      ],
      declarations: [
        UserService
      ],
    }).compileComponents();
  }));
});
