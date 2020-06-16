package com.example.pokemondreamteam.interfaces.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamResponse {

  public String teamId;
  public String firstPokemon;
  public String secondPokemon;
  public String thirdPokemon;
  public String fourthPokemon;
  public String fifthPokemon;
  public String lastPokemon;
}