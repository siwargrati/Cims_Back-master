package Cims.PFE.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Cims.PFE.Dao.AffectationPartielleRepository;
import Cims.PFE.Entities.AffectationPartielle;

@Service
public class AffectationPartielleService {
	@Autowired
	private AffectationPartielleRepository repo;
	
	public List<AffectationPartielle> listAll(){
		List<AffectationPartielle> affect = new ArrayList<>();
		repo.findAll().forEach(affect::add);
		return affect;
	}
	public AffectationPartielle save(AffectationPartielle p) {
		return repo.save(p);
	}
	public AffectationPartielle saveNotif(AffectationPartielle p) {
		return repo.save(p);
	}
	
	public AffectationPartielle update(Long id,AffectationPartielle p){
		p.setIdAffect(id);
		return repo.findById(id).get();
	}
	public AffectationPartielle getById(Long id) {
		return repo.findById(id).get();
	}
	public void delete(Long id){
		repo.deleteById(id);
	}


}
