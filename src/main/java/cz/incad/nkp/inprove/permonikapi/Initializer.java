package cz.incad.nkp.inprove.permonikapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "cz.incad.nkp.inprove.permonikapi")
public class Initializer {
	public static void main(String[] args) {
		SpringApplication.run(Initializer.class, args);
	}

}