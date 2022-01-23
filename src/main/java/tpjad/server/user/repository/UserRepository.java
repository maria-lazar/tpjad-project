package tpjad.server.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tpjad.server.user.model.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    @Override
    @Query("SELECT u FROM UserEntity u WHERE u.deleted = false")
    List<UserEntity> findAll();

    @Override
    @Query("SELECT u FROM UserEntity u WHERE u.id = ?1 AND u.deleted = false")
    Optional<UserEntity> findById(String s);

    @Query("SELECT u FROM UserEntity u WHERE u.badgeNumber = ?1")
    Optional<UserEntity> findByBadgeNumber(String badgeNumber);

    @Override
    @Modifying
    @Query("update UserEntity u set u.deleted = true where u.id = ?1")
    void deleteById(String s);
}
