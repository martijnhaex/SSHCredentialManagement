package be.haexnet.sshcredentialmanagement.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = "be.haexnet.sshcredentialmanagement")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
