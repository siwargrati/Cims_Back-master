package Cims.PFE.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Cims.PFE.Dao.OrdreAffPRepository;
import Cims.PFE.Entities.OrdreAffectationP;

@Service
public class OrdreAffPService {

	@Autowired
	private OrdreAffPRepository repo;
	
	public List<OrdreAffectationP> listAll(){
		List<OrdreAffectationP> ordres = new ArrayList<>();
		repo.findAll().forEach(ordres::add);
		return ordres;
	}
	
	public OrdreAffectationP save(OrdreAffectationP o) {
		return repo.save(o);
	}
	
	public OrdreAffectationP update(Long id, OrdreAffectationP o){
		o.setIdO_Aff(id);;
		return repo.findById(id).get();
	}
	public OrdreAffectationP getById(Long id) {
		return repo.findById(id).get();
	}
	public void delete(Long id){
		repo.deleteById(id);
	}
}
