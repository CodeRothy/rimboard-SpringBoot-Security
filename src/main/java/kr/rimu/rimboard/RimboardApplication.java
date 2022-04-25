package kr.rimu.rimboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RimboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(RimboardApplication.class, args);
	}

}
