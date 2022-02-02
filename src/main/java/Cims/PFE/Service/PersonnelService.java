package Cims.PFE.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import Cims.PFE.Dao.FonctionRepository;
import Cims.PFE.Dao.GradeRepository;
import Cims.PFE.Dao.PersonnelRepository;
import Cims.PFE.Dao.SiteRepository;
import Cims.PFE.Entities.Fonction;
import Cims.PFE.Entities.Grade;
import Cims.PFE.Entities.Personnel;
import Cims.PFE.Entities.Site;
import Cims.PFE.Entities.Structure;
@Service
public class PersonnelService {
	@Autowired
	private PersonnelRepository personnelRepository;
	public List<Personnel> listAll(){
		List<Personnel> personnels = new ArrayList<>();
		personnelRepository.findAll().forEach(personnels::add);
		return personnelRepository.findAll();
	}
	public Personnel save(Personnel p) {
		return personnelRepository.save(p);
	}

	public Personnel update(Long id, Personnel p){
		p.setId_personnel(id);
		return personnelRepository.findById(id).get();
	}
	public Personnel getById(Long id) {
		return personnelRepository.findById(id).get();
	}
	public boolean delete(Long id){
		personnelRepository.deleteById(id);
		if(personnelRepository.existsById(id)==false)
		{
			return true;
		}
			return false;
	}
}