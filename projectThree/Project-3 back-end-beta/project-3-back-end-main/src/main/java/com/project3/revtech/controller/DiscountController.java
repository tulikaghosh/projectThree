package com.project3.revtech.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project3.revtech.pojo.DiscountPojo;
import com.project3.revtech.exception.ApplicationException;
import com.project3.revtech.service.DiscountService;

@RestController
@CrossOrigin
@RequestMapping("discounts")
public class DiscountController {

	@Autowired
	DiscountService discountService;
	
	@GetMapping("all")
	List<DiscountPojo> getAllDiscounts() throws ApplicationException{
		return discountService.getAllDiscounts();
	}
	
	@PostMapping("add")
	DiscountPojo addDiscount( @Valid @RequestBody DiscountPojo discount) throws ApplicationException{
		return discountService.addDiscount(discount);
	}
	
	@DeleteMapping("remove/{discId}")
	boolean removeDiscount(@PathVariable("discId") int discId) throws ApplicationException{
		return discountService.removeDiscount(discId);
	}	
	@PutMapping("update")
	DiscountPojo updateDiscount(@Valid @RequestBody DiscountPojo discountPojo) throws ApplicationException{
		return discountService.updateDiscount(discountPojo);
	}
}
