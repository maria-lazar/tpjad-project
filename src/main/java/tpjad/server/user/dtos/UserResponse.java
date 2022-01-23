package tpjad.server.user.dtos;

import tpjad.server.team.model.Team;
import tpjad.server.user.model.UserType;

public class UserResponse {
    private String id;
    private String badgeNumber;
    private String name;
    private UserType role;
    private Team team;

    public UserResponse() {
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(String email) {
        this.badgeNumber = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getRole() {
        return role;
    }

    public void setRole(UserType role) {
        this.role = role;
    }
}
