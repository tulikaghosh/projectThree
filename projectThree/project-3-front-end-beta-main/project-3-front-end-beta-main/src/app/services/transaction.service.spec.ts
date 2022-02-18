import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { Transaction } from '../models/transaction.model';
import { TokenStorageService } from './token-storage.service';

import { TransactionService } from './transaction.service';


describe('TransactionService', () => {
  let service: TransactionService;

  beforeEach(() => {
    const tokenStorageServiceStub = () => ({ getToken: () => ({}) });
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        TransactionService,
        { provide: TokenStorageService, useFactory: tokenStorageServiceStub }
      ]
    });
    service = TestBed.inject(TransactionService);
  });

  it('can load instance', () => {
    expect(service).toBeTruthy();
  });

  describe('sendTransaction', () => {
    it('makes expected calls', () => {
      const httpTestingController = TestBed.inject(HttpTestingController);
      const transactionStub: Transaction = <any>{};
      service.sendTransaction(transactionStub).subscribe(res => {
        expect(res).toEqual(transactionStub);
      });
      const req = httpTestingController.expectOne(service.baseUrl + "/post");
      expect(req.request.method).toEqual('POST');
      req.flush(transactionStub);
      httpTestingController.verify();
    });
  });
});
