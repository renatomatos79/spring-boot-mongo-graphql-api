package bjss.learning.testroomapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class TestRoomApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestRoomApiApplication.class, args);
	}

}
