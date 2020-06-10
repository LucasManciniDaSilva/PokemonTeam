package com.example.pokemondreamteam.schemas;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
public class TeamSchema {

    @Id
    public UUID teamId;
    public String firstPokemon;
    public String secondPokemon;
    public String thirdPokemon;
    public String fourthPokemon;
    public String fifthPokemon;
    public String lastPokemon;


}