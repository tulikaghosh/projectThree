import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductAndDiscount } from 'src/app/models/product.model';
import { PurchasedItemProduct } from 'src/app/models/purchased-item.model';
import { PurchasedItemService } from 'src/app/services/purchased-item.service';

@Component({
  selector: 'app-confirmation-checkout',
  templateUrl: './confirmation-checkout.component.html',
  styleUrls: ['./confirmation-checkout.component.scss']
})
export class ConfirmationCheckoutComponent implements OnInit {

  purchasedItemProduct: PurchasedItemProduct[] = [];
  errorMsg: string = "";
  transId: any = 0;
  constructor(private activatedRoute: ActivatedRoute, private purchasedItemService: PurchasedItemService) { }

  ngOnInit(): void {
    this.transId = this.activatedRoute.snapshot.paramMap.get("sentTransaction");
    this.loadItems();
  }


  loadItems() {
    this.purchasedItemService.getPurchasedItemsByTransaction(this.transId).subscribe((response) => {
      this.purchasedItemProduct = response;
    }, error => {
      this.errorMsg = 'There was some internal error! Please try again later!';
    });
  }

  calculateCost() {
    let totalCost = 0;
    this.purchasedItemProduct.forEach(item => {
      totalCost += (item.purchaseCost * item.itemQty);
    });
    return totalCost;
  }

}
