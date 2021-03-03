package com.example.pokemondreamteam.interfaces.json.Team;

import com.example.pokemondreamteam.documents.TeamDocument;
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
public class TeamPost {

  @NotNull
  private String firstPokemon;
  @NotNull
  private String secondPokemon;
  @NotNull
  private String thirdPokemon;
  @NotNull
  private String fourthPokemon;
  @NotNull
  private String fifthPokemon;
  @NotNull
  private String lastPokemon;
}
