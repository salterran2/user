package user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UserApplication {

	private static final Logger log = LoggerFactory.getLogger(UserApplication.class);

	@Autowired
	private UserRepository userRepository;
	
	public static void main(String... args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {

		return args -> {
			ResponseEntity<List<User>> usersResponse = restTemplate.exchange(
					"https://jsonplaceholder.typicode.com/users", HttpMethod.GET, null,
					new ParameterizedTypeReference<List<User>>() {
					});
			List<User> users = usersResponse.getBody();

			for(User user: users) {
				userRepository.save(user);
			}
		};
	}
}