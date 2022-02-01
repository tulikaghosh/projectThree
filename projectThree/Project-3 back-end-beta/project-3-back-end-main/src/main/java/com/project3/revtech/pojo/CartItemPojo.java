package com.project3.revtech.pojo;


import java.util.Objects;

public class CartItemPojo {

    private int cartItemId;
    private int cartId;
    private int productId;
    private int cartQty;

    public CartItemPojo(int cartItemId, int cartId, int productId, int cartQty) {
        this.cartItemId = cartItemId;
        this.cartId = cartId;
        this.productId = productId;
        this.cartQty = cartQty;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCartQty() {
        return cartQty;
    }

    public void setCartQty(int cartQty) {
        this.cartQty = cartQty;
    }

    @Override
    public String toString() {
        return "CartItemPojo{" +
                "cartItemId=" + cartItemId +
                ", cartId=" + cartId +
                ", productId=" + productId +
                ", cartQty=" + cartQty +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItemPojo that = (CartItemPojo) o;
        return cartItemId == that.cartItemId && cartId == that.cartId && productId == that.productId && cartQty == that.cartQty;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartItemId, cartId, productId, cartQty);
    }
}
