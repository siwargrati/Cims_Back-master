package Cims.PFE.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Cims.PFE.Dao.OrdreAffPAttRepository;
import Cims.PFE.Dao.OrdreAffPRepository;
import Cims.PFE.Entities.OrdreAffectationP;
import Cims.PFE.Entities.OrdreAffectationPAtt;

@Service
public class OrdreAffPAttService {

	@Autowired
	private OrdreAffPAttRepository repo;
	
	public List<OrdreAffectationPAtt> listAll(){
		List<OrdreAffectationPAtt> ordres = new ArrayList<>();
		repo.findAll().forEach(ordres::add);
		return ordres;
	}
	
	public OrdreAffectationPAtt save(OrdreAffectationPAtt o) {
		return repo.save(o);
	}
	
	public OrdreAffectationPAtt update(Long id, OrdreAffectationPAtt o){
		o.setIdO_AffAtt(id);;
		return repo.findById(id).get();
	}
	public OrdreAffectationPAtt getById(Long id) {
		return repo.findById(id).get();
	}
	public void delete(Long id){
		repo.deleteById(id);
	}
}
