package com.javadevjournal;

import com.javadevjournal.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CaffeineCacheApp implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(CaffeineCacheApp.class);

    @Autowired
    CustomerService customerService;

    @Override
    public void run(String... args) throws Exception {

        LOG.info("Starting the Caffeine cache testing process");
        customerService.getCustomer(1l) ; //Caching data
        customerService.getCustomer(2l) ; //Caching data
        customerService.getCustomer(1l) ; //Will be read up from cache so nothing is printed out
        customerService.getCustomer(1l) ; //Will be read up from cache so nothing is printed out
        customerService.getCustomer(1l) ; //Will be read up from cache so nothing is printed out
        customerService.getCustomer(1l) ; //Will be read up from cache so nothing is printed out
        customerService.getCachKeys(); //Testing to see if used with id of 1 is giving back any value - cached properly
    }
}
