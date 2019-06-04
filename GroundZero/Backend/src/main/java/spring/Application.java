package spring;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import spring.controller.Controller;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import spring.model.Cursa;

@SpringBootApplication
@ComponentScan(basePackageClasses = Controller.class)
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
