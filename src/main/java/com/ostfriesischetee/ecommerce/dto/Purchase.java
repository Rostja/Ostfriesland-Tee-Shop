package com.ostfriesischetee.ecommerce.dto;

import com.ostfriesischetee.ecommerce.entity.Address;
import com.ostfriesischetee.ecommerce.entity.Customer;
import com.ostfriesischetee.ecommerce.entity.Order;
import com.ostfriesischetee.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}
