package Cims.PFE.Service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Cims.PFE.Dao.OrdreAffPRepository;
import Cims.PFE.Dao.OrdreAffPersRepository;
import Cims.PFE.Dao.OrdreAffTotRepository;
import Cims.PFE.Entities.OrdreAffectationP;
import Cims.PFE.Entities.OrdreAffectationPers;
import Cims.PFE.Entities.OrdreAffectationTot;
@Transactional
@Service
public class OrdreAffTotService {

	@Autowired
	private OrdreAffTotRepository repo;
	
	public List<OrdreAffectationTot> listAll(){
		List<OrdreAffectationTot> ordres = new ArrayList<>();
		repo.findAll().forEach(ordres::add);
		return ordres;
	}
	
	public OrdreAffectationTot save(OrdreAffectationTot o) {
		return repo.save(o);
	}
	
	public OrdreAffectationTot update(Long id, OrdreAffectationTot o){
		o.setIdO_Aff_Tot(id);
		return repo.findById(id).get();
	}
	public OrdreAffectationTot getById(Long id) {
		return repo.findById(id).get();
	}
	public void delete(Long id){
		repo.deleteById(id);
	}
}
