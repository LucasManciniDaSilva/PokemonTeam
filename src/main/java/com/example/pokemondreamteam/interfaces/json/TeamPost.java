package com.example.pokemondreamteam.interfaces.json;

import com.example.pokemondreamteam.documents.TeamDocument;
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
  public String firstPokemon;
  public String secondPokemon;
  public String thirdPokemon;
  public String fourthPokemon;
  public String fifthPokemon;
  public String lastPokemon;

  public TeamDocument toTeamSchema() {

      return TeamDocument.builder()
          .firstPokemon(this.firstPokemon)
          .secondPokemon(this.secondPokemon)
          .thirdPokemon(this.thirdPokemon)
          .fourthPokemon(this.fourthPokemon)
          .fifthPokemon(this.fifthPokemon)
          .lastPokemon(this.lastPokemon)
          .build();
  }
}
