package com.project3.revtech.service;

import com.project3.revtech.dao.TransactionRepository;
import com.project3.revtech.entity.Transaction;
import com.project3.revtech.pojo.TransactionPojo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.project3.revtech.prototype.TransactionPrototype.transactionPojoTestObj;
import static com.project3.revtech.prototype.TransactionPrototype.transactionTestObj;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {TransactionServiceImpl.class})
@ExtendWith(SpringExtension.class)
class TransactionServiceImplTest {

    @MockBean
    TransactionRepository transactionRepository;

    @Autowired
    TransactionServiceImpl transactionService;


    @Autowired
    private TransactionServiceImpl transactionServiceImpl;

    @Test
    void testGetAllTransactions() {
        when(this.transactionRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(this.transactionServiceImpl.getAllTransactions().isEmpty());
        verify(this.transactionRepository).findAll();
    }

    @Test
    void testGetAllTransactions2() {
        Transaction transaction = new Transaction();
        transaction.setCartId(123);
        transaction.setTransactionDate(mock(Timestamp.class));
        transaction.setTransactionId(123);

        ArrayList<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        when(this.transactionRepository.findAll()).thenReturn(transactionList);
        List<TransactionPojo> actualAllTransactions = this.transactionServiceImpl.getAllTransactions();
        assertEquals(1, actualAllTransactions.size());
        TransactionPojo getResult = actualAllTransactions.get(0);
        assertEquals(123, getResult.getCartId());
        assertEquals(123, getResult.getTransactionId());
        verify(this.transactionRepository).findAll();
    }

    @Test
    void testGetAllTransactions3() {
        Transaction transaction = new Transaction();
        transaction.setCartId(123);
        transaction.setTransactionDate(mock(Timestamp.class));
        transaction.setTransactionId(123);

        Transaction transaction1 = new Transaction();
        transaction1.setCartId(123);
        transaction1.setTransactionDate(mock(Timestamp.class));
        transaction1.setTransactionId(123);

        ArrayList<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction1);
        transactionList.add(transaction);
        when(this.transactionRepository.findAll()).thenReturn(transactionList);
        List<TransactionPojo> actualAllTransactions = this.transactionServiceImpl.getAllTransactions();
        assertEquals(2, actualAllTransactions.size());
        TransactionPojo getResult = actualAllTransactions.get(1);
        assertEquals(123, getResult.getTransactionId());
        assertEquals(123, getResult.getCartId());
        TransactionPojo getResult1 = actualAllTransactions.get(0);
        assertEquals(123, getResult1.getTransactionId());
        assertEquals(123, getResult1.getCartId());
        verify(this.transactionRepository).findAll();
    }

    @Test
    void testGetTransactionById() {
        Transaction transaction = new Transaction();
        transaction.setCartId(123);
        transaction.setTransactionDate(mock(Timestamp.class));
        transaction.setTransactionId(123);
        when(this.transactionRepository.getById((Integer) any())).thenReturn(transaction);
        TransactionPojo actualTransactionById = this.transactionServiceImpl.getTransactionById(123);
        assertEquals(123, actualTransactionById.getCartId());
        assertEquals(123, actualTransactionById.getTransactionId());
        verify(this.transactionRepository).getById((Integer) any());
        assertTrue(this.transactionServiceImpl.getAllTransactions().isEmpty());
    }

    @Test
    void testFindAllTransactionsInCart() {
        when(this.transactionRepository.findAllByCartId(anyInt())).thenReturn(new ArrayList<>());
        assertTrue(this.transactionServiceImpl.findAllTransactionsInCart(123).isEmpty());
        verify(this.transactionRepository).findAllByCartId(anyInt());
        assertTrue(this.transactionServiceImpl.getAllTransactions().isEmpty());
    }

