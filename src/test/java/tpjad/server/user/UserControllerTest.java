package tpjad.server.user;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import tpjad.server.authentication.controller.AuthController;
import tpjad.server.team.model.Team;
import tpjad.server.team.service.TeamServiceImplementation;
import tpjad.server.user.controller.UserController;
import tpjad.server.user.dto.UserDTO;
import tpjad.server.user.dto.UserResponse;
import tpjad.server.user.model.UserEntity;
import tpjad.server.user.model.UserType;
import tpjad.server.user.service.UserServiceImplementation;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;

@WebMvcTest(AuthController.class)
@ContextConfiguration(classes = {UserServiceImplementation.class, TeamServiceImplementation.class})
public class UserControllerTest {

    @MockBean
    UserServiceImplementation userServiceImplementation;

    @MockBean
    TeamServiceImplementation teamServiceImplementation;

    private UserController userController;

    @BeforeEach
    void setUp() {
        userController = new UserController(userServiceImplementation, teamServiceImplementation);
    }


    @Test
    public void getAllUsers() {
        UserEntity userEntity = new UserEntity("1");
        userEntity.setBadgeNumber("badge");
        List<UserEntity> users = new ArrayList<>();
        users.add(userEntity);
        List<UserResponse> userResponses = new ArrayList<>();
        users.forEach(user -> userResponses.add(constructUserResponse(user)));
        when(userServiceImplementation.getAllUsers()).thenReturn(users);

        ResponseEntity<?> response = userController.getAllUsers();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(new Gson().toJson(response.getBody())).isEqualTo(new Gson().toJson(userResponses));
    }

    @Test
    public void create() {
        Team team = new Team("1", "team1", "available");
        UserType userType = new UserType("1", "POLICEMAN");
        UserDTO userDTO = new UserDTO();
        userDTO.setBadgeNumber("badge");
        userDTO.setPassword("pass");
        userDTO.setTeam("1");
        userDTO.setUserType("1");
        UserEntity userEntity = new UserEntity();
        userEntity.setId("1");
        userEntity.setBadgeNumber("badge");
        userEntity.setPassword("pass");
        userEntity.setTeam(team);
        userEntity.setRole(userType);
        when(userServiceImplementation.getUserTypeById("1")).thenReturn(userType);
        when(teamServiceImplementation.getTeamById("1")).thenReturn(team);
        when(userServiceImplementation.create(userEntity)).thenReturn(userEntity);
        ResponseEntity<?> response = userController.createUser(userDTO);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(new Gson().toJson(response.getBody())).isEqualTo(new Gson().toJson(constructUserResponse(userEntity)));
    }

    @Test
    public void update() {
        Team team = new Team("1", "team1", "available");
        UserType userType = new UserType("1", "POLICEMAN");
        UserDTO userDTO = new UserDTO();
        userDTO.setBadgeNumber("badge");
        userDTO.setPassword("pass");
        userDTO.setTeam("1");
        userDTO.setUserType("1");
        UserEntity userEntity = new UserEntity();
        userEntity.setId("1");
        userEntity.setBadgeNumber("badge");
        userEntity.setPassword("pass");
        userEntity.setTeam(team);
        userEntity.setRole(userType);
        when(userServiceImplementation.getUserTypeById("1")).thenReturn(userType);
        when(teamServiceImplementation.getTeamById("1")).thenReturn(team);
        when(userServiceImplementation.update(userEntity)).thenReturn(userEntity);
        ResponseEntity<?> response = userController.updateUser("1", userDTO);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(new Gson().toJson(response.getBody())).isEqualTo(new Gson().toJson(constructUserResponse(userEntity)));
    }

    private UserResponse constructUserResponse(UserEntity user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setBadgeNumber(user.getBadgeNumber());
        userResponse.setName(user.getName());
        userResponse.setRole(user.getRole());
        userResponse.setTeam(user.getTeam());
        return userResponse;
    }
}
