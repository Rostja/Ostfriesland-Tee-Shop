package com.ostfriesischetee.ecommerce.service;

import com.ostfriesischetee.ecommerce.dao.CustomerRepository;
import com.ostfriesischetee.ecommerce.dto.Purchase;
import com.ostfriesischetee.ecommerce.dto.PurchaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private CustomerRepository customerRepository;

    @Autowired
    private CheckoutServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    @Override
    public PurchaseResponse placeOrder(Purchase purchase) {
        return null;
    }
}
