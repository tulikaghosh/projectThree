import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { CartAndItemsService } from './cart-and-items.service';
import { TokenStorageService } from './token-storage.service';

describe('CartAndItemsService', () => {
  let service: CartAndItemsService;

  beforeEach(() => {
    const tokenStorageServiceStub = () => ({ getToken: () => ({}) });
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        CartAndItemsService,
        { provide: TokenStorageService, useFactory: tokenStorageServiceStub }
      ]
    });
    service = TestBed.inject(CartAndItemsService);
  });

  it('can load instance', () => {
    expect(service).toBeTruthy();
  });
});
