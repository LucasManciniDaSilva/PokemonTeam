package com.example.pokemondreamteam.interfaces.json.PokemonInfos;

import lombok.Data;


@Data
public class Types {
    private Type type;



    @Data
    public static class Type {
        private String Name;
    }
}