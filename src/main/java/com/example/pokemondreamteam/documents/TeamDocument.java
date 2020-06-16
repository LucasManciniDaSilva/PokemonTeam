package com.example.pokemondreamteam.documents;

import com.example.pokemondreamteam.interfaces.json.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.Null;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class TeamDocument {

  @MongoId
  public ObjectId _id;
  @Transient
  public String _class;
  public String firstPokemon;
  public String secondPokemon;
  public String thirdPokemon;
  public String fourthPokemon;
  public String fifthPokemon;
  public String lastPokemon;

  public Team toTeam() {
    return Team.builder()
        .firstPokemon(this.firstPokemon)
        .secondPokemon(this.secondPokemon)
        .thirdPokemon(this.thirdPokemon)
        .fourthPokemon(this.fourthPokemon)
        .fifthPokemon(this.fifthPokemon)
        .lastPokemon(this.lastPokemon)
        .build();
  }
}
