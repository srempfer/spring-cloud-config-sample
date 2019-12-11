package org.srempfer.config.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ConfigClient15Application {

	public static void main(String[] args) {
		SpringApplication.run( ConfigClient15Application.class, args);
	}

    @Bean
    public CommandLineRunner propertyPrinter ( Environment env ) {
        return runner -> {
            String value = env.getProperty ( "client.test.key" );
            System.out.println ( ">>>>>>> Property resolved from config server >>> " + value + " <<<<" );
        };
    }

}
