package tpjad.server.mission;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import tpjad.server.authentication.controller.AuthController;
import tpjad.server.mission.controller.MissionController;
import tpjad.server.mission.model.Mission;
import tpjad.server.mission.service.MissionServiceImplementation;
import tpjad.server.team.model.Team;
import tpjad.server.team.service.TeamServiceImplementation;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@WebMvcTest(AuthController.class)
@ContextConfiguration(classes = {MissionServiceImplementation.class, TeamServiceImplementation.class})
public class MissionControllerTest {

    @MockBean
    MissionServiceImplementation missionServiceImplementation;

    @MockBean
    TeamServiceImplementation teamServiceImplementation;

    private MissionController missionController;

    @BeforeEach
    void setUp() {
        missionController = new MissionController(missionServiceImplementation, teamServiceImplementation);
    }


    @Test
    public void getAllMissions() {
        Team team = new Team("1", "team", "available");
        Mission mission = new Mission();
        mission.setStatus("in progress");
        mission.setId("1");
        mission.setTeam(team);
        mission.setLocation("l");
        mission.setDescription("d");
        List<Mission> missions = new ArrayList<>();
        missions.add(mission);
        when(missionServiceImplementation.getAllMissions()).thenReturn(missions);

        ResponseEntity<?> response = missionController.getAllMissions();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(new Gson().toJson(response.getBody())).isEqualTo(new Gson().toJson(missions));
    }
}
