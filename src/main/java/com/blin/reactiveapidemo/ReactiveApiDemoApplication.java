package com.blin.reactiveapidemo;

import com.blin.reactiveapidemo.model.User;
import com.blin.reactiveapidemo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ReactiveApiDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveApiDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner run(UserRepository userRepository) {
		return args -> {
			userRepository.deleteAll()
					.thenMany(Flux.just(
									new User("Harrier Du Bois", 44, 1),
									new User("Kim Kitsuragi", 43, 3200),
									new User("Insulindian Phasmid", 3700, 9000)
							)
							.flatMap(userRepository::save))
					.thenMany(userRepository.findAll())
					.subscribe(System.out::println);
		};
	}
}
