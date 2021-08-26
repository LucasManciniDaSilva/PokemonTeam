package com.example.pokemondreamteam.services;

import com.example.pokemondreamteam.exceptions.MessageError;
import com.example.pokemondreamteam.exceptions.UnprocessableEntityException;
import com.example.pokemondreamteam.integration.PokemonApi;
import com.example.pokemondreamteam.interfaces.json.Team.TeamPost;
import com.example.pokemondreamteam.repository.PokemonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PokemonApiTests {
  private static String TEAM_NAME;
  private static String FIRST_POKEMON;
  private static String SECOND_POKEMON;
  private static String THIRD_POKEMON;
  private static String FOURTH_POKEMON;
  private static String FIFTH_POKEMON;
  private static String LAST_POKEMON;
  @Mock private List<String> teamListPost;
  @Mock private List<String> pokemonList;
  @Mock private MessageError messageError;
  @Mock private MessageError.ApiError apiError;
  @Mock private TeamPost teamPost;
  @Mock private PokemonRepository pokemonRepository;
  @Mock private PokemonApi pokemonApi;

  @BeforeAll
  public static void beforeAll() {
    TEAM_NAME = "TESTE";
    FIRST_POKEMON = "Squirtle";
    SECOND_POKEMON = "Charmander";
    THIRD_POKEMON = "Bulbasaur";
    FOURTH_POKEMON = "Pikachu";
    FIFTH_POKEMON = "Abra";
    LAST_POKEMON = "Gastly";
  }
  @BeforeEach
  public void beforeEach() {
    String pokemonUri = "https://pokeapi.co/api/v2/pokemon/";
    pokemonApi = new PokemonApi(messageError, pokemonRepository, pokemonUri);
    pokemonList = new ArrayList<>();
      teamPost = spy(
            new TeamPost(
                    TEAM_NAME,
                    FIRST_POKEMON,
                    SECOND_POKEMON,
                    THIRD_POKEMON,
                    FOURTH_POKEMON,
                    FIFTH_POKEMON,
                    LAST_POKEMON));
  }


  @DisplayName("Should Search List of Pokemon")
  @Test
  public void searchPokemonSuccessfully() {
    teamListPost = teamPost.teamPostList(teamPost);
    pokemonApi.verifyPokemon(teamListPost);
  }

  @DisplayName("Should search a Pokemon")
  @Test
  public void searchAPokemonSuccessfully() {
    pokemonList.add(FIRST_POKEMON);
    pokemonApi.verifyPokemon(pokemonList);
  }

  @DisplayName("Should search a wrong Pokemon")
  @Test
  public void searchAWrongPokemon() {
    pokemonList.add("mewthree");
    when(messageError.create(any(), any())).thenReturn(apiError);
    assertThatThrownBy(() -> pokemonApi.verifyPokemon(pokemonList))
            .isInstanceOf(UnprocessableEntityException.class)
            .hasMessageContaining("apiError - Detail: Pokemon Not Found. Check your team.");
  }
}
