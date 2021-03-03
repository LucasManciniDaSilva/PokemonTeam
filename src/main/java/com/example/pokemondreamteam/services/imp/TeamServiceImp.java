package com.example.pokemondreamteam.services.imp;

import com.example.pokemondreamteam.documents.TeamDocument;
import com.example.pokemondreamteam.exceptions.MessageError;
import com.example.pokemondreamteam.exceptions.NotFoundException;
import com.example.pokemondreamteam.integration.PokemonApi;
import com.example.pokemondreamteam.interfaces.Messages;
import com.example.pokemondreamteam.interfaces.json.Pokemons.Pokemon;
import com.example.pokemondreamteam.interfaces.json.Team.Team;
import com.example.pokemondreamteam.interfaces.json.Team.TeamPost;
import com.example.pokemondreamteam.interfaces.json.Team.TeamPut;
import com.example.pokemondreamteam.repository.PokemonRepository;
import com.example.pokemondreamteam.repository.TeamRepository;
import com.example.pokemondreamteam.services.TeamService;
import lombok.extern.slf4j.Slf4j;
import me.sargunvohra.lib.pokekotlin.client.PokeApiClient;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;

@Slf4j
@Service
public class TeamServiceImp implements TeamService {

  private final TeamRepository teamRepository;
  private final PokemonRepository pokemonRepository;
  private final MessageError messageError;
  private final PokemonApi pokemonApi;

  public TeamServiceImp(TeamRepository teamRepository, PokemonRepository pokemonRepository, MessageError messageError, PokemonApi pokemonApi) {
    this.teamRepository = teamRepository;
    this.pokemonRepository = pokemonRepository;
    this.messageError = messageError;
    this.pokemonApi = pokemonApi;
  }

  @Override
  @Transactional
  public Team teamPost(TeamPost teamPost) {

    pokemonApi.VerifyPokemon(teamPost.getFirstPokemon());
    pokemonApi.VerifyPokemon(teamPost.getSecondPokemon());
    pokemonApi.VerifyPokemon(teamPost.getThirdPokemon());
    pokemonApi.VerifyPokemon(teamPost.getFourthPokemon());
    pokemonApi.VerifyPokemon(teamPost.getFifthPokemon());
    pokemonApi.VerifyPokemon(teamPost.getLastPokemon());

    TeamDocument teamDocument = saveTeamSchema(teamPost);

    this.teamRepository.save(teamDocument);

    return teamDocument.toTeam();
  }

  @Override
  public Team getTeam(ObjectId _id) {
    return getTeamDocumentById(_id).toTeam();
  }

  @Override
  @Transactional
  public Team putTeam(ObjectId _id, TeamPut teamPut) {

    TeamDocument teamDocument = getTeamDocumentById(_id);

    teamPut.updatePokemonTeam(teamDocument);

    this.teamRepository.save(teamDocument);

    return teamDocument.toTeam();
  }

  @Override
  @Transactional
  public void deleteTeam(ObjectId _id) {

    TeamDocument teamDocument = getTeamDocumentById(_id);

    this.teamRepository.delete(teamDocument);

  }

  private TeamDocument getTeamDocumentById(ObjectId _id){
    return this.teamRepository.findBy_id(_id).orElseThrow(() -> new NotFoundException(this.messageError.create(
            Messages.POKEMON_TEAM_NOT_FOUND), MessageFormat.format(
            "Pokemon Team not found -> pokemonTeam={0}", _id)));
  }

  private TeamDocument saveTeamSchema(TeamPost teamPost){
    Pokemon firstPokemon = this.pokemonRepository.findByPokemonName(teamPost.getFirstPokemon().toLowerCase()).toPokemon();
    Pokemon secondPokemon = this.pokemonRepository.findByPokemonName(teamPost.getSecondPokemon().toLowerCase()).toPokemon();
    Pokemon thirdPokemon = this.pokemonRepository.findByPokemonName(teamPost.getThirdPokemon().toLowerCase()).toPokemon();
    Pokemon fourthPokemon = this.pokemonRepository.findByPokemonName(teamPost.getFourthPokemon().toLowerCase()).toPokemon();
    Pokemon fifthPokemon = this.pokemonRepository.findByPokemonName(teamPost.getFifthPokemon().toLowerCase()).toPokemon();
    Pokemon lastPokemon = this.pokemonRepository.findByPokemonName(teamPost.getLastPokemon().toLowerCase()).toPokemon();


      return TeamDocument.builder()
              .firstPokemon(firstPokemon)
              .secondPokemon(secondPokemon)
              .thirdPokemon(thirdPokemon)
              .fourthPokemon(fourthPokemon)
              .fifthPokemon(fifthPokemon)
              .lastPokemon(lastPokemon)
              .build();
    }
}
