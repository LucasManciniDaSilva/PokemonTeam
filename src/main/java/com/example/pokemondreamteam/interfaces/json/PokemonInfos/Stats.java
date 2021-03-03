package com.example.pokemondreamteam.interfaces.json.PokemonInfos;

import lombok.Data;


@Data
public class Stats {
    private Stat stat;
    private Integer effort;
    private Integer base_stat;



    @Data
    public static class Stat {
        private String Name;
    }
}