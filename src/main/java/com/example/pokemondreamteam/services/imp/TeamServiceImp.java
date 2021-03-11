package com.example.pokemondreamteam.services.imp;

import com.example.pokemondreamteam.documents.TeamDocument;
import com.example.pokemondreamteam.exceptions.MessageError;
import com.example.pokemondreamteam.exceptions.NotFoundException;
import com.example.pokemondreamteam.integration.PokemonApi;
import com.example.pokemondreamteam.interfaces.Messages;
import com.example.pokemondreamteam.interfaces.json.Team.Team;
import com.example.pokemondreamteam.interfaces.json.Team.TeamPost;
import com.example.pokemondreamteam.interfaces.json.Team.TeamPatch;
import com.example.pokemondreamteam.repository.PokemonRepository;
import com.example.pokemondreamteam.repository.TeamRepository;
import com.example.pokemondreamteam.services.PokemonService;
import com.example.pokemondreamteam.services.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.*;

@Slf4j
@Service
public class TeamServiceImp implements TeamService {

  private final TeamRepository teamRepository;
  private final PokemonService pokemonService;
  private final PokemonRepository pokemonRepository;
  private final MessageError messageError;
  private final PokemonApi pokemonApi;

  public TeamServiceImp(
          TeamRepository teamRepository,
          PokemonService pokemonService, PokemonRepository pokemonRepository,
          MessageError messageError,
          PokemonApi pokemonApi) {
    this.teamRepository = teamRepository;
    this.pokemonService = pokemonService;
    this.pokemonRepository = pokemonRepository;
    this.messageError = messageError;
    this.pokemonApi = pokemonApi;
  }

  @Override
  @Transactional
  public Team teamPost(TeamPost teamPost) {

    List<String> teamPostList = teamPost.teamPostList(teamPost);

    pokemonApi.verifyPokemon(teamPostList);

    TeamDocument teamDocument = pokemonService.saveTeamSchema(teamPost);

    this.teamRepository.save(teamDocument);

    return teamDocument.toTeam();
  }

  @Override
  public Team getTeam(ObjectId _id) {
    return getTeamDocumentById(_id).toTeam();
  }

  @Override
  @Transactional
  public Team patchTeam(ObjectId _id, TeamPatch teamPatch) {

    List<String> teamPatchList = teamPatch.teamPatchList(teamPatch);

    pokemonApi.verifyPokemon(teamPatchList);

    TeamDocument teamDocument = this.getTeamDocumentById(_id);

    pokemonService.updateTeam(teamDocument, teamPatchList);

    this.teamRepository.save(teamDocument);

    return teamDocument.toTeam();
  }

  @Override
  @Transactional
  public void deleteTeam(ObjectId _id) {

    TeamDocument teamDocument = getTeamDocumentById(_id);

    this.teamRepository.delete(teamDocument);
  }

  private TeamDocument getTeamDocumentById(ObjectId _id) {
    return this.teamRepository
        .findBy_id(_id)
        .orElseThrow(
            () ->
                new NotFoundException(
                    this.messageError.create(Messages.POKEMON_TEAM_NOT_FOUND),
                    MessageFormat.format("Pokemon Team Not Found -> pokemonTeam={0}",  _id)));
  }
}
