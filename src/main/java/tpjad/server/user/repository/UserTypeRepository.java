package tpjad.server.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpjad.server.user.model.UserEntity;
import tpjad.server.user.model.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, String> {

}
