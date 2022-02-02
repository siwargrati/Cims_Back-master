package Cims.PFE.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Cims.PFE.Dao.MissionRepository;
import Cims.PFE.Entities.Mission;

@Service
public class MissionService {
	@Autowired
	private MissionRepository missionRepository;
	
	public List<Mission> listAll(){
		List<Mission> missions = new ArrayList<>();
		missionRepository.findAll().forEach(missions::add);
		return missions;
	}
	
	public Mission save(Mission m) {
		return missionRepository.save(m);
	}
	
	
	public Mission getById(Long id) {
		return missionRepository.findById(id).get();
	}
	public void delete(Long id){
		missionRepository.deleteById(id);
	}
}
