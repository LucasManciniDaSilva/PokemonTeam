package com.example.pokemondreamteam.interfaces.json.Team;

import com.example.pokemondreamteam.documents.PokemonDocument;
import com.example.pokemondreamteam.documents.TeamDocument;
import com.example.pokemondreamteam.interfaces.json.Pokemons.Pokemon;
import com.example.pokemondreamteam.interfaces.json.Pokemons.PokemonResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamPatch {

  private String firstPokemon;
  private String secondPokemon;
  private String thirdPokemon;
  private String fourthPokemon;
  private String fifthPokemon;
  private String lastPokemon;

    public List<String> teamPatchList(TeamPatch teamPatch) {
    List<String> teamPatchList = new ArrayList<>();
    teamPatchList.add(teamPatch.getFirstPokemon());
    teamPatchList.add(teamPatch.getSecondPokemon());
    teamPatchList.add(teamPatch.getThirdPokemon());
    teamPatchList.add(teamPatch.getFourthPokemon());
    teamPatchList.add(teamPatch.getFifthPokemon());
    teamPatchList.add(teamPatch.getLastPokemon());
    return teamPatchList;
  }
}