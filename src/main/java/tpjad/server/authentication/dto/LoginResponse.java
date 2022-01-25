package tpjad.server.authentication.dto;

import tpjad.server.user.dto.UserResponse;

public class LoginResponse {
   private UserResponse user;
   private String token;

    public LoginResponse(UserResponse user, String token) {
        this.user = user;
        this.token = token;
    }

    public LoginResponse() {
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
