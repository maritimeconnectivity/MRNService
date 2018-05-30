package net.maritimeconnectivity.mrnservice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MRNValidationApplication extends SpringBootServletInitializer{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MRNValidationApplication.class);
    }

    public static void main(String[] args) {
        // Set awt to be headless to avoid issues when scaling images (logos)
        //System.setProperty("java.awt.headless", "true");
        // Set Bouncy Castle as Provider, used for Certificates.
        //Security.addProvider(new BouncyCastleProvider());
        SpringApplication.run(MRNValidationApplication.class, args);
    }
}
