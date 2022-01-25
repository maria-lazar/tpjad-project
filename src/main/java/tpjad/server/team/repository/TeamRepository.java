package tpjad.server.team.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpjad.server.team.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {

}
