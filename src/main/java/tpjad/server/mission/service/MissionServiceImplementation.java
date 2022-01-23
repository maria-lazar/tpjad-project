package tpjad.server.mission.service;

import org.springframework.stereotype.Service;
import tpjad.server.mission.model.Mission;
import tpjad.server.mission.repository.MissionRepository;

import java.util.List;


@Service(value = "missionService")
public class MissionServiceImplementation implements MissionServiceInterface {
    MissionRepository missionRepository;

    public MissionServiceImplementation(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    @Override
    public List<Mission> getAllMissions() {
        return missionRepository.findAll();
    }

    @Override
    public Mission create(Mission mission) {
        return missionRepository.save(mission);
    }

    @Override
    public Mission update(Mission mission) {
        missionRepository.findById(mission.getId()).orElseThrow();
        return missionRepository.save(mission);
    }

    @Override
    public Mission getMissionById(String id) {
        return missionRepository.findById(id).orElseThrow();
    }
}
