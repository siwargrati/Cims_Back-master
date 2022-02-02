package Cims.PFE.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Cims.PFE.Dao.OrdreMissionRepository;
import Cims.PFE.Entities.OrdreMission;

@Service
public class OrdreMissionService {

	@Autowired
	private OrdreMissionRepository repo;
	
	public List<OrdreMission> listAll(){
		List<OrdreMission> ordres = new ArrayList<>();
		repo.findAll().forEach(ordres::add);
		return ordres;
	}
	
	public OrdreMission save(OrdreMission o) {
		return repo.save(o);
	}
	
	public OrdreMission update(Long id, OrdreMission o){
		o.setIdO_Miss(id);
		return repo.findById(id).get();
	}
	public OrdreMission getById(Long id) {
		return repo.findById(id).get();
	}
	public void delete(Long id){
		repo.deleteById(id);
	}
}
