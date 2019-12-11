package org.srempfer.config.config.server;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigServer21Application {

	public static void main(String[] args) {
		SpringApplication.run( ConfigServer21Application.class, args);
	}

}
