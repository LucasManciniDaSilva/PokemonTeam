package com.example.pokemondreamteam.services.imp;

import com.example.pokemondreamteam.interfaces.json.Team;
import com.example.pokemondreamteam.interfaces.json.TeamPost;
import com.example.pokemondreamteam.interfaces.response.TeamResponse;
import com.example.pokemondreamteam.repository.TeamRepository;
import com.example.pokemondreamteam.documents.TeamDocument;
import com.example.pokemondreamteam.services.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class TeamServiceImp implements TeamService {

  private final TeamRepository teamRepository;

  public TeamServiceImp(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  @Override
  @Transactional
  public TeamResponse teamPost(TeamPost teamPost) {

    TeamDocument teamDocument = teamPost.toTeamSchema();

    TeamResponse teamResponse = new TeamResponse();

    teamResponse.setFirstPokemon(teamDocument.getFirstPokemon());
    teamResponse.setSecondPokemon(teamDocument.getSecondPokemon());
    teamResponse.setThirdPokemon(teamDocument.getThirdPokemon());
    teamResponse.setFourthPokemon(teamDocument.getFourthPokemon());
    teamResponse.setFifthPokemon(teamDocument.getFifthPokemon());
    teamResponse.setLastPokemon(teamDocument.getLastPokemon());

    this.teamRepository.save(teamDocument);

    return teamResponse;
  }

  @Override
  public Team getTeamById(ObjectId _id){
    return getTeam(_id).toTeam();
  }

  @Override
  public TeamDocument getTeam(ObjectId _id) {
   return this.teamRepository.findBy_id(_id);

  }


}
