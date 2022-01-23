package tpjad.server.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tpjad.server.team.service.TeamServiceInterface;
import tpjad.server.user.dtos.UserDTO;
import tpjad.server.user.dtos.UserResponse;
import tpjad.server.user.model.UserEntity;
import tpjad.server.user.service.UserServiceInterface;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {
    UserServiceInterface userService;

    TeamServiceInterface teamService;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserController(UserServiceInterface userService, TeamServiceInterface teamService) {
        this.userService = userService;
        this.teamService = teamService;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
            List<UserEntity> users = userService.getAllUsers();
            List<UserResponse> userResponses = new ArrayList<>();
            users.forEach(userEntity -> userResponses.add(constructUserResponse(userEntity)));
            return ResponseEntity.ok().body(userResponses);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        try {
            return ResponseEntity.ok().body(constructUserResponse(userService.getUserById(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        try {
            userService.delete(id);
            return ResponseEntity.ok().body("User deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        try {
            UserEntity user = fromUserDTOToUser(userDTO);
            return ResponseEntity.ok().body(constructUserResponse(userService.create(user)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody UserDTO userDTO) {
        try {
            UserEntity user = fromUserDTOToUser(userDTO);
            user.setId(id);
            return ResponseEntity.ok().body(constructUserResponse(userService.update(user)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private UserEntity fromUserDTOToUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userDTO.getName());
        userEntity.setBadgeNumber(userDTO.getBadgeNumber());
        userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userEntity.setRole(userService.getUserTypeById(userDTO.getUserType()));
        userEntity.setTeam(teamService.getTeamById(userDTO.getTeam()));
        return userEntity;
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
