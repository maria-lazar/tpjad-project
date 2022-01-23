package tpjad.server.team.service;


import tpjad.server.team.model.Team;

import java.util.List;

public interface TeamServiceInterface {
    List<Team> getAllTeams();

    Team getTeamById(String id);

    Team create(Team team);

    void delete(String id);

    Team update(Team team);
}
