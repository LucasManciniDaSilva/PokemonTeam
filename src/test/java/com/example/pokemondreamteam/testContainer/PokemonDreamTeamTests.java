package com.example.pokemondreamteam.testContainer;

import com.example.pokemondreamteam.interfaces.json.Team.Team;
import com.example.pokemondreamteam.interfaces.json.Team.TeamPatch;
import com.example.pokemondreamteam.interfaces.json.Team.TeamPost;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;
import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {MongoContainer.Initializer.class})
public class PokemonDreamTeamTests {

  private static final Logger logger = Logger.getLogger(PokemonDreamTeamTests.class.getName());

  @Autowired public TestRestTemplate restTemplate;

  @LocalServerPort public int port;

  public String getRootUrl() {
    return "http://localhost:" + port + "/pokemon-team/v1";
  }

  private TeamPost createNewTeam() {

    Faker faker = new Faker();
    String teamName = faker.name().username();
    TeamPost teamPost = new TeamPost();
    teamPost.setTeamName(teamName);
    teamPost.setFirstPokemon("Squirtle");
    teamPost.setSecondPokemon("Bulbasaur");
    teamPost.setThirdPokemon("Eevee");
    teamPost.setFourthPokemon("Zorua");
    teamPost.setFifthPokemon("Jolteon");
    teamPost.setLastPokemon("Mew");

    return teamPost;
  }

  private TeamPatch updateTeam() {

    TeamPatch teamPatch = new TeamPatch();
    teamPatch.setFirstPokemon("Gible");
    teamPatch.setSecondPokemon("Charmander");
    teamPatch.setThirdPokemon("Mudkip");
    teamPatch.setFourthPokemon("Bagon");
    teamPatch.setFifthPokemon("Quilava");
    teamPatch.setLastPokemon("Dratini");

    return teamPatch;
  }



  @Test
  public void postTeamWithSuccess() {
    TeamPost teamPost = createNewTeam();

    ResponseEntity postResponse =
        restTemplate.postForEntity(getRootUrl() + "/team", teamPost, null);

    logger.info("Pokemon Team Post Request"+ teamPost.toString());

    String teamName = (String.valueOf(postResponse.getHeaders().getFirst("teamName")));

    logger.info("Pokemon Team Name:" + teamName);

    Assert.assertEquals(teamName, teamPost.getTeamName());

    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> entity = new HttpEntity<>(null, headers);

    ResponseEntity deleteTeam =
            restTemplate.exchange(
                    getRootUrl()
                            + "/team/"
                            + Objects.requireNonNull(postResponse.getHeaders()).getFirst("teamName"),
                    HttpMethod.DELETE,
                    entity,
                    (Class<Team>) null);

    logger.info("Delete Pokemon Team status code returned: " + deleteTeam.getStatusCode());

    Assert.assertEquals(deleteTeam.getStatusCode(), HttpStatus.NO_CONTENT);
  }

