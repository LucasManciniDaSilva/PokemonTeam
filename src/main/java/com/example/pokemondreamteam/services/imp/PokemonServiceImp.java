package com.example.pokemondreamteam.services.imp;

import com.example.pokemondreamteam.documents.TeamDocument;
import com.example.pokemondreamteam.interfaces.json.Pokemons.Pokemon;
import com.example.pokemondreamteam.interfaces.json.Team.TeamPost;
import com.example.pokemondreamteam.repository.PokemonRepository;
import com.example.pokemondreamteam.services.PokemonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PokemonServiceImp implements PokemonService {

  private final PokemonRepository pokemonRepository;

  public PokemonServiceImp(PokemonRepository pokemonRepository) {
    this.pokemonRepository = pokemonRepository;
  }

  @Override
  public TeamDocument saveTeamSchema(TeamPost teamPost) {

    Pokemon firstPokemon =
        this.pokemonRepository
            .findByPokemonName(teamPost.getFirstPokemon().toLowerCase())
            .toPokemon();
    Pokemon secondPokemon =
        this.pokemonRepository
            .findByPokemonName(teamPost.getSecondPokemon().toLowerCase())
            .toPokemon();
    Pokemon thirdPokemon =
        this.pokemonRepository
            .findByPokemonName(teamPost.getThirdPokemon().toLowerCase())
            .toPokemon();
    Pokemon fourthPokemon =
        this.pokemonRepository
            .findByPokemonName(teamPost.getFourthPokemon().toLowerCase())
            .toPokemon();
    Pokemon fifthPokemon =
        this.pokemonRepository
            .findByPokemonName(teamPost.getFifthPokemon().toLowerCase())
            .toPokemon();
    Pokemon lastPokemon =
        this.pokemonRepository
            .findByPokemonName(teamPost.getLastPokemon().toLowerCase())
            .toPokemon();

    return TeamDocument.builder()
        .firstPokemon(firstPokemon)
        .secondPokemon(secondPokemon)
        .thirdPokemon(thirdPokemon)
        .fourthPokemon(fourthPokemon)
        .fifthPokemon(fifthPokemon)
        .lastPokemon(lastPokemon)
        .build();
  }

  @Override
  public void updateTeam(TeamDocument teamDocument, List<String> teamPatchList) {

    if (teamPatchList.get(0) != null) {
      Pokemon firstPokemon =
          this.pokemonRepository.findByPokemonName(teamPatchList.get(0).toLowerCase()).toPokemon();
      teamDocument.setFirstPokemon(firstPokemon);
    }
    if (teamPatchList.get(1) != null) {
      Pokemon secondPokemon =
          this.pokemonRepository.findByPokemonName(teamPatchList.get(1).toLowerCase()).toPokemon();
      teamDocument.setSecondPokemon(secondPokemon);
    }

    if (teamPatchList.get(2) != null) {
      Pokemon thirdPokemon =
          this.pokemonRepository.findByPokemonName(teamPatchList.get(2).toLowerCase()).toPokemon();
      teamDocument.setThirdPokemon(thirdPokemon);
    }
    if (teamPatchList.get(3) != null) {
      Pokemon fourthPokemon =
          this.pokemonRepository.findByPokemonName(teamPatchList.get(3).toLowerCase()).toPokemon();
      teamDocument.setFourthPokemon(fourthPokemon);
    }

    if (teamPatchList.get(4) != null) {
      Pokemon fifthPokemon =
          this.pokemonRepository.findByPokemonName(teamPatchList.get(4).toLowerCase()).toPokemon();
      teamDocument.setFifthPokemon(fifthPokemon);
    }

    if (teamPatchList.get(5) != null) {
      Pokemon lastPokemon =
          this.pokemonRepository.findByPokemonName(teamPatchList.get(5).toLowerCase()).toPokemon();
      teamDocument.setLastPokemon(lastPokemon);
    }
  }
}
