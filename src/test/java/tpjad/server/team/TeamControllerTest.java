package tpjad.server.team;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import tpjad.server.authentication.controller.AuthController;
import tpjad.server.team.controller.TeamController;
import tpjad.server.team.model.Team;
import tpjad.server.team.service.TeamServiceImplementation;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@WebMvcTest(AuthController.class)
@ContextConfiguration(classes = {TeamServiceImplementation.class})
public class TeamControllerTest {

    @MockBean
    TeamServiceImplementation teamServiceImplementation;

    private TeamController teamController;

    @BeforeEach
    void setUp() {
        teamController = new TeamController(teamServiceImplementation);
    }


    @Test
    public void getAllTeams() {
        Team team = new Team("1", "team1", "available");
        Team team2 = new Team("2", "team2", "busy");
        List<Team> teams = new ArrayList<>();
        teams.add(team);
        teams.add(team2);
        when(teamServiceImplementation.getAllTeams()).thenReturn(teams);

        ResponseEntity<?> response = teamController.getAllTeams();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(new Gson().toJson(response.getBody())).isEqualTo(new Gson().toJson(teams));
    }
}
