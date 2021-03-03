package com.example.pokemondreamteam.repository;

import com.example.pokemondreamteam.documents.PokemonDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends MongoRepository<PokemonDocument, String> {
    Boolean existsByPokemonName(String pokemonName);
    PokemonDocument findByPokemonName(String pokemonName);
}
