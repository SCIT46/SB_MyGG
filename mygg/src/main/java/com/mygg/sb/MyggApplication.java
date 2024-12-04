package com.mygg.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication //(exclude = {DataSourceAutoConfiguration.class})
public class MyggApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyggApplication.class, args);
	}

}
