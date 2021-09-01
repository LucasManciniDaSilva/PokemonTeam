package com.example.pokemondreamteam.testContainer;

import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.MongoDBContainer;

@RunWith(Suite.class)
@Suite.SuiteClasses({PokemonDreamTeamTests.class})
public class MongoContainer {

  @ClassRule
  public static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.0.10");

  static class Initializer
      implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        mongoDBContainer.start();
    }
  }
}
