package com.example.pokemondreamteam.interfaces.json.Team;

import com.example.pokemondreamteam.documents.TeamDocument;
import com.example.pokemondreamteam.interfaces.json.Pokemons.Pokemon;
import com.example.pokemondreamteam.repository.PokemonRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamPost {

  @NotNull private String firstPokemon;
  @NotNull private String secondPokemon;
  @NotNull private String thirdPokemon;
  @NotNull private String fourthPokemon;
  @NotNull private String fifthPokemon;
  @NotNull private String lastPokemon;

  public List<String> teamPostList(TeamPost teamPost) {
    List<String> teamPostList = new ArrayList<>();
    teamPostList.add(teamPost.getFirstPokemon());
    teamPostList.add(teamPost.getSecondPokemon());
    teamPostList.add(teamPost.getThirdPokemon());
    teamPostList.add(teamPost.getFourthPokemon());
    teamPostList.add(teamPost.getFifthPokemon());
    teamPostList.add(teamPost.getLastPokemon());
    return teamPostList;
  }
}
