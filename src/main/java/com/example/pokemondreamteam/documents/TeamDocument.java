package com.example.pokemondreamteam.documents;

import com.example.pokemondreamteam.interfaces.json.Pokemons.Pokemon;
import com.example.pokemondreamteam.interfaces.json.Team.Team;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class TeamDocument {

  private Pokemon firstPokemon;
  private Pokemon secondPokemon;
  private Pokemon thirdPokemon;
  private Pokemon fourthPokemon;
  private Pokemon fifthPokemon;
  private Pokemon lastPokemon;
  @Id
  private String teamName;

  public Team toTeam() {
    return Team.builder()
        .teamName(this.teamName)
        .firstPokemon(this.firstPokemon)
        .secondPokemon(this.secondPokemon)
        .thirdPokemon(this.thirdPokemon)
        .fourthPokemon(this.fourthPokemon)
        .fifthPokemon(this.fifthPokemon)
        .lastPokemon(this.lastPokemon)
        .build();
  }

}
