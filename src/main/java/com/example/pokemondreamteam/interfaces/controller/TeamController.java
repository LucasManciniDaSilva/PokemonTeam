package com.example.pokemondreamteam.interfaces.controller;


import com.example.pokemondreamteam.interfaces.json.Team.TeamPost;
import com.example.pokemondreamteam.interfaces.json.Team.Team;
import com.example.pokemondreamteam.interfaces.json.Team.TeamPatch;
import com.example.pokemondreamteam.services.TeamService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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

    HttpHeaders teamId = new HttpHeaders();

    teamId.set("_id", teamResponse.get_id().toString());


    return ResponseEntity.status(HttpStatus.CREATED).headers(teamId).body(null);
  }

  @GetMapping("/{_id}")
  public ResponseEntity<Team> getTeam(
          @PathVariable ObjectId _id) {
    return ok(this.teamService.getTeam(_id));
  }

  @PatchMapping("/{_id}")
  public ResponseEntity<Team> patchTeam(
          @PathVariable ObjectId _id,
          @RequestBody @Valid TeamPatch teamPatch
  ) {
    this.teamService.patchTeam(_id, teamPatch);
    return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(null);
  }

  @DeleteMapping("/{_id}")
  public ResponseEntity<Void> deleteTeam(@PathVariable ObjectId _id) {
    this.teamService.deleteTeam(_id);
    return noContent();
  }


}
