import {Component, NgModule, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { CartAndItems, CartItem, ItemProductAndDiscount } from 'src/app/models/cart.model';
import { ProductAndDiscount } from 'src/app/models/product.model';
import { User } from 'src/app/models/user.model';
import { AuthService } from 'src/app/services/auth.service';
import { CartAndItemsService } from 'src/app/services/cart-and-items.service';
import { CartItemService } from 'src/app/services/cart-item.service';
import { ProductAndDiscountService } from 'src/app/services/product-and-discount.service';
import {TokenStorageService} from "../../services/token-storage.service";
import { HttpClientModule } from '@angular/common/http';
import {Review, UserReview} from "../../models/review.model";
import {ReviewService} from "../../services/review.service";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
@Component({
  selector: 'app-product-page',
  templateUrl: './product-page.component.html',
  styleUrls: ['./product-page.component.scss']
})

// @NgModule({
//   imports: [ReactiveFormsModule,
//             FormsModule],
// });
export class ProductPageComponent implements OnInit {
  productAndDiscount: ProductAndDiscount = new ProductAndDiscount();
  userId: any = 0;
  cartAndItems: CartAndItems = new CartAndItems();
  item: CartItem = new CartItem();
  productId: number = 0;
  counter = 0;
  title = "Initiating Testing";
  reviews: UserReview[] = [];
  reviewForm: Review = new Review();
  averageRating: number = 0.0;
  productLoaded: boolean = false;
  reviewRatings = {
    "five": 0,
    "four": 0,
    "three": 0,
    "two": 0,
    "one": 0,
  }

  constructor(private productAndDiscountService: ProductAndDiscountService,
              private cartItemService: CartItemService,
              private cartAndItemsService: CartAndItemsService,
              private authService: AuthService,
              private activatedRoute: ActivatedRoute,
              private tokenService: TokenStorageService,
              private router: Router,
              private reviewService: ReviewService) { }

  ngOnInit(): void {
    // let pId: string = this.activatedRoute.snapshot.paramMap.get("productId") == null ? "" :  this.activatedRoute.snapshot.paramMap.get("productId");
    this.userId = this.tokenService.getUser().user_id;
    let param = this.activatedRoute.snapshot.paramMap.get("productId");
    this.productId = (param == null) ? 0 : parseInt(param);
    this.loadData();
    this.loadReviews();

  }
  loadData() {
    this.productAndDiscountService.getProductAndDiscountService(this.productId).subscribe({
      next: response => {
        this.productAndDiscount = response;
        this.productLoaded = true;
      },
      error: error => {
      }
    });
    // if(this.user.userId <= 0) this.user.userId = 1; //Remove this line if not testing
    this.cartAndItemsService.getCartAndItemsWithUserIdService(this.userId).subscribe({
      next: response => {
        this.cartAndItems = response;

      },
      error: error => {
      }
    });
  }
  updateCartItem() {
    this.item.cartId = this.cartAndItems.cartId;
    this.item.productId = this.productId;
    this.item.cartQty = this.counter;
    this.item.cartItemId = -1;
    this.cartItemService.addNewItemService(this.item).subscribe({
      next: response => {
        // this.goToCheckout()
        this.loadData();
      },
      error: error => {
      }
    });
  }

  goToCheckout() {
    this.router.navigate(['checkout']);
  }

  changeQuantity(item: ItemProductAndDiscount, event: any) {
    let newItem = new CartItem();
    newItem.cartItemId = item.cartItemId;
    newItem.cartId = item.cartId;
    newItem.productId = item.productId;
    newItem.cartQty = event.value;
    this.cartItemService.updateItemService(newItem).subscribe({
      next: response => {
        this.loadData();
      },
      error: err => {
      }
    });
  }

  increaseCount() {
    this.counter++;
    this.qtyChange();
  }
  decreaseCount() {
    this.counter--;
    this.qtyChange();
  }

  qtyChange() {
    if (this.counter > this.productAndDiscount.productQty) this.counter = this.productAndDiscount.productQty;
    else if (this.counter < 0) this.counter = 0;
    this.updateCartItem();
  }

  loadReviews() {
    this.reviewRatings = {
      "five": 0,
      "four": 0,
      "three": 0,
      "two": 0,
      "one": 0,
    }
    this.reviewService.getReviews(this.productId).subscribe({
      next: response => {
        // this.goToCheckout()
        this.reviews = response;
        this.sortReviews();
      },
      error: error => {
      }
    });
  }

  sortReviews() {
    let totalStars = 0;
    this.reviews.forEach(review => {
      totalStars += review.rating;
      if(review.rating == 5) this.reviewRatings.five++;
      else if(review.rating == 4) this.reviewRatings.four++;
      else if(review.rating == 3) this.reviewRatings.three++;
      else if(review.rating == 2) this.reviewRatings.two++;
      else if(review.rating == 1) this.reviewRatings.one++;
    })
    this.averageRating = totalStars / this.reviews.length;
    if(!this.averageRating) this.averageRating = 0;
  }

  // getAverageRating() {
  //   let totalStars = 0;
  //
  // }

  postReview() {
    this.reviewForm.productId = this.productId;
    this.reviewForm.userId = this.userId;
    this.reviewService.postReview(this.reviewForm).subscribe({
      next: response => {
        // this.goToCheckout()
        this.loadReviews();
      },
      error: error => {
      }
    });
  }

  starRating(): Array<number> {
    // if (this.reviewForm.rating < 1) this.reviewForm.rating = 1;
    // console.log();
    return Array.from({length: this.reviewForm.rating}, (_, i) => i + 1)
  }

  negativeStarRating(): Array<number> {
    return Array.from({length: 5 - this.reviewForm.rating}, (_, i) => i + 1)
    // return Array(5 - this.reviewForm.rating);
  }

  Rating(rate: number): Array<number> {
    // if (this.reviewForm.rating < 1) this.reviewForm.rating = 1;
    return Array.from({length: rate}, (_, i) => i + 1)
  }

  negativeRating(rate: number): Array<number> {
    return Array.from({length: 5 - rate}, (_, i) => i + 1)
    // return Array(5 - this.reviewForm.rating);
  }


  // numSequence(): Array<number> {
  //   // if (this.reviewForm.rating < 1) this.reviewForm.rating = 1;
  //   return Array(this.reviewForm.rating);
  // }


  updateReviewRating(event: any) {
    this.reviewForm.rating = parseInt(event);
  }


  checkIfUserExistsInReviews() {
    let userFound = false;
    this.reviews.forEach( review => {
      if (review.userId == this.userId) userFound = true;
    })
    return userFound;
  }



}