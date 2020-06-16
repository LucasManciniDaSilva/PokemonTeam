package com.example.pokemondreamteam.repository;

import com.example.pokemondreamteam.documents.TeamDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends MongoRepository<TeamDocument, String> {
    TeamDocument findBy_id(ObjectId id);
}
