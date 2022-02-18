import { HttpClientTestingModule } from '@angular/common/http/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthService } from 'src/app/services/auth.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';

import { LoginComponent } from './login.component';


describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(() => {
    const routerStub = () => ({ navigate: (array: any) => ({ then: () => ({}) }) });
    const authServiceStub = () => ({
      login: (username: any, password: any) => ({ subscribe: (f: (arg0: {}) => any) => f({}) })
    });
    const tokenStorageServiceStub = () => ({
      getToken: () => ({}),
      getUser: () => ({ roles: {}, username: {} }),
      saveToken: (accessToken: any) => ({}),
      saveUser: (data: any) => ({})
    });
    TestBed.configureTestingModule({
      imports: [FormsModule],
      schemas: [NO_ERRORS_SCHEMA],
      declarations: [LoginComponent],
      providers: [
        { provide: Router, useFactory: routerStub },
        { provide: AuthService, useFactory: authServiceStub },
        { provide: TokenStorageService, useFactory: tokenStorageServiceStub }
      ]
    });
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
  });

  it('can load instance', () => {
    expect(component).toBeTruthy();
  });

  it(`isLoggedIn has default value`, () => {
    expect(component.isLoggedIn).toEqual(false);
  });

  it(`isLoginFailed has default value`, () => {
    expect(component.isLoginFailed).toEqual(false);
  });

  it(`roles has default value`, () => {
    expect(component.roles).toEqual([]);
  });

  describe('ngOnInit', () => {
    it('makes expected calls', () => {
      const tokenStorageServiceStub: TokenStorageService = fixture.debugElement.injector.get(
        TokenStorageService
      );
      spyOn(tokenStorageServiceStub, 'getToken').and.callThrough();
      spyOn(tokenStorageServiceStub, 'getUser').and.callThrough();
      component.ngOnInit();
      expect(tokenStorageServiceStub.getToken).toHaveBeenCalled();
      expect(tokenStorageServiceStub.getUser).toHaveBeenCalled();
    });
  });

  describe('onSubmit', () => {
    it('makes expected calls', () => {
      const routerStub: Router = fixture.debugElement.injector.get(Router);
      const authServiceStub: AuthService = fixture.debugElement.injector.get(
        AuthService
      );
      const tokenStorageServiceStub: TokenStorageService = fixture.debugElement.injector.get(
        TokenStorageService
      );
      spyOn(routerStub, 'navigate').and.callThrough();
      spyOn(authServiceStub, 'login').and.callThrough();
      spyOn(tokenStorageServiceStub, 'saveToken').and.callThrough();
      spyOn(tokenStorageServiceStub, 'saveUser').and.callThrough();
      spyOn(tokenStorageServiceStub, 'getUser').and.callThrough();
      component.onSubmit();
      expect(routerStub.navigate).toHaveBeenCalled();
      expect(authServiceStub.login).toHaveBeenCalled();
      expect(tokenStorageServiceStub.saveToken).toHaveBeenCalled();
      expect(tokenStorageServiceStub.saveUser).toHaveBeenCalled();
      expect(tokenStorageServiceStub.getUser).toHaveBeenCalled();
    });
  });
});



