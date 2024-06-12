package com.msc.ms.authentification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MscMsAuthentificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscMsAuthentificationApplication.class, args);
	}

}
