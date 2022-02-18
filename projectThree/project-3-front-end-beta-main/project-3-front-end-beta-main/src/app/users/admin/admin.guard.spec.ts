import { HttpClientTestingModule } from '@angular/common/http/testing';
import { getTestBed, TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { AdminGuard } from './admin.guard';

describe('AdminGuard', () => {
  let injector: TestBed;
  let token: TokenStorageService
  let routeMock: any = { snapshot: {}};
  let routeStateMock: any = { snapshot: {}, url: '/cookies'};
  let routerMock = {navigate: jasmine.createSpy('navigate')}
  let guard: AdminGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AdminGuard, { provide: Router, useValue: routerMock },],
      imports: [HttpClientTestingModule]
    });
    injector = getTestBed();
    token = injector.get(AuthService);
    guard = injector.get(AdminGuard);

  });  });
