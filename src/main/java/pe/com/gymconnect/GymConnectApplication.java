package pe.com.gymconnect;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
public class GymConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymConnectApplication.class, args);
	}


}
