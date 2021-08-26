package com.example.pokemondreamteam.repository;

import com.example.pokemondreamteam.documents.TeamDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends MongoRepository<TeamDocument, String> {
    Optional<TeamDocument>  findByTeamName(String teamName);
    Boolean existsByTeamName(String teamName);
}
