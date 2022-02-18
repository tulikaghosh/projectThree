import { async, inject, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import { ProductAndDiscountService } from './product-and-discount.service';
import { TokenStorageService } from './token-storage.service';



export interface ProductAndDiscount {
  productId: number;
  productSku: string;
  productName: string;
  productCost: number;

}


describe('ProductAndDiscountService', () => {
  let service: ProductAndDiscountService;

  beforeEach(() => {
    const tokenStorageServiceStub = () => ({ getToken: () => ({}) });
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        ProductAndDiscountService,
        { provide: TokenStorageService, useFactory: tokenStorageServiceStub }
      ]
    });
    service = TestBed.inject(ProductAndDiscountService);
  });

  it('can load instance', () => {
    expect(service).toBeTruthy();
  });
});