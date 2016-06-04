package ar.com.jalmeyda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Juan Almeyda on 6/3/2016.
 */
@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"ar.com.jalmeyda.controller", "ar.com.jalmeyda.job"})
public class ApplicationStarter {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationStarter.class, args);
    }
}
