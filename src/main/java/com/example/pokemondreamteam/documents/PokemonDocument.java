package com.example.pokemondreamteam.documents;

import com.example.pokemondreamteam.interfaces.json.PokemonInfos.Abilities;
import com.example.pokemondreamteam.interfaces.json.PokemonInfos.Moves;
import com.example.pokemondreamteam.interfaces.json.PokemonInfos.Stats;
import com.example.pokemondreamteam.interfaces.json.PokemonInfos.Types;
import com.example.pokemondreamteam.interfaces.json.Pokemons.Pokemon;
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

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class PokemonDocument {

    @Id
    @JsonSerialize(using= ToStringSerializer.class)
    private ObjectId _id;
    @Field("PokemonNumber")
    private Integer pokedexNumber;
    @Field("PokemonName")
    private String pokemonName;
    @Field("Base_Experience")
    private Integer baseExperience;
    @Field("Abilities")
    private List<Abilities.Ability> abilities;
    @Field("Types")
    private List<Types.Type> types;
    @Field("Stats")
    private List<Stats> stats;
    @Field("Moves")
    private List<Moves.Move> moves;

    public Pokemon toPokemon() {
    return Pokemon.builder()
        .id(this.pokedexNumber)
        .name(this.pokemonName)
        .baseExperience(this.baseExperience)
        .abilities(this.abilities)
        .types(this.types)
        .stats(this.stats)
        .moves(this.moves)
        .build();
    }
}