  @Test
  public void postTeamWithTeamAlreadyExists() {
    TeamPost teamPost = createNewTeam();
    teamPost.setTeamName("lucio.rodriguez");

    ResponseEntity postResponse =
        restTemplate.postForEntity(getRootUrl() + "/team", teamPost, null);

    logger.info("Pokemon Team Post Request"+ teamPost.toString());

    String responseBody = postResponse.getStatusCode().toString();

    logger.info("Response Code:" + responseBody);

    Assert.assertEquals(postResponse.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
  }

  @Test
  public void postTeamWithPokemonThatNotExists() {
    TeamPost teamPost = createNewTeam();
    teamPost.setFirstPokemon("Mewthree");

    ResponseEntity postResponse =
        restTemplate.postForEntity(getRootUrl() + "/team", teamPost, null);

    logger.info("Pokemon Team Post Request"+ teamPost.toString());

    String responseBody = postResponse.getStatusCode().toString();

    logger.info("Response Code:" + responseBody);

    Assert.assertEquals(postResponse.getStatusCode(), HttpStatus.NOT_FOUND);
  }

  @Test
  public void postTeamWithoutFieldFirstPokemon() {
    TeamPost teamPost = createNewTeam();
    teamPost.setFirstPokemon(null);

    ResponseEntity postResponse =
        restTemplate.postForEntity(getRootUrl() + "/team", teamPost, null);

    logger.info("Pokemon Team Post Request"+ teamPost.toString());

    String responseBody = postResponse.getStatusCode().toString();

    logger.info("Response Code:" + responseBody);

    Assert.assertEquals(postResponse.getStatusCode(), HttpStatus.BAD_REQUEST);
  }

  @Test
  public void postTeamWithoutFieldSecondPokemon() {
    TeamPost teamPost = createNewTeam();
    teamPost.setSecondPokemon(null);

    ResponseEntity postResponse =
        restTemplate.postForEntity(getRootUrl() + "/team", teamPost, null);

    logger.info("Pokemon Team Post Request"+ teamPost.toString());

    String responseBody = postResponse.getStatusCode().toString();

    logger.info("Response Code:" + responseBody);

    Assert.assertEquals(postResponse.getStatusCode(), HttpStatus.BAD_REQUEST);
  }

  @Test
  public void postTeamWithoutFieldThirdPokemon() {
    TeamPost teamPost = createNewTeam();
    teamPost.setThirdPokemon(null);

    ResponseEntity postResponse =
        restTemplate.postForEntity(getRootUrl() + "/team", teamPost, null);

    logger.info("Pokemon Team Post Request"+ teamPost.toString());

    String responseBody = postResponse.getStatusCode().toString();

    logger.info("Response Code:" + responseBody);

    Assert.assertEquals(postResponse.getStatusCode(), HttpStatus.BAD_REQUEST);
  }

  @Test
  public void postTeamWithoutFieldFourthPokemon() {
    TeamPost teamPost = createNewTeam();
    teamPost.setFourthPokemon(null);

    ResponseEntity postResponse =
        restTemplate.postForEntity(getRootUrl() + "/team", teamPost, null);

    logger.info("Pokemon Team Post Request"+ teamPost.toString());

    String responseBody = postResponse.getStatusCode().toString();

    logger.info("Response Code:" + responseBody);

    Assert.assertEquals(postResponse.getStatusCode(), HttpStatus.BAD_REQUEST);
  }

  @Test
  public void postTeamWithoutFieldFifthPokemon() {
    TeamPost teamPost = createNewTeam();
    teamPost.setFifthPokemon(null);

    ResponseEntity postResponse =
        restTemplate.postForEntity(getRootUrl() + "/team", teamPost, null);

    logger.info("Pokemon Team Post Request"+ teamPost.toString());

    String responseBody = postResponse.getStatusCode().toString();

    logger.info("Response Code:" + responseBody);

    Assert.assertEquals(postResponse.getStatusCode(), HttpStatus.BAD_REQUEST);
  }

  @Test
  public void postTeamWithoutFieldLastPokemon() {
    TeamPost teamPost = createNewTeam();
    teamPost.setLastPokemon(null);

    ResponseEntity postResponse =
        restTemplate.postForEntity(getRootUrl() + "/team", teamPost, null);

    logger.info("Pokemon Team Post Request"+ teamPost.toString());

    String responseBody = postResponse.getStatusCode().toString();

    logger.info("Response Code:" + responseBody);

    Assert.assertEquals(postResponse.getStatusCode(), HttpStatus.BAD_REQUEST);
  }

  @Test
  public void getPokemonTeamWithSuccess() {
    TeamPost teamPost = createNewTeam();

    ResponseEntity postResponse =
            restTemplate.postForEntity(getRootUrl() + "/team", teamPost, null);

    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> entity = new HttpEntity<>(null, headers);
    ResponseEntity<Team> response =
            restTemplate.exchange(
                    getRootUrl()
                            + "/team/"
                            + Objects.requireNonNull(postResponse.getHeaders()).getFirst("teamName"),
                    HttpMethod.GET,
                    entity,
                    Team.class);

    logger.info("Get Pokemon Team Response:" + Objects.requireNonNull(response.getBody()).toString());

    Assert.assertEquals(
            Objects.requireNonNull(response.getBody()).getTeamName(), teamPost.getTeamName());

    Assert.assertEquals(response.getBody().getFirstPokemon().getName(), teamPost.getFirstPokemon().toLowerCase());

    Assert.assertEquals(
            response.getBody().getSecondPokemon().getName(), teamPost.getSecondPokemon().toLowerCase());

    Assert.assertEquals(response.getBody().getThirdPokemon().getName(), teamPost.getThirdPokemon().toLowerCase());

    Assert.assertEquals(
            response.getBody().getFourthPokemon().getName(), teamPost.getFourthPokemon().toLowerCase());

    Assert.assertEquals(response.getBody().getFifthPokemon().getName(), teamPost.getFifthPokemon().toLowerCase());

    Assert.assertEquals(response.getBody().getLastPokemon().getName(), teamPost.getLastPokemon().toLowerCase());

    Assert.assertTrue(response.getStatusCode().is2xxSuccessful());

    ResponseEntity deleteTeam =
            restTemplate.exchange(
                    getRootUrl()
                            + "/team/"
                            + Objects.requireNonNull(postResponse.getHeaders()).getFirst("teamName"),
                    HttpMethod.DELETE,
                    entity,
                    (Class<?>) null);

    logger.info("Delete Pokemon Team status code returned: " + deleteTeam.getStatusCode());

    Assert.assertEquals(deleteTeam.getStatusCode(), HttpStatus.NO_CONTENT);
  }

  @Test
  public void getPokemonTeamWithTeamNameThatNotExists() {
    Faker faker = new Faker();
    String teamName = faker.name().username();

    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> entity = new HttpEntity<>(null, headers);
    ResponseEntity<Team> response =
            restTemplate.exchange(
                    getRootUrl()
                            + "/team/"
                            + teamName,
                    HttpMethod.GET,
                    entity,
                    (Class<Team>) null);

    Assert.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
  }

  @Test
  public void getPokemonTeamWithouTeamNameInThePath() {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> entity = new HttpEntity<>(null, headers);
    ResponseEntity<Team> response =
            restTemplate.exchange(
                    getRootUrl()
                            + "/team/",
                    HttpMethod.GET,
                    entity,
                    (Class<Team>) null);

    Assert.assertEquals(response.getStatusCode(), HttpStatus.METHOD_NOT_ALLOWED);
  }

  @Test
  public void updateAnExistingPokemonTeam() {
    TeamPost teamPost = createNewTeam();

    ResponseEntity postResponse =
            restTemplate.postForEntity(getRootUrl() + "/team", teamPost, null);

      logger.info("Team Created");

    HttpHeaders headers = new HttpHeaders();

    TeamPatch teamPatch = updateTeam();

    HttpEntity<TeamPatch> patchEntity = new HttpEntity<>(teamPatch, null);

    ResponseEntity teamPatchRequest =
            restTemplate.exchange(getRootUrl()
                            + "/team/"
                            + Objects.requireNonNull(postResponse.getHeaders()).getFirst("teamName"),
                    HttpMethod.PATCH,
                    patchEntity,  (Class<Team>) null );

    logger.info("Team Updated");

    HttpEntity<String> entity = new HttpEntity<>(null, headers);

    ResponseEntity<Team> response =
            restTemplate.exchange(
                    getRootUrl()
                            + "/team/"
                            + Objects.requireNonNull(postResponse.getHeaders()).getFirst("teamName"),
                    HttpMethod.GET,
                    entity,
                    Team.class);

    logger.info("Get Pokemon Team Response:" + Objects.requireNonNull(response.getBody()).toString());

    Assert.assertEquals(response.getBody().getFirstPokemon().getName(), teamPatch.getFirstPokemon().toLowerCase());

    Assert.assertEquals(
            response.getBody().getSecondPokemon().getName(), teamPatch.getSecondPokemon().toLowerCase());

    Assert.assertEquals(response.getBody().getThirdPokemon().getName(), teamPatch.getThirdPokemon().toLowerCase());

    Assert.assertEquals(
            response.getBody().getFourthPokemon().getName(), teamPatch.getFourthPokemon().toLowerCase());

    Assert.assertEquals(response.getBody().getFifthPokemon().getName(), teamPatch.getFifthPokemon().toLowerCase());

    Assert.assertEquals(response.getBody().getLastPokemon().getName(), teamPatch.getLastPokemon().toLowerCase());

    Assert.assertTrue(response.getStatusCode().is2xxSuccessful());

    ResponseEntity deleteTeam =
            restTemplate.exchange(
                    getRootUrl()
                            + "/team/"
                            + Objects.requireNonNull(postResponse.getHeaders()).getFirst("teamName"),
                    HttpMethod.DELETE,
                    entity,
                    (Class<?>) null);

    logger.info("Delete Pokemon Team status code returned: " + deleteTeam.getStatusCode());

    Assert.assertEquals(deleteTeam.getStatusCode(), HttpStatus.NO_CONTENT);
  }

  @Test
  public void updatePokemonTeamWithTeamNameThatNotExists() {
    Faker faker = new Faker();

    String teamName = faker.name().username();

    TeamPatch teamPatch = updateTeam();

    HttpEntity<TeamPatch> patchEntity = new HttpEntity<>(teamPatch, null);

    ResponseEntity teamPatchRequest =
            restTemplate.exchange(getRootUrl()
                            + "/team/"
                            + teamName,
                    HttpMethod.PATCH,
                    patchEntity,  (Class<?>) null );

    Assert.assertEquals(teamPatchRequest.getStatusCode(), HttpStatus.NOT_FOUND);
  }

  @Test
  public void updatePokemonTeamWithouTeamNameInThePath() {
    TeamPatch teamPatch = updateTeam();

    HttpEntity<TeamPatch> patchEntity = new HttpEntity<>(teamPatch, null);

    ResponseEntity teamPatchRequest =
            restTemplate.exchange(getRootUrl()
                            + "/team/",
                    HttpMethod.PATCH,
                    patchEntity,  (Class<?>) null );

    Assert.assertEquals(teamPatchRequest.getStatusCode(), HttpStatus.METHOD_NOT_ALLOWED);
  }

  @Test
  public void deleteAnExistingPokemonTeam() {
    TeamPost teamPost = createNewTeam();

    ResponseEntity postResponse =
            restTemplate.postForEntity(getRootUrl() + "/team", teamPost, null);

    HttpHeaders headers = new HttpHeaders();

    HttpEntity<String> entity = new HttpEntity<>(null, headers);

    logger.info("Team Created");

    ResponseEntity deleteTeam =
            restTemplate.exchange(
                    getRootUrl()
                            + "/team/"
                            + Objects.requireNonNull(postResponse.getHeaders()).getFirst("teamName"),
                    HttpMethod.DELETE,
                    entity,
                    (Class<?>) null);

    logger.info("Delete Pokemon Team status code returned: " + deleteTeam.getStatusCode());

    Assert.assertEquals(deleteTeam.getStatusCode(), HttpStatus.NO_CONTENT);
  }

  @Test
  public void deletePokemonTeamWithTeamNameThatNotExists() {
    Faker faker = new Faker();

    String teamName = faker.name().username();

    HttpHeaders headers = new HttpHeaders();

    HttpEntity<String> entity = new HttpEntity<>(null, headers);

    ResponseEntity deleteTeam =
            restTemplate.exchange(
                    getRootUrl()
                            + "/team/"
                            + teamName,
                    HttpMethod.DELETE,
                    entity,
                    (Class<?>) null);

    Assert.assertEquals(deleteTeam.getStatusCode(), HttpStatus.NOT_FOUND);
  }

  @Test
  public void deletePokemonTeamWithoutTeamNameInThePath() {
    HttpHeaders headers = new HttpHeaders();

    HttpEntity<String> entity = new HttpEntity<>(null, headers);

    ResponseEntity deleteTeam =
            restTemplate.exchange(
                    getRootUrl()
                            + "/team/",
                    HttpMethod.DELETE,
                    entity,
                    (Class<?>) null);

    Assert.assertEquals(deleteTeam.getStatusCode(), HttpStatus.METHOD_NOT_ALLOWED);
  }
}


