package com.example.pokemondreamteam.interfaces.json.Team;

import com.example.pokemondreamteam.documents.PokemonDocument;
import com.example.pokemondreamteam.interfaces.json.Pokemons.Pokemon;
import com.example.pokemondreamteam.interfaces.json.Pokemons.PokemonResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Team {

    private String teamName;
    private Pokemon firstPokemon;
    private Pokemon secondPokemon;
    private Pokemon thirdPokemon;
    private Pokemon fourthPokemon;
    private Pokemon fifthPokemon;
    private Pokemon lastPokemon;

}
