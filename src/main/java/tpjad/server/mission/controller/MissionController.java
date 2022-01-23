package tpjad.server.mission.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tpjad.server.mission.dto.MissionDTO;
import tpjad.server.mission.dto.RequestString;
import tpjad.server.mission.model.Mission;
import tpjad.server.mission.service.MissionServiceInterface;
import tpjad.server.team.model.Team;
import tpjad.server.team.service.TeamServiceInterface;

@CrossOrigin
@RestController
@RequestMapping("/api/missions")
public class MissionController {
    MissionServiceInterface missionService;

    TeamServiceInterface teamService;

    public MissionController(MissionServiceInterface missionService, TeamServiceInterface teamService) {
        this.missionService = missionService;
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<?> getAllMissions() {
        try {
            return ResponseEntity.ok().body(missionService.getAllMissions());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createMission(@RequestBody MissionDTO missionDTO) {
        try {
            Mission mission = fromMissionDTOToMission(missionDTO);
            mission.setStatus("ready");
            Team team = mission.getTeam();
            if (team != null) {
                team.setStatus("busy");
                teamService.update(team);
                mission.setStatus("in progress");
            }
            return ResponseEntity.ok().body(missionService.create(mission));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMission(@PathVariable String id, @RequestBody MissionDTO missionDTO) {
        try {
            Mission mission = fromMissionDTOToMission(missionDTO);
            mission.setId(id);
            return ResponseEntity.ok().body(missionService.update(mission));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> changeStatus(@PathVariable String id, @RequestBody RequestString status) {
        try {
            Mission mission = missionService.getMissionById(id);
            mission.setStatus(status.getPayload());
            if (status.getPayload().equals("finished")) {
                Team team = mission.getTeam();
                if (team != null) {
                    team.setStatus("available");
                    teamService.update(team);
                }
            }
            return ResponseEntity.ok().body(missionService.update(mission));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/team")
    public ResponseEntity<?> assignTeam(@PathVariable String id, @RequestBody RequestString teamId) {
        try {
            Mission mission = missionService.getMissionById(id);
            Team team = teamService.getTeamById(teamId.getPayload());
            team.setStatus("busy");
            teamService.update(team);
            mission.setTeam(team);
            mission.setStatus("in progress");
            return ResponseEntity.ok().body(missionService.update(mission));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private Mission fromMissionDTOToMission(MissionDTO missionDTO) {
        Mission mission = new Mission();
        mission.setDescription(missionDTO.getDescription());
        mission.setLocation(missionDTO.getLocation());
        mission.setStatus(missionDTO.getStatus());
        String teamId = missionDTO.getTeam();
        if (teamId != null) {
            Team team = teamService.getTeamById(teamId);
            mission.setTeam(team);
        }
        return mission;
    }
}
