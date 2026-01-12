package com.ostfriesischetee.ecommerce.service;

import com.ostfriesischetee.ecommerce.dto.Purchase;
import com.ostfriesischetee.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
