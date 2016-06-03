package ar.com.jalmeyda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Juan Almeyda on 6/3/2016.
 */
@SpringBootApplication
@ComponentScan(basePackages = {"ar.com.jalmeyda.controller"})
public class ApplicationStarter {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationStarter.class, args);
    }
}
