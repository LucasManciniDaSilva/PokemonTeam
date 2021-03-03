package com.example.pokemondreamteam.interfaces.controller;


import com.example.pokemondreamteam.interfaces.json.Team.TeamPost;
import com.example.pokemondreamteam.interfaces.json.Team.Team;
import com.example.pokemondreamteam.interfaces.json.Team.TeamPut;
import com.example.pokemondreamteam.services.TeamService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;


@RestController
@Controller
@RequestMapping(path = "/team")
@Validated
public class TeamController implements BaseController<Team> {

  private TeamService teamService;

  public TeamController(TeamService teamService) {
    this.teamService = teamService;
  }

  @PostMapping
  public ResponseEntity<Team> PostPokemonTeam(@RequestBody @Valid TeamPost teamPost) {

    final Team teamResponse = this.teamService
            .teamPost(teamPost);


    return ResponseEntity.status(HttpStatus.CREATED).body(teamResponse);
  }

  @GetMapping("/{_id}")
  public ResponseEntity<Team> getTeam(
          @PathVariable ObjectId _id) {
    return ok(this.teamService.getTeam(_id));
  }

  @PutMapping("/{_id}")
  public ResponseEntity<Team> putTeam(
          @PathVariable ObjectId _id,
          @RequestBody @Valid TeamPut teamPut
  ) {
    this.teamService.putTeam(_id, teamPut);
    return ok(this.teamService.getTeam(_id));
  }

  @DeleteMapping("/{_id}")
  public ResponseEntity<Void> deleteTeam(@PathVariable ObjectId _id) {
    this.teamService.deleteTeam(_id);
    return noContent();
  }


}
