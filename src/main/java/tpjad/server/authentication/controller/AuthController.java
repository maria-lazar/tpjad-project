package tpjad.server.authentication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tpjad.server.authentication.dto.LoginRequest;
import tpjad.server.authentication.dto.LoginResponse;
import tpjad.server.security.JwtTokenUtil;
import tpjad.server.user.dto.UserResponse;
import tpjad.server.user.model.UserEntity;

@CrossOrigin
@RestController
@RequestMapping
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            UserEntity user = (UserEntity) authenticate.getPrincipal();

            return ResponseEntity.ok()
                    .body(new LoginResponse(constructUserResponse(user), jwtTokenUtil.generateAccessToken(user)));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        }
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
