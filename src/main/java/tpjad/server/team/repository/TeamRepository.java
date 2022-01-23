package tpjad.server.team.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpjad.server.team.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {

    //    @Query("SELECT max(user.id) FROM UserEntity user")
//    Long getMaxId();
//
//    @Transactional
//    @Modifying
//    @Query("UPDATE UserEntity user SET user.token = :jwtParam WHERE user.username = :usernameParam")
//    void updateToken(@Param("usernameParam") String username, @Param("jwtParam") String jwt);
//
//    @Transactional
//    @Modifying
//    @Query("UPDATE UserEntity user SET user.username = :usernameParam WHERE user.token = :jwtParam")
//    void updateUsername(@Param("jwtParam") String jwt, @Param("usernameParam") String username);
//
//    @Transactional
//    @Modifying
//    @Query("UPDATE UserEntity user SET user.full_name = :fullNameParam WHERE user.token = :jwtParam")
//    void updateFullName(@Param("jwtParam") String jwt, @Param("fullNameParam") String fullName);
//
//    @Transactional
//    @Modifying
//    @Query("UPDATE UserEntity user SET user.password = :passwordParam WHERE user.token = :jwtParam")
//    void updatePassword(@Param("jwtParam") String jwt, @Param("passwordParam") String password);
//
//    @Transactional
//    @Modifying
//    @Query("UPDATE UserEntity user SET user.badgeNumber = :emailParam WHERE user.token = :jwtParam")
//    void updateEmail(@Param("jwtParam") String jwt, @Param("emailParam") String username);
//
//    @Transactional
//    @Modifying
//    @Query("DELETE FROM UserEntity user WHERE user.username = :username")
//    void deleteUser(@Param("username") String username);
//
}
