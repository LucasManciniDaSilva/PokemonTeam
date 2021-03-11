package com.example.pokemondreamteam.services;

import com.example.pokemondreamteam.documents.TeamDocument;
import com.example.pokemondreamteam.interfaces.json.Team.TeamPost;

import java.util.List;

public interface PokemonService {

    TeamDocument saveTeamSchema(TeamPost teamPost);
    void updateTeam(TeamDocument teamDocument, List<String> teamPatchList);

}