    @Test
    void testFindAllTransactionsInCart2() {
        Transaction transaction = new Transaction();
        transaction.setCartId(123);
        transaction.setTransactionDate(mock(Timestamp.class));
        transaction.setTransactionId(123);

        ArrayList<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        when(this.transactionRepository.findAllByCartId(anyInt())).thenReturn(transactionList);
        List<TransactionPojo> actualFindAllTransactionsInCartResult = this.transactionServiceImpl
                .findAllTransactionsInCart(123);
        assertEquals(1, actualFindAllTransactionsInCartResult.size());
        TransactionPojo getResult = actualFindAllTransactionsInCartResult.get(0);
        assertEquals(123, getResult.getCartId());
        assertEquals(123, getResult.getTransactionId());
        verify(this.transactionRepository).findAllByCartId(anyInt());
        assertTrue(this.transactionServiceImpl.getAllTransactions().isEmpty());
    }

    @Test
    void testFindAllTransactionsInCart3() {
        Transaction transaction = new Transaction();
        transaction.setCartId(123);
        transaction.setTransactionDate(mock(Timestamp.class));
        transaction.setTransactionId(123);

        Transaction transaction1 = new Transaction();
        transaction1.setCartId(123);
        transaction1.setTransactionDate(mock(Timestamp.class));
        transaction1.setTransactionId(123);

        ArrayList<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction1);
        transactionList.add(transaction);
        when(this.transactionRepository.findAllByCartId(anyInt())).thenReturn(transactionList);
        List<TransactionPojo> actualFindAllTransactionsInCartResult = this.transactionServiceImpl
                .findAllTransactionsInCart(123);
        assertEquals(2, actualFindAllTransactionsInCartResult.size());
        TransactionPojo getResult = actualFindAllTransactionsInCartResult.get(1);
        assertEquals(123, getResult.getTransactionId());
        assertEquals(123, getResult.getCartId());
        TransactionPojo getResult1 = actualFindAllTransactionsInCartResult.get(0);
        assertEquals(123, getResult1.getTransactionId());
        assertEquals(123, getResult1.getCartId());
        verify(this.transactionRepository).findAllByCartId(anyInt());
        assertTrue(this.transactionServiceImpl.getAllTransactions().isEmpty());
    }

    @Test
    void testCreateTransaction() {
        Transaction transaction = new Transaction();
        transaction.setCartId(123);
        transaction.setTransactionDate(mock(Timestamp.class));
        transaction.setTransactionId(123);
        when(this.transactionRepository.saveAndFlush((Transaction) any())).thenReturn(transaction);
        TransactionPojo transactionPojo = new TransactionPojo(123, mock(Timestamp.class), 123);

        TransactionPojo actualCreateTransactionResult = this.transactionServiceImpl.createTransaction(transactionPojo);
        assertSame(transactionPojo, actualCreateTransactionResult);
        assertEquals(123, actualCreateTransactionResult.getCartId());
        assertEquals(123, actualCreateTransactionResult.getTransactionId());
        verify(this.transactionRepository).saveAndFlush((Transaction) any());
        assertTrue(this.transactionServiceImpl.getAllTransactions().isEmpty());
    }

    @Test
    void testDeleteTransaction() {
        doNothing().when(this.transactionRepository).delete((com.project3.revtech.entity.Transaction) any());
        TransactionPojo transactionPojo = new TransactionPojo(123, mock(Timestamp.class), 123);

        assertSame(transactionPojo, this.transactionServiceImpl.deleteTransaction(transactionPojo));
        verify(this.transactionRepository).delete((com.project3.revtech.entity.Transaction) any());
        assertTrue(this.transactionServiceImpl.getAllTransactions().isEmpty());
    }

    @Test
    void testUpdateTransaction() {
        Transaction transaction = new Transaction();
        transaction.setCartId(123);
        transaction.setTransactionDate(mock(Timestamp.class));
        transaction.setTransactionId(123);
        when(this.transactionRepository.saveAndFlush((Transaction) any())).thenReturn(transaction);
        TransactionPojo transactionPojo = new TransactionPojo(123, mock(Timestamp.class), 123);

        assertSame(transactionPojo, this.transactionServiceImpl.updateTransaction(transactionPojo));
        verify(this.transactionRepository).saveAndFlush((Transaction) any());
        assertTrue(this.transactionServiceImpl.getAllTransactions().isEmpty());
    }

}