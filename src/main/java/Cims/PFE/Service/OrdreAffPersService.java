package Cims.PFE.Service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Cims.PFE.Dao.OrdreAffPRepository;
import Cims.PFE.Dao.OrdreAffPersRepository;
import Cims.PFE.Entities.OrdreAffectationP;
import Cims.PFE.Entities.OrdreAffectationPers;
@Transactional
@Service
public class OrdreAffPersService {

	@Autowired
	private OrdreAffPersRepository repo;
	
	public List<OrdreAffectationPers> listAll(){
		List<OrdreAffectationPers> ordres = new ArrayList<>();
		repo.findAll().forEach(ordres::add);
		return ordres;
	}
	
	public OrdreAffectationPers save(OrdreAffectationPers o) {
		return repo.save(o);
	}
	
	public OrdreAffectationPers update(Long id, OrdreAffectationPers o){
		o.setIdO_Aff_Pers(id);
		return repo.findById(id).get();
	}
	public OrdreAffectationPers getById(Long id) {
		return repo.findById(id).get();
	}
	public void delete(Long id){
		repo.deleteById(id);
	}
}
