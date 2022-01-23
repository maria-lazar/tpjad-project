package tpjad.server.team.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpjad.server.team.dto.TeamDTO;
import tpjad.server.team.model.Team;
import tpjad.server.team.service.TeamServiceInterface;

@CrossOrigin
@RestController
@RequestMapping("/api/teams")
public class TeamController {
    TeamServiceInterface teamService;

    public TeamController(TeamServiceInterface teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<?> getAllTeams() {
        try {
            return ResponseEntity.ok().body(teamService.getAllTeams());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeamById(@PathVariable String id) {
        try {
            return ResponseEntity.ok().body(teamService.getTeamById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createTeam(@RequestBody TeamDTO teamDTO) {
        try {
            Team team = fromTeamDTOToTeam(teamDTO);
            return ResponseEntity.ok().body(teamService.create(team));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable String id) {
        try {
            teamService.delete(id);
            return ResponseEntity.ok().body("Team deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeam(@PathVariable String id, @RequestBody TeamDTO teamDTO) {
        try {
            Team team = fromTeamDTOToTeam(teamDTO);
            team.setId(id);
            return ResponseEntity.ok().body(teamService.update(team));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private Team fromTeamDTOToTeam(TeamDTO teamDTO) {
        Team team = new Team();
        team.setName(teamDTO.getName());
        team.setStatus(teamDTO.getStatus());
        return team;
    }
}
