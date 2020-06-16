package com.example.pokemondreamteam.interfaces.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Team {

    public ObjectId _id;
    public String firstPokemon;
    public String secondPokemon;
    public String thirdPokemon;
    public String fourthPokemon;
    public String fifthPokemon;
    public String lastPokemon;

}
