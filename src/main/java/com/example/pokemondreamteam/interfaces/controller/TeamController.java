package com.example.pokemondreamteam.interfaces.controller;


import com.example.pokemondreamteam.interfaces.json.TeamPost;
import com.example.pokemondreamteam.interfaces.json.Team;
import com.example.pokemondreamteam.interfaces.response.TeamResponse;
import com.example.pokemondreamteam.services.TeamService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "/team")
@Validated
public class TeamController implements BaseController<Team> {

  private TeamService teamService;

  public TeamController(TeamService teamService) {
    this.teamService = teamService;
  }

  @PostMapping
  public ResponseEntity<TeamResponse> PostPokemonTeam(@RequestBody @Valid TeamPost teamPost) {

    final TeamResponse teamResponse = this.teamService
            .teamPost(teamPost);

    return ResponseEntity.status(HttpStatus.CREATED).body(teamResponse);
  }

  @GetMapping("/{_id}")
  public ResponseEntity<Team> getTeam(
          @PathVariable ObjectId _id) {
    return ok(this.teamService.getTeamById(_id));
  }
    }
