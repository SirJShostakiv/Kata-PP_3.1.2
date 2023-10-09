package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@PropertySource("classpath:application.properties")
@EnableTransactionManagement
@SpringBootApplication
@EntityScan("app.model")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
