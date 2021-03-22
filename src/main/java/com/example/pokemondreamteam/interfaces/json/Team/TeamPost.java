package com.example.pokemondreamteam.interfaces.json.Team;

import com.example.pokemondreamteam.documents.TeamDocument;
import com.example.pokemondreamteam.interfaces.json.Pokemons.Pokemon;
import com.example.pokemondreamteam.repository.PokemonRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamPost {

  @NotBlank private String firstPokemon;
  @NotBlank private String secondPokemon;
  @NotBlank private String thirdPokemon;
  @NotBlank private String fourthPokemon;
  @NotBlank private String fifthPokemon;
  @NotBlank private String lastPokemon;

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
