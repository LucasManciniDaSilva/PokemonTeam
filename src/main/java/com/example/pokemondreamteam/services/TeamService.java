package com.example.pokemondreamteam.services;

import com.example.pokemondreamteam.documents.TeamDocument;
import com.example.pokemondreamteam.interfaces.json.Team;
import com.example.pokemondreamteam.interfaces.json.TeamPost;
import com.example.pokemondreamteam.interfaces.response.TeamResponse;
import org.bson.types.ObjectId;

import java.util.Optional;


public interface TeamService {

    TeamResponse teamPost(TeamPost teamPost);
    TeamDocument getTeam(ObjectId _id);
    Team getTeamById(ObjectId _id);
}
