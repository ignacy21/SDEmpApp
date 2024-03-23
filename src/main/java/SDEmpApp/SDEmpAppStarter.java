package SDEmpApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class SDEmpAppStarter {

    public static void main(String[] args) {
        SpringApplication.run(SDEmpAppStarter.class, args);
    }

}