package tpjad.server.user.service;

import org.springframework.stereotype.Service;
import tpjad.server.user.model.UserEntity;
import tpjad.server.user.model.UserType;
import tpjad.server.user.repository.UserRepository;
import tpjad.server.user.repository.UserTypeRepository;

import java.util.List;


@Service(value = "userService")
public class UserServiceImplementation implements UserServiceInterface {
    private final UserRepository userRepository;

    private final UserTypeRepository userTypeRepository;

    public UserServiceImplementation(UserRepository userRepository, UserTypeRepository userTypeRepository) {
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity getUserById(String id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserEntity create(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public UserEntity update(UserEntity user) {
        userRepository.findById(user.getId()).orElseThrow();
        return userRepository.save(user);
    }

    @Override
    public UserType getUserTypeById(String id) {
        return userTypeRepository.findById(id).orElseThrow();
    }
}
