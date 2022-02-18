import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { LoginComponent } from 'src/app/users/login/login.component';

import { ProductPageComponent } from './product-page.component';
import {HttpClient} from "@angular/common/http";
import {ProductService} from "../../services/product.service";
import {FormBuilder, FormsModule, NgControl, ReactiveFormsModule} from "@angular/forms";
import {ProductAndDiscountService} from "../../services/product-and-discount.service";
import {Product, ProductAndDiscount} from "../../models/product.model";
import {CartItem} from "../../models/cart.model";
import {CartItemService} from "../../services/cart-item.service";

describe('ProductPageComponent', () => {
  let component: ProductPageComponent;
  let fixture: ComponentFixture<ProductPageComponent>;
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;
  let productAndDiscountService: ProductAndDiscountService;
  let formTest: NgControl;
  let productService: ProductService;
  let cartItemService: CartItemService;

  beforeEach(async () => {

    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule, ReactiveFormsModule, FormsModule],
      declarations: [ ProductPageComponent ],
      providers: [ProductAndDiscountService, CartItemService, FormBuilder, NgControl]
    })
      .compileComponents();
    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
    productAndDiscountService = TestBed.inject(ProductAndDiscountService);
    cartItemService = TestBed.inject(CartItemService);
    formTest = TestBed.inject(NgControl);
  });



  beforeEach(() => {
    fixture = TestBed.createComponent(ProductPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  describe('#loadProductPage',() =>{
    let expectedProduct: any;
    beforeEach(() => {

      expectedProduct = {productId: 1, productSku:"I9P5XCXP1R913LC2PKEA693",productName:"Xbox One Controller",productCost:49.99,productCategory:"Gaming",
        productDescription:"Wireless Gaming Controller (Black)",productQty:9,
        imageUrl:"https://media.istockphoto.com/photos/xbox-one-controller-picture-id472036153?k=20&m=472036153&s=612x612&w=0&h=353Bz7VHG7lr8MdBbse9OkBT3wBFoFKl0Vqm2ivGwAE=",
        productRemoved:false};

    });

    //Test case 1
    it('should return expected product and discount details', () => {
      productAndDiscountService.getProductAndDiscountService(1).subscribe(
        product => expect(product).toEqual(expectedProduct,'should be null'),
        fail
      );

      let require = httpTestingController.match(productAndDiscountService.baseUrl + "/" + 1 + "/get");
      expect(require[0].request.method).toEqual('GET');
      require[0].flush(expectedProduct);
    });

    it('should get product and discount ', () => {
      productAndDiscountService.getProductAndDiscountService(1).subscribe(
        product => expect(product).toEqual(expectedProduct,'to be null'),
        fail
      );

      let require = httpTestingController.match(productAndDiscountService.baseUrl + "/" + 1 + "/get");
      expect(require[0].request.method).toEqual('GET');
      require[0].flush(expectedProduct);
    });

    //Test case 2
    it('should be OK not displaying a product', () =>{
      productAndDiscountService.getProductAndDiscountService(0).subscribe(
        prods => expect(0).toEqual(0,'productId is equal to 0'),
        fail
      );
      let require = httpTestingController.match(productAndDiscountService.baseUrl + "/" + 0 + "/get");
      expect(require[0].request.method).toEqual('GET');
      require[0].flush(null);
    });
  })

  describe('#addToCart',() =>{
    let addedItem: CartItem;
    let expectedItem: CartItem;
    beforeEach(() => {

      expectedItem = {
        cartItemId: 1,
        cartId: 2,
        productId: 3,
        cartQty: 4
      }

      addedItem = {
        cartItemId: 1,
        cartId: 2,
        productId: 3,
        cartQty: 4
      }


    });

    //Test case 1
    it('should POST expected item(s)', () => {
      cartItemService.addNewItemService(addedItem).subscribe(
        item => expect(item).toEqual(addedItem,'should  add cart item'),
        fail
      );

      const require = httpTestingController.match(cartItemService.baseUrl + "/post");
      expect(require[0].request.method).toEqual('POST');
      require[0].flush(expectedItem);
    });


  });

  describe('#removeFromCart',() =>{
    let addedItem: CartItem;
    let expectedItem: CartItem;
    beforeEach(() => {

      expectedItem = {
        cartItemId: 1,
        cartId: 2,
        productId: 3,
        cartQty: 4
      }

      addedItem = {
        cartItemId: 1,
        cartId: 2,
        productId: 3,
        cartQty: 4
      }


    });

    it('should update expected item(s)', () => {
      cartItemService.addNewItemService(addedItem).subscribe(
        item => expect(item).toEqual(addedItem,'should  add cart item'),
        fail
      );

      const require = httpTestingController.match(cartItemService.baseUrl + "/post");
      expect(require[0].request.method).toEqual('POST');
      require[0].flush(expectedItem);
    });


  });

});