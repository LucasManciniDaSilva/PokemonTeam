package com.example.pokemondreamteam.interfaces.json.PokemonInfos;

import lombok.Data;


@Data
public class Abilities {
    private Ability ability;



    @Data
    public static class Ability {
        private String Name;
    }
}