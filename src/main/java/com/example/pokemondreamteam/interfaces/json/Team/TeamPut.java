package com.example.pokemondreamteam.interfaces.json.Team;

import com.example.pokemondreamteam.documents.PokemonDocument;
import com.example.pokemondreamteam.documents.TeamDocument;
import com.example.pokemondreamteam.interfaces.json.Pokemons.Pokemon;
import com.example.pokemondreamteam.interfaces.json.Pokemons.PokemonResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamPut {

  @NotNull private Pokemon firstPokemon;
  @NotNull private Pokemon secondPokemon;
  @NotNull private Pokemon thirdPokemon;
  @NotNull private Pokemon fourthPokemon;
  @NotNull private Pokemon fifthPokemon;
  @NotNull private Pokemon lastPokemon;

  public void updatePokemonTeam(TeamDocument teamDocument) {

    teamDocument.setFirstPokemon(this.firstPokemon);
    teamDocument.setSecondPokemon(this.secondPokemon);
    teamDocument.setThirdPokemon(this.thirdPokemon);
    teamDocument.setFourthPokemon(this.fourthPokemon);
    teamDocument.setFifthPokemon(this.fifthPokemon);
    teamDocument.setLastPokemon(this.lastPokemon);
  }
}
