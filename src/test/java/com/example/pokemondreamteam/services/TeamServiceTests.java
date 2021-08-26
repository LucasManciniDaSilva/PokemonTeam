package com.example.pokemondreamteam.services;

import com.example.pokemondreamteam.documents.PokemonDocument;
import com.example.pokemondreamteam.documents.TeamDocument;
import com.example.pokemondreamteam.exceptions.MessageError;
import com.example.pokemondreamteam.exceptions.NotFoundException;
import com.example.pokemondreamteam.integration.PokemonApi;
import com.example.pokemondreamteam.interfaces.json.Pokemons.Pokemon;
import com.example.pokemondreamteam.interfaces.json.Pokemons.PokemonResponse;
import com.example.pokemondreamteam.interfaces.json.Team.Team;
import com.example.pokemondreamteam.interfaces.json.Team.TeamPatch;
import com.example.pokemondreamteam.interfaces.json.Team.TeamPost;
import com.example.pokemondreamteam.repository.PokemonRepository;
import com.example.pokemondreamteam.repository.TeamRepository;
import com.example.pokemondreamteam.services.imp.TeamServiceImp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTests {
  private static String TEAM_NAME;
  private static String POKEMON_NULL;
  private static String FIRST_POKEMON;
  private static String SECOND_POKEMON;
  private static String THIRD_POKEMON;
  private static String FOURTH_POKEMON;
  private static String FIFTH_POKEMON;
  private static String LAST_POKEMON;

  private TeamService teamService;

  @Mock private TeamRepository teamRepository;
  @Mock private List<String> teamListPost;
  @Mock private List<String> teamListPatch;
  @Mock private MessageError messageError;
  @Mock private TeamPost teamPost;
  @Mock private TeamPost teamPostError;
  @Mock private TeamDocument teamDocument;
  @Mock private PokemonDocument pokemonDocument;
  @Mock private PageRequest pageRequest;
  @Mock private Pokemon pokemon;
  @Mock private MessageError.ApiError apiError;
  @Mock private TeamPatch teamPatch;
  @Mock private Team team;
  @Mock private PokemonResponse pokemonResponse;
  @Mock private PokemonService pokemonService;
  @Mock private PokemonRepository pokemonRepository;
  @Mock private PokemonApi pokemonApi;
  private Object ResponseEntityExceptionHandler;

  @BeforeAll
  public static void beforeAll() {
    TEAM_NAME = "TESTE";
    FIRST_POKEMON = "Squirtle";
    SECOND_POKEMON = "Charmander";
    THIRD_POKEMON = "Bulbasaur";
    FOURTH_POKEMON = "Pikachu";
    FIFTH_POKEMON = "Abra";
    LAST_POKEMON = "Gastly";
    POKEMON_NULL = "";
  }

  @BeforeEach
  public void beforeEach() {
    teamService =
        spy(
            new TeamServiceImp(
                teamRepository, pokemonService, pokemonRepository, messageError, pokemonApi));
    teamPost =
        spy(
            new TeamPost(
                TEAM_NAME,    
                FIRST_POKEMON,
                SECOND_POKEMON,
                THIRD_POKEMON,
                FOURTH_POKEMON,
                FIFTH_POKEMON,
                LAST_POKEMON));
    teamPostError =
        spy(
            new TeamPost(
                    TEAM_NAME,
                    FIRST_POKEMON,
                    SECOND_POKEMON,
                    THIRD_POKEMON,
                    FOURTH_POKEMON,
                    FIFTH_POKEMON,
                    LAST_POKEMON));
    teamPatch =
        spy(
            new TeamPatch(
                    FIRST_POKEMON,
                    SECOND_POKEMON,
                    THIRD_POKEMON,
                    FOURTH_POKEMON,
                    FIFTH_POKEMON,
                    LAST_POKEMON));
    teamListPatch = teamPatch.teamPatchList(teamPatch);
  }

  @DisplayName("Should add new team successfully")
  @Test
  public void postTeamSuccessfully() {
    when(pokemonService.saveTeamSchema(teamPost)).thenReturn(teamDocument);
    when(teamRepository.save(teamDocument)).thenReturn(teamDocument);
    when(teamDocument.toTeam()).thenReturn(team);
    assertEquals(team, teamService.teamPost(teamPost));
  }

  @DisplayName("Should get team successfully")
  @Test
  public void getTeamSuccessfully() {
    when(teamRepository.findByTeamName(team.getTeamName())).thenReturn(Optional.of(teamDocument));
    when(teamDocument.toTeam()).thenReturn(team);
    assertEquals(team, teamService.getTeam(team.getTeamName()));
  }

  @DisplayName("Should update team successfully")
  @Test
  public void updateTeamSuccessfully() {
    when(teamRepository.findByTeamName(team.getTeamName())).thenReturn(Optional.of(teamDocument));
    pokemonService.updateTeam(teamDocument, teamListPatch);
    when(teamRepository.save(teamDocument)).thenReturn(teamDocument);
    when(teamDocument.toTeam()).thenReturn(team);
    assertEquals(team, teamService.patchTeam(team.getTeamName(), teamPatch));
  }

  @DisplayName("Should delete team successfully")
  @Test
  public void deleteTeamSuccessfully() {
    when(teamRepository.findByTeamName(team.getTeamName())).thenReturn(Optional.of(teamDocument));
    teamRepository.delete(teamDocument);
    teamService.deleteTeam(team.getTeamName());
  }

  @DisplayName("Should throw an exception when try to get a team with a teamId not found")
  @Test
  public void getTeamWithTeamIdNotFound() {
    String teamId = TEAM_NAME;

    when(messageError.create(any(), any())).thenReturn(apiError);

    when(teamRepository.findByTeamName(teamId)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> teamService.getTeam(teamId))
        .isInstanceOf(NotFoundException.class)
        .hasMessageContaining("apiError - Detail: Pokemon Team Not Found");
  }

  @DisplayName("Should throw an exception when try to delete a team with a teamId not found")
  @Test
  public void deleteTeamWithTeamIdNotFound() {
    String teamId = TEAM_NAME;

    when(messageError.create(any(), any())).thenReturn(apiError);

    when(teamRepository.findByTeamName(teamId)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> teamService.deleteTeam(teamId))
        .isInstanceOf(NotFoundException.class)
        .hasMessageContaining("apiError - Detail: Pokemon Team Not Found");
  }

  @DisplayName("Should throw an exception when try to update a team with a teamId not found")
  @Test
  public void updateTeamWithTeamIdNotFound() {
    String teamId = TEAM_NAME;

    when(messageError.create(any(), any())).thenReturn(apiError);

    when(teamRepository.findByTeamName(teamId)).thenReturn(Optional.empty());

    assertThatThrownBy(() -> teamService.patchTeam(teamId, teamPatch))
        .isInstanceOf(NotFoundException.class)
        .hasMessageContaining("apiError - Detail: Pokemon Team Not Found");
  }
}
