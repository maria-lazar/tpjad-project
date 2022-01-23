package tpjad.server.team.service;

import org.springframework.stereotype.Service;
import tpjad.server.team.model.Team;
import tpjad.server.team.repository.TeamRepository;

import java.util.List;


@Service(value = "teamService")
public class TeamServiceImplementation implements TeamServiceInterface {
    TeamRepository teamRepository;

    public TeamServiceImplementation(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team getTeamById(String id) {
        return teamRepository.findById(id).orElseThrow();
    }

    @Override
    public Team create(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public void delete(String id) {
        teamRepository.deleteById(id);
    }

    @Override
    public Team update(Team team) {
        teamRepository.findById(team.getId()).orElseThrow();
        return teamRepository.save(team);
    }
}
