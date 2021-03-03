package com.example.pokemondreamteam.services;

import com.example.pokemondreamteam.interfaces.json.Team.Team;
import com.example.pokemondreamteam.interfaces.json.Team.TeamPost;
import com.example.pokemondreamteam.interfaces.json.Team.TeamPut;
import org.bson.types.ObjectId;
import org.springframework.transaction.annotation.Transactional;


public interface TeamService {

    Team teamPost(TeamPost teamPost);
    Team getTeam(ObjectId _id);

    @Transactional
    Team putTeam(ObjectId _id, TeamPut teamPut);

    @Transactional
    void deleteTeam(ObjectId _id);
}
