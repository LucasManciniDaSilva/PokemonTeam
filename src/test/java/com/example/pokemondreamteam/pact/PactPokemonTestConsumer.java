package com.example.pokemondreamteam.pact;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class PactPokemonTestConsumer {

  @Rule
  public PactProviderRuleMk2 provider =
      new PactProviderRuleMk2("test_provider", "localhost", 8081, this);

  @Pact(consumer = "test_consumer")
  public RequestResponsePact createPact(PactDslWithProvider builder) {
    Map<String, String> headersRequest = new HashMap<>();
    headersRequest.put("content-type", "application/json");

    return builder
        .given("Post a new team")
        .uponReceiving("Post Request")
        .path("/team")
        .method("POST")
        .headers(headersRequest)
        .body(
            "{"
                + "  \"firstPokemon\": \"Eevee\",\n"
                + "  \"secondPokemon\": \"Pikachu\",\n"
                + "  \"thirdPokemon\": \"Dratini\",\n"
                + "  \"fourthPokemon\": \"Quilava\",\n"
                + "  \"fifthPokemon\": \"Articuno\",\n"
                + "  \"lastPokemon\": \"Raichu\",\n"
                + "}")
        .willRespondWith()
        .status(201)
        .toPact();
  }

  @Test
  @PactVerification()
  public void givenPost_whenSendRequest_shouldReturn201Ok() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    String jsonBody =
        "{"
            + "  \"firstPokemon\": \"Eevee\",\n"
            + "  \"secondPokemon\": \"Pikachu\",\n"
            + "  \"thirdPokemon\": \"Dratini\",\n"
            + "  \"fourthPokemon\": \"Quilava\",\n"
            + "  \"fifthPokemon\": \"Articuno\",\n"
            + "  \"lastPokemon\": \"Raichu\",\n"
            + "}";
    // when
    ResponseEntity<String> postResponse =
        new RestTemplate()
            .exchange(
                provider.getUrl() + "/team",
                HttpMethod.POST,
                new HttpEntity<>(jsonBody, httpHeaders),
                String.class);

    // then
    assertThat(postResponse.getStatusCode().value()).isEqualTo(201);
    assertThat(postResponse.getHeaders().get("_id"));
  }
}
