import { Injectable } from '@angular/core';
import {Instance} from "../models/Instance";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {TokenStorageService} from "./token-storage.service";
import {PurchasedItem, PurchasedItemProduct} from "../models/purchased-item.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class PurchasedItemService {

  baseUrl = Instance.url + "/api/purchased-items";
  header = {};

  constructor(private http: HttpClient, tokenService: TokenStorageService) {
    this.header = {
      headers: new HttpHeaders()
        .set('Authorization',  `Bearer ${tokenService.getToken()}`)
    }
  }

  addPurchasedItems(items: PurchasedItem[]): Observable<boolean> {
    return this.http.post<boolean>(this.baseUrl + "/many/post", items, this.header);
  }

  getPurchasedItemsByTransaction(transactionId: number): Observable<PurchasedItemProduct[]> {
    return this.http.get<PurchasedItemProduct[]>(this.baseUrl + ("/transaction/" + transactionId  + "/get"), this.header)
  }

  getPurchasedItemsByUser(userId: number): Observable<PurchasedItemProduct[]> {
    return this.http.get<PurchasedItemProduct[]>(this.baseUrl + ("/user/" + userId  + "/get"), this.header)
  }

}
