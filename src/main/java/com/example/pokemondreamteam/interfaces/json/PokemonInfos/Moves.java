package com.example.pokemondreamteam.interfaces.json.PokemonInfos;

import lombok.Data;


@Data
public class Moves {
    private Move move;



    @Data
    public static class Move {
        private String Name;
    }
}