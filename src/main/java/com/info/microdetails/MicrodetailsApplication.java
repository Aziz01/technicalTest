package com.info.microdetails;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class MicrodetailsApplication {

    public static void main(String[] args) {

        log.info("***Starting Application to create and find a specific user***");
        SpringApplication.run(MicrodetailsApplication.class, args);
    }

}
