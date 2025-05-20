package lt.viko.eif.mstrimaitis;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;

@SpringBootApplication
public class RestApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        InetAddress localhost = InetAddress.getLocalHost();
        String hostname = localhost.getCanonicalHostName();

        System.out.println("Full Hostname: " + hostname);

        String baseUrl = "http://" + "localhost" + ":8080";
        System.out.println("Available API Endpoints:");
        // T-34 PAKEISTI I NORMAL localhost
        System.out.println(baseUrl + "/api/farmers");
        System.out.println(baseUrl + "/api/worker");
        System.out.println(baseUrl + "/api/farmerworker");
    }
}