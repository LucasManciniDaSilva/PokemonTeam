package com.example.pokemondreamteam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PokemonDreamTeamApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemonDreamTeamApplication.class, args);
	}

}
