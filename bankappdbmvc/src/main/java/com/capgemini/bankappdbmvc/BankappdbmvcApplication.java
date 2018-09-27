package com.capgemini.bankappdbmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.capgemini.bankappdbmvc")
public class BankappdbmvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankappdbmvcApplication.class, args);
	}
}
