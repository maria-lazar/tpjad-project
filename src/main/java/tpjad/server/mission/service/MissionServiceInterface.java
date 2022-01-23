package tpjad.server.mission.service;


import tpjad.server.mission.model.Mission;

import java.util.List;

public interface MissionServiceInterface {

    List<Mission> getAllMissions();

    Mission create(Mission mission);

    Mission update(Mission mission);

    Mission getMissionById(String id);
}
