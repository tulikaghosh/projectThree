package com.project3.revtech.service;

import com.project3.revtech.dao.CartItemRepository;
import com.project3.revtech.entity.Cart;
import com.project3.revtech.entity.CartItem;
import com.project3.revtech.entity.Discount;
import com.project3.revtech.entity.Product;
import com.project3.revtech.entity.Transaction;
import com.project3.revtech.entity.User;
import com.project3.revtech.exception.ApplicationException;
import com.project3.revtech.pojo.CartItemPojo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {CartItemServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CartItemServiceImplTest {

    @MockBean
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartItemServiceImpl cartItemServiceImpl;

    @Test
    void testAddItem() throws ApplicationException {
        Transaction transaction = new Transaction();
        transaction.setCartId(123);
        transaction.setTransactionDate(mock(Timestamp.class));
        transaction.setTransactionId(123);

        User user = new User();
        user.setAddress("42 Main St");
        user.setContact("Contact");
        user.setEmail("jane.doe@example.org");
        user.setFirst_name("Jane");
        user.setLast_name("Doe");
        user.setPassword("iloveyou");
        user.setRoles(new HashSet<>());
        user.setUser_id(1);
        user.setUsername("janedoe");

        Cart cart = new Cart();
        cart.setCartId(123);
        cart.setCartItems(new ArrayList<>());
        cart.setCartPaid(true);
        cart.setCartRemoved(true);
        cart.setCartTotal(1);
        cart.setTransaction(transaction);
        cart.setUser(user);
        cart.setUserId(123);

        Discount discount = new Discount();
        discount.setDiscountDescription("3");
        discount.setDiscountId(3);
        discount.setDiscountPercentage(null);
        discount.setProduct(new Product());
        discount.setProductId(123);

        Product product = new Product();
        product.setCartItems(new ArrayList<>());
        product.setDiscount(discount);
        product.setImageUrl("https://example.org/example");
        product.setImages(new ArrayList<>());
        product.setProductCategory("Product Category");
        product.setProductCost(BigDecimal.valueOf(42L));
        product.setProductDescription("Product Description");
        product.setProductId(123);
        product.setProductName("Product Name");
        product.setProductQty(1);
        product.setProductRemoved(true);
        product.setProductSku("Product Sku");

        Discount discount1 = new Discount();
        discount1.setDiscountDescription("3");
        discount1.setDiscountId(3);
        discount1.setDiscountPercentage(BigDecimal.valueOf(42L));
        discount1.setProduct(product);
        discount1.setProductId(123);

        Product product1 = new Product();
        product1.setCartItems(new ArrayList<>());
        product1.setDiscount(discount1);
        product1.setImageUrl("https://example.org/example");
        product1.setImages(new ArrayList<>());
        product1.setProductCategory("Product Category");
        product1.setProductCost(BigDecimal.valueOf(42L));
        product1.setProductDescription("Product Description");
        product1.setProductId(123);
        product1.setProductName("Product Name");
        product1.setProductQty(1);
        product1.setProductRemoved(true);
        product1.setProductSku("Product Sku");

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setCartId(123);
        cartItem.setCartItemId(123);
        cartItem.setCartQty(1);
        cartItem.setProduct(product1);
        cartItem.setProductId(123);
        doNothing().when(this.cartItemRepository).deleteById((Integer) any());
        when(this.cartItemRepository.existsByCartQtyIsLessThanAndCartIdAndProductId(anyInt(), anyInt(), anyInt()))
                .thenReturn(true);
        when(this.cartItemRepository.findByCartIdAndProductId(anyInt(), anyInt())).thenReturn(cartItem);
        when(this.cartItemRepository.existsByCartIdAndProductId(anyInt(), anyInt())).thenReturn(true);
        CartItemPojo cartItemPojo = new CartItemPojo(123, 123, 123, 1);

        CartItemPojo actualAddItemResult = this.cartItemServiceImpl.addItem(cartItemPojo);
        assertSame(cartItemPojo, actualAddItemResult);
        assertEquals(-1, actualAddItemResult.getCartItemId());
        verify(this.cartItemRepository).deleteById((Integer) any());
        verify(this.cartItemRepository).existsByCartIdAndProductId(anyInt(), anyInt());
        verify(this.cartItemRepository).existsByCartQtyIsLessThanAndCartIdAndProductId(anyInt(), anyInt(), anyInt());
        verify(this.cartItemRepository, atLeast(1)).findByCartIdAndProductId(anyInt(), anyInt());
    }

    @Test
    void testAddItem2() throws ApplicationException {
        Transaction transaction = new Transaction();
        transaction.setCartId(123);
        transaction.setTransactionDate(mock(Timestamp.class));
        transaction.setTransactionId(123);

        User user = new User();
        user.setAddress("42 Main St");
        user.setContact("Contact");
        user.setEmail("jane.doe@example.org");
        user.setFirst_name("Jane");
        user.setLast_name("Doe");
        user.setPassword("iloveyou");
        user.setRoles(new HashSet<>());
        user.setUser_id(1);
        user.setUsername("janedoe");

        Cart cart = new Cart();
        cart.setCartId(123);
        cart.setCartItems(new ArrayList<>());
        cart.setCartPaid(true);
        cart.setCartRemoved(true);
        cart.setCartTotal(1);
        cart.setTransaction(transaction);
        cart.setUser(user);
        cart.setUserId(123);

        Discount discount = new Discount();
        discount.setDiscountDescription("3");
        discount.setDiscountId(3);
        discount.setDiscountPercentage(null);
        discount.setProduct(new Product());
        discount.setProductId(123);

        Product product = new Product();
        product.setCartItems(new ArrayList<>());
        product.setDiscount(discount);
        product.setImageUrl("https://example.org/example");
        product.setImages(new ArrayList<>());
        product.setProductCategory("Product Category");
        product.setProductCost(BigDecimal.valueOf(42L));
        product.setProductDescription("Product Description");
        product.setProductId(123);
        product.setProductName("Product Name");
        product.setProductQty(1);
        product.setProductRemoved(true);
        product.setProductSku("Product Sku");

        Discount discount1 = new Discount();
        discount1.setDiscountDescription("3");
        discount1.setDiscountId(3);
        discount1.setDiscountPercentage(BigDecimal.valueOf(42L));
        discount1.setProduct(product);
        discount1.setProductId(123);

        Product product1 = new Product();
        product1.setCartItems(new ArrayList<>());
        product1.setDiscount(discount1);
        product1.setImageUrl("https://example.org/example");
        product1.setImages(new ArrayList<>());
        product1.setProductCategory("Product Category");
        product1.setProductCost(BigDecimal.valueOf(42L));
        product1.setProductDescription("Product Description");
        product1.setProductId(123);
        product1.setProductName("Product Name");
        product1.setProductQty(1);
        product1.setProductRemoved(true);
        product1.setProductSku("Product Sku");

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setCartId(123);
        cartItem.setCartItemId(123);
        cartItem.setCartQty(1);
        cartItem.setProduct(product1);
        cartItem.setProductId(123);

        Transaction transaction1 = new Transaction();
        transaction1.setCartId(123);
        transaction1.setTransactionDate(mock(Timestamp.class));
        transaction1.setTransactionId(123);

        User user1 = new User();
        user1.setAddress("42 Main St");
        user1.setContact("Contact");
        user1.setEmail("jane.doe@example.org");
        user1.setFirst_name("Jane");
        user1.setLast_name("Doe");
        user1.setPassword("iloveyou");
        user1.setRoles(new HashSet<>());
        user1.setUser_id(1);
        user1.setUsername("janedoe");

        Cart cart1 = new Cart();
        cart1.setCartId(123);
        cart1.setCartItems(new ArrayList<>());
        cart1.setCartPaid(true);
        cart1.setCartRemoved(true);
        cart1.setCartTotal(1);
        cart1.setTransaction(transaction1);
        cart1.setUser(user1);
        cart1.setUserId(123);

        Discount discount2 = new Discount();
        discount2.setDiscountDescription("3");
        discount2.setDiscountId(3);
        discount2.setDiscountPercentage(null);
        discount2.setProduct(new Product());
        discount2.setProductId(123);

        Product product2 = new Product();
        product2.setCartItems(new ArrayList<>());
        product2.setDiscount(discount2);
        product2.setImageUrl("https://example.org/example");
        product2.setImages(new ArrayList<>());
        product2.setProductCategory("Product Category");
        product2.setProductCost(BigDecimal.valueOf(42L));
        product2.setProductDescription("Product Description");
        product2.setProductId(123);
        product2.setProductName("Product Name");
        product2.setProductQty(1);
        product2.setProductRemoved(true);
        product2.setProductSku("Product Sku");

        Discount discount3 = new Discount();
        discount3.setDiscountDescription("3");
        discount3.setDiscountId(3);
        discount3.setDiscountPercentage(BigDecimal.valueOf(42L));
        discount3.setProduct(product2);
        discount3.setProductId(123);

        Product product3 = new Product();
        product3.setCartItems(new ArrayList<>());
        product3.setDiscount(discount3);
        product3.setImageUrl("https://example.org/example");
        product3.setImages(new ArrayList<>());
        product3.setProductCategory("Product Category");
        product3.setProductCost(BigDecimal.valueOf(42L));
        product3.setProductDescription("Product Description");
        product3.setProductId(123);
        product3.setProductName("Product Name");
        product3.setProductQty(1);
        product3.setProductRemoved(true);
        product3.setProductSku("Product Sku");

        CartItem cartItem1 = new CartItem();
        cartItem1.setCart(cart1);
        cartItem1.setCartId(123);
        cartItem1.setCartItemId(123);
        cartItem1.setCartQty(1);
        cartItem1.setProduct(product3);
        cartItem1.setProductId(123);
        when(this.cartItemRepository.save((CartItem) any())).thenReturn(cartItem1);
        doNothing().when(this.cartItemRepository).deleteById((Integer) any());
        when(this.cartItemRepository.existsByCartQtyIsLessThanAndCartIdAndProductId(anyInt(), anyInt(), anyInt()))
                .thenReturn(false);
        when(this.cartItemRepository.findByCartIdAndProductId(anyInt(), anyInt())).thenReturn(cartItem);
        when(this.cartItemRepository.existsByCartIdAndProductId(anyInt(), anyInt())).thenReturn(true);
        CartItemPojo cartItemPojo = new CartItemPojo(123, 123, 123, 1);

        CartItemPojo actualAddItemResult = this.cartItemServiceImpl.addItem(cartItemPojo);
        assertSame(cartItemPojo, actualAddItemResult);
        assertEquals(123, actualAddItemResult.getCartItemId());
        verify(this.cartItemRepository).existsByCartIdAndProductId(anyInt(), anyInt());
        verify(this.cartItemRepository).existsByCartQtyIsLessThanAndCartIdAndProductId(anyInt(), anyInt(), anyInt());
        verify(this.cartItemRepository, atLeast(1)).findByCartIdAndProductId(anyInt(), anyInt());
        verify(this.cartItemRepository).save((CartItem) any());
    }

    @Test
    void testUpdateItem() throws ApplicationException {
        Transaction transaction = new Transaction();
        transaction.setCartId(123);
        transaction.setTransactionDate(mock(Timestamp.class));
        transaction.setTransactionId(123);

        User user = new User();
        user.setAddress("42 Main St");
        user.setContact("Contact");
        user.setEmail("jane.doe@example.org");
        user.setFirst_name("Jane");
        user.setLast_name("Doe");
        user.setPassword("iloveyou");
        user.setRoles(new HashSet<>());
        user.setUser_id(1);
        user.setUsername("janedoe");

        Cart cart = new Cart();
        cart.setCartId(123);
        cart.setCartItems(new ArrayList<>());
        cart.setCartPaid(true);
        cart.setCartRemoved(true);
        cart.setCartTotal(1);
        cart.setTransaction(transaction);
        cart.setUser(user);
        cart.setUserId(123);

        Discount discount = new Discount();
        discount.setDiscountDescription("3");
        discount.setDiscountId(3);
        discount.setDiscountPercentage(null);
        discount.setProduct(new Product());
        discount.setProductId(123);

        Product product = new Product();
        product.setCartItems(new ArrayList<>());
        product.setDiscount(discount);
        product.setImageUrl("https://example.org/example");
        product.setImages(new ArrayList<>());
        product.setProductCategory("Product Category");
        product.setProductCost(BigDecimal.valueOf(42L));
        product.setProductDescription("Product Description");
        product.setProductId(123);
        product.setProductName("Product Name");
        product.setProductQty(1);
        product.setProductRemoved(true);
        product.setProductSku("Product Sku");

        Discount discount1 = new Discount();
        discount1.setDiscountDescription("3");
        discount1.setDiscountId(3);
        discount1.setDiscountPercentage(BigDecimal.valueOf(42L));
        discount1.setProduct(product);
        discount1.setProductId(123);

        Product product1 = new Product();
        product1.setCartItems(new ArrayList<>());
        product1.setDiscount(discount1);
        product1.setImageUrl("https://example.org/example");
        product1.setImages(new ArrayList<>());
        product1.setProductCategory("Product Category");
        product1.setProductCost(BigDecimal.valueOf(42L));
        product1.setProductDescription("Product Description");
        product1.setProductId(123);
        product1.setProductName("Product Name");
        product1.setProductQty(1);
        product1.setProductRemoved(true);
        product1.setProductSku("Product Sku");

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setCartId(123);
        cartItem.setCartItemId(123);
        cartItem.setCartQty(1);
        cartItem.setProduct(product1);
        cartItem.setProductId(123);
        doNothing().when(this.cartItemRepository).deleteById((Integer) any());
        when(this.cartItemRepository.existsByCartQtyIsLessThanAndCartIdAndProductId(anyInt(), anyInt(), anyInt()))
                .thenReturn(true);
        when(this.cartItemRepository.findByCartIdAndProductId(anyInt(), anyInt())).thenReturn(cartItem);
        CartItemPojo cartItemPojo = new CartItemPojo(123, 123, 123, 1);

        CartItemPojo actualUpdateItemResult = this.cartItemServiceImpl.updateItem(cartItemPojo);
        assertSame(cartItemPojo, actualUpdateItemResult);
        assertEquals(-1, actualUpdateItemResult.getCartItemId());
        verify(this.cartItemRepository).deleteById((Integer) any());
        verify(this.cartItemRepository).existsByCartQtyIsLessThanAndCartIdAndProductId(anyInt(), anyInt(), anyInt());
        verify(this.cartItemRepository).findByCartIdAndProductId(anyInt(), anyInt());
    }

    @Test
    void testUpdateItem2() throws ApplicationException {
        Transaction transaction = new Transaction();
        transaction.setCartId(123);
        transaction.setTransactionDate(mock(Timestamp.class));
        transaction.setTransactionId(123);

        User user = new User();
        user.setAddress("42 Main St");
        user.setContact("Contact");
        user.setEmail("jane.doe@example.org");
        user.setFirst_name("Jane");
        user.setLast_name("Doe");
        user.setPassword("iloveyou");
        user.setRoles(new HashSet<>());
        user.setUser_id(1);
        user.setUsername("janedoe");

        Cart cart = new Cart();
        cart.setCartId(123);
        cart.setCartItems(new ArrayList<>());
        cart.setCartPaid(true);
        cart.setCartRemoved(true);
        cart.setCartTotal(1);
        cart.setTransaction(transaction);
        cart.setUser(user);
        cart.setUserId(123);

        Discount discount = new Discount();
        discount.setDiscountDescription("3");
        discount.setDiscountId(3);
        discount.setDiscountPercentage(null);
        discount.setProduct(new Product());
        discount.setProductId(123);

        Product product = new Product();
        product.setCartItems(new ArrayList<>());
        product.setDiscount(discount);
        product.setImageUrl("https://example.org/example");
        product.setImages(new ArrayList<>());
        product.setProductCategory("Product Category");
        product.setProductCost(BigDecimal.valueOf(42L));
        product.setProductDescription("Product Description");
        product.setProductId(123);
        product.setProductName("Product Name");
        product.setProductQty(1);
        product.setProductRemoved(true);
        product.setProductSku("Product Sku");

        Discount discount1 = new Discount();
        discount1.setDiscountDescription("3");
        discount1.setDiscountId(3);
        discount1.setDiscountPercentage(BigDecimal.valueOf(42L));
        discount1.setProduct(product);
        discount1.setProductId(123);

        Product product1 = new Product();
        product1.setCartItems(new ArrayList<>());
        product1.setDiscount(discount1);
        product1.setImageUrl("https://example.org/example");
        product1.setImages(new ArrayList<>());
        product1.setProductCategory("Product Category");
        product1.setProductCost(BigDecimal.valueOf(42L));
        product1.setProductDescription("Product Description");
        product1.setProductId(123);
        product1.setProductName("Product Name");
        product1.setProductQty(1);
        product1.setProductRemoved(true);
        product1.setProductSku("Product Sku");

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setCartId(123);
        cartItem.setCartItemId(123);
        cartItem.setCartQty(1);
        cartItem.setProduct(product1);
        cartItem.setProductId(123);

        Transaction transaction1 = new Transaction();
        transaction1.setCartId(123);
        transaction1.setTransactionDate(mock(Timestamp.class));
        transaction1.setTransactionId(123);

        User user1 = new User();
        user1.setAddress("42 Main St");
        user1.setContact("Contact");
        user1.setEmail("jane.doe@example.org");
        user1.setFirst_name("Jane");
        user1.setLast_name("Doe");
        user1.setPassword("iloveyou");
        user1.setRoles(new HashSet<>());
        user1.setUser_id(1);
        user1.setUsername("janedoe");

        Cart cart1 = new Cart();
        cart1.setCartId(123);
        cart1.setCartItems(new ArrayList<>());
        cart1.setCartPaid(true);
        cart1.setCartRemoved(true);
        cart1.setCartTotal(1);
        cart1.setTransaction(transaction1);
        cart1.setUser(user1);
        cart1.setUserId(123);

        Discount discount2 = new Discount();
        discount2.setDiscountDescription("3");
        discount2.setDiscountId(3);
        discount2.setDiscountPercentage(null);
        discount2.setProduct(new Product());
        discount2.setProductId(123);

        Product product2 = new Product();
        product2.setCartItems(new ArrayList<>());
        product2.setDiscount(discount2);
        product2.setImageUrl("https://example.org/example");
        product2.setImages(new ArrayList<>());
        product2.setProductCategory("Product Category");
        product2.setProductCost(BigDecimal.valueOf(42L));
        product2.setProductDescription("Product Description");
        product2.setProductId(123);
        product2.setProductName("Product Name");
        product2.setProductQty(1);
        product2.setProductRemoved(true);
        product2.setProductSku("Product Sku");

        Discount discount3 = new Discount();
        discount3.setDiscountDescription("3");
        discount3.setDiscountId(3);
        discount3.setDiscountPercentage(BigDecimal.valueOf(42L));
        discount3.setProduct(product2);
        discount3.setProductId(123);

        Product product3 = new Product();
        product3.setCartItems(new ArrayList<>());
        product3.setDiscount(discount3);
        product3.setImageUrl("https://example.org/example");
        product3.setImages(new ArrayList<>());
        product3.setProductCategory("Product Category");
        product3.setProductCost(BigDecimal.valueOf(42L));
        product3.setProductDescription("Product Description");
        product3.setProductId(123);
        product3.setProductName("Product Name");
        product3.setProductQty(1);
        product3.setProductRemoved(true);
        product3.setProductSku("Product Sku");

        CartItem cartItem1 = new CartItem();
        cartItem1.setCart(cart1);
        cartItem1.setCartId(123);
        cartItem1.setCartItemId(123);
        cartItem1.setCartQty(1);
        cartItem1.setProduct(product3);
        cartItem1.setProductId(123);
        when(this.cartItemRepository.save((CartItem) any())).thenReturn(cartItem1);
        doNothing().when(this.cartItemRepository).deleteById((Integer) any());
        when(this.cartItemRepository.existsByCartQtyIsLessThanAndCartIdAndProductId(anyInt(), anyInt(), anyInt()))
                .thenReturn(false);
        when(this.cartItemRepository.findByCartIdAndProductId(anyInt(), anyInt())).thenReturn(cartItem);
        CartItemPojo cartItemPojo = new CartItemPojo(123, 123, 123, 1);

        CartItemPojo actualUpdateItemResult = this.cartItemServiceImpl.updateItem(cartItemPojo);
        assertSame(cartItemPojo, actualUpdateItemResult);
        assertEquals(123, actualUpdateItemResult.getCartItemId());
        verify(this.cartItemRepository).existsByCartQtyIsLessThanAndCartIdAndProductId(anyInt(), anyInt(), anyInt());
        verify(this.cartItemRepository).findByCartIdAndProductId(anyInt(), anyInt());
        verify(this.cartItemRepository).save((CartItem) any());
    }

    @Test
    void testGetCartItem() throws ApplicationException {
        assertNull(this.cartItemServiceImpl.getCartItem(42));
    }

    @Test
    void testRemoveItem() throws ApplicationException {
        doNothing().when(this.cartItemRepository).deleteById((Integer) any());
        assertTrue(this.cartItemServiceImpl.removeItem(123));
        verify(this.cartItemRepository).deleteById((Integer) any());
    }

    @Test
    void testCheckIfExistsInCart() throws ApplicationException {
        when(this.cartItemRepository.existsByCartIdAndProductId(anyInt(), anyInt())).thenReturn(true);
        assertTrue(this.cartItemServiceImpl.checkIfExistsInCart(123, 123));
        verify(this.cartItemRepository).existsByCartIdAndProductId(anyInt(), anyInt());
    }

    @Test
    void testCheckIfExistsInCart2() throws ApplicationException {
        when(this.cartItemRepository.existsByCartIdAndProductId(anyInt(), anyInt())).thenReturn(false);
        assertFalse(this.cartItemServiceImpl.checkIfExistsInCart(123, 123));
        verify(this.cartItemRepository).existsByCartIdAndProductId(anyInt(), anyInt());
    }

    @Test
    void testCheckIfNoQty() throws ApplicationException {
        when(this.cartItemRepository.existsByCartQtyIsLessThanAndCartIdAndProductId(anyInt(), anyInt(), anyInt()))
                .thenReturn(true);
        assertTrue(this.cartItemServiceImpl.checkIfNoQty(123, 123));
        verify(this.cartItemRepository).existsByCartQtyIsLessThanAndCartIdAndProductId(anyInt(), anyInt(), anyInt());
    }

    @Test
    void testCheckIfNoQty2() throws ApplicationException {
        when(this.cartItemRepository.existsByCartQtyIsLessThanAndCartIdAndProductId(anyInt(), anyInt(), anyInt()))
                .thenReturn(false);
        assertFalse(this.cartItemServiceImpl.checkIfNoQty(123, 123));
        verify(this.cartItemRepository).existsByCartQtyIsLessThanAndCartIdAndProductId(anyInt(), anyInt(), anyInt());
    }


}