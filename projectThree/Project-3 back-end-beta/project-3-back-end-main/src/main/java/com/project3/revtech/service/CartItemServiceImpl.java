package com.project3.revtech.service;

import com.project3.revtech.dao.CartItemRepository;
import com.project3.revtech.entity.CartItem;
import com.project3.revtech.exception.ApplicationException;
import com.project3.revtech.pojo.CartItemPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CartItemServiceImpl implements CartItemService{

    @Autowired
    CartItemRepository cartItemRepository;

    @Override
    public CartItemPojo addItem(CartItemPojo item) throws ApplicationException {
        if(item.getCartQty() < 1) {
            item.setCartItemId(-1);
            return item;
        }
        if(this.checkIfExistsInCart(item.getCartId(), item.getProductId())) {
            CartItem existingItem = cartItemRepository.findByCartIdAndProductId(item.getCartId(), item.getProductId());
            item.setCartItemId(existingItem.getCartItemId());
            return this.updateItem(item);
        } else {
            CartItem itemEntity = new CartItem( item.getCartId(), item.getProductId(), item.getCartQty());
            CartItem returningItem = cartItemRepository.saveAndFlush(itemEntity);
            item.setCartItemId(returningItem.getCartItemId());
        }
        return item;
    }

    @Override
    public CartItemPojo updateItem(CartItemPojo item) throws ApplicationException {
        CartItem existingItem = cartItemRepository.findByCartIdAndProductId(item.getCartId(), item.getProductId());
        if(existingItem == null) return addItem(item);
        item.setCartItemId(existingItem.getCartItemId());
        if(this.checkIfNoQty(item.getCartId(), item.getProductId()) || item.getCartQty() < 1) {
            this.removeItem(item.getCartItemId());
            item.setCartItemId(-1);
        } else {
            CartItem itemEntity = new CartItem(item.getCartItemId(), item.getCartId(), item.getProductId(), item.getCartQty());
//            CartItem returningItem = cartItemRepository.
            CartItem returningItem = cartItemRepository.save(itemEntity);
        }

        return item;
    }

    @Override
    public boolean removeItem(int itemId) throws ApplicationException {
        cartItemRepository.deleteById(itemId);
        return true;
    }

    @Override
    public boolean checkIfExistsInCart(int cartId, int productId) throws ApplicationException {

        return cartItemRepository.existsByCartIdAndProductId(cartId, productId);
    }

    @Override
    public boolean checkIfNoQty(int cartId, int productId) throws ApplicationException {
        return cartItemRepository.existsByCartQtyIsLessThanAndCartIdAndProductId(1, cartId, productId);
    }
}
