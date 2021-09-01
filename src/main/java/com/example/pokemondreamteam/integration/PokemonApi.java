package com.example.pokemondreamteam.integration;

import com.example.pokemondreamteam.documents.PokemonDocument;
import com.example.pokemondreamteam.exceptions.MessageError;
import com.example.pokemondreamteam.exceptions.NotFoundException;
import com.example.pokemondreamteam.exceptions.UnprocessableEntityException;
import com.example.pokemondreamteam.interfaces.Messages;
import com.example.pokemondreamteam.interfaces.json.Pokemons.PokemonResponse;
import com.example.pokemondreamteam.repository.PokemonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.List;

@Component
@Slf4j
public class PokemonApi {
  private final MessageError messageError;
  private final PokemonRepository pokemonRepository;
  private String uriPokeApi;

  public PokemonApi(
      MessageError messageError,
      PokemonRepository pokemonRepository,
      @Value("${pokeapi.urlPokemon}") String uriPokeApi) {
    this.messageError = messageError;
    this.pokemonRepository = pokemonRepository;
    this.uriPokeApi = uriPokeApi;
  }

  public void verifyPokemon(List<String> pokemon) {
    ExchangeStrategies exchangeStrategies =
        ExchangeStrategies.builder()
            .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2 * 1024 * 1024))
            .build();
    WebClient webClient = WebClient.builder().exchangeStrategies(exchangeStrategies).build();

    for (String s : pokemon) {

      if (s != null) {
        String pokemonName = s.toLowerCase();
        Boolean pokemonExistsOnDb = this.pokemonRepository.existsByPokemonName(pokemonName);
        if (!pokemonExistsOnDb) {
          String uri = uriPokeApi + pokemonName;
          PokemonResponse getPokemon =
              webClient
                  .get()
                  .uri(uri)
                  .retrieve()
                  .onStatus(HttpStatus::isError, this::errorPokemon)
                  .bodyToMono(PokemonResponse.class)
                  .block();

          logJson(getPokemon);
          assert getPokemon != null;
          PokemonDocument pokemonDocument = getPokemon.savePokemon();
          this.pokemonRepository.save(pokemonDocument);
        }
      }
    }
  }

  private void logJson(Object json) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      log.info(mapper.writeValueAsString(json));
    } catch (JsonProcessingException e) {
      log.error(e.getMessage());
    }
  }

  private Mono<? extends Throwable> errorPokemon(ClientResponse response) {
    Mono<WebClientResponseException> exception = response.createException();
    return exception.flatMap(
        ex ->
            Mono.error(
                new NotFoundException(
                    messageError.create(Messages.POKEMON_NOT_FOUND),
                    MessageFormat.format("Pokemon Not Found. Check your team.", response))));
  }
}
