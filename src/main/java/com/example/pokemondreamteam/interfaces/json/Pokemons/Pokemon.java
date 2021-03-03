package com.example.pokemondreamteam.interfaces.json.Pokemons;

import com.example.pokemondreamteam.documents.PokemonDocument;
import com.example.pokemondreamteam.interfaces.json.PokemonInfos.Abilities;
import com.example.pokemondreamteam.interfaces.json.PokemonInfos.Moves;
import com.example.pokemondreamteam.interfaces.json.PokemonInfos.Stats;
import com.example.pokemondreamteam.interfaces.json.PokemonInfos.Types;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pokemon {

    @JsonSerialize(using= ToStringSerializer.class)
    private Integer id;
    private String name;
    private Integer baseExperience;
    private List<Abilities.Ability> abilities;
    private List<Types.Type> types;
    private List<Stats> stats;
    private List<Moves.Move> moves;

    public PokemonDocument toPokemonDocument() {

        return PokemonDocument.builder()
                .pokedexNumber(this.id)
                .pokemonName(this.name)
                .baseExperience(this.baseExperience)
                .abilities(this.abilities)
                .types(this.types)
                .stats(this.stats)
                .build();
    }
}
