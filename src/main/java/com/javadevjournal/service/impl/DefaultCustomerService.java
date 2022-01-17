package com.javadevjournal.service.impl;

import com.javadevjournal.data.Customer;
import com.javadevjournal.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@CacheConfig(cacheNames = {"customer"})
public class DefaultCustomerService implements CustomerService {

    @Autowired
    CacheManager cacheManager;
    private static final Logger LOG = LoggerFactory.getLogger(DefaultCustomerService.class);

    @Cacheable
    @Override
    public Customer getCustomer(Long customerID) {
        LOG.info("Trying to get customer information for id {} ",customerID);
        return getCustomerData(customerID);
    }

    private Customer getCustomerData(final Long id){
        Customer customer = new Customer(id, "testemail@test.com", "Test Customer" + id);
        return  customer;
    }
    @Override
    public void getCachKeys() {
        LOG.info(cacheManager.getCache("customer").get(1l).get().toString());
    }
}
