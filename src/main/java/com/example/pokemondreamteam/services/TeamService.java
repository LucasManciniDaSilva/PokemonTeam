package com.example.pokemondreamteam.services;

import com.example.pokemondreamteam.interfaces.json.Team.Team;
import com.example.pokemondreamteam.interfaces.json.Team.TeamPost;
import com.example.pokemondreamteam.interfaces.json.Team.TeamPatch;
import org.bson.types.ObjectId;
import org.springframework.transaction.annotation.Transactional;


public interface TeamService {

    Team teamPost(TeamPost teamPost);
    Team getTeam(ObjectId _id);

    @Transactional
    Team patchTeam(ObjectId _id, TeamPatch teamPatch);

    @Transactional
    void deleteTeam(ObjectId _id);
}
