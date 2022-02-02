package Cims.PFE.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Cims.PFE.Dao.FonctionRepository;
import Cims.PFE.Entities.Fonction;


@Service
public class FonctionService {

	@Autowired
	private FonctionRepository fonctionRepository ;
	
	public List<Fonction> listAll(){
		List<Fonction> fonctions = new ArrayList<>();
		fonctionRepository.findAll().forEach(fonctions::add);
		return fonctions;
	}
	
	public Fonction save(Fonction g) {
		return fonctionRepository.save(g);
	}
	
	public Fonction update(Long id, Fonction g){
		g.setId_fonction(id);
		return fonctionRepository.findById(id).get();
	}
	public Fonction getById(Long id) {
		return fonctionRepository.findById(id).get();
	}
	public boolean delete(Long id){
		fonctionRepository.deleteById(id);
		if(fonctionRepository.existsById(id)==false) {
			return true;
		}
		return false;
	}
}
