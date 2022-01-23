package tpjad.server.mission.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tpjad.server.mission.model.Mission;
import tpjad.server.team.model.Team;

@Repository
public interface MissionRepository extends JpaRepository<Mission, String> {
}
