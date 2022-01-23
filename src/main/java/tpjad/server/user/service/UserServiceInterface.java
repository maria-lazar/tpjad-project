package tpjad.server.user.service;


import tpjad.server.user.model.UserEntity;
import tpjad.server.user.model.UserType;

import java.util.List;

public interface UserServiceInterface {
    List<UserEntity> getAllUsers();

    UserEntity getUserById(String id);

    void delete(String id);

    UserEntity create(UserEntity user);

    UserEntity update(UserEntity user);

    UserType getUserTypeById(String id);
}
