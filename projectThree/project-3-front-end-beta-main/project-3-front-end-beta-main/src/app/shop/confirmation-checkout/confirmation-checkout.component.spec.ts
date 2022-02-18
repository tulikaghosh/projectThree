import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmationCheckoutComponent } from './confirmation-checkout.component';
import {RouterTestingModule} from "@angular/router/testing";
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('ConfirmationCheckoutComponent', () => {
  let component: ConfirmationCheckoutComponent;
  let fixture: ComponentFixture<ConfirmationCheckoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        HttpClientTestingModule
      ],
      declarations: [ ConfirmationCheckoutComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmationCheckoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

});
