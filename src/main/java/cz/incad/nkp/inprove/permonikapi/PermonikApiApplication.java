package cz.incad.nkp.inprove.permonikapi;

import cz.incad.nkp.inprove.permonikapi.config.SearchContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({SearchContext.class })
public class PermonikApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(PermonikApiApplication.class, args);
	}

}
