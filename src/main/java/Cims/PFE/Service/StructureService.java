package Cims.PFE.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Cims.PFE.Dao.StructureRepository;
import Cims.PFE.Entities.AffectationPartielle;
import Cims.PFE.Entities.Structure;

@Service
public class StructureService {
	@Autowired
	private StructureRepository repo;
	
	public List<Structure> listAll(){
		List<Structure> struct = new ArrayList<>();
		repo.findAll().forEach(struct::add);
		return struct;
	}
	public Structure save(Structure d) {
		return repo.save(d);
	}
	public Structure update(Long id,Structure d){
		d.setId_struct(id);
		return repo.findById(id).get();
	}
	public Structure getById(Long id) {
		return repo.findById(id).get();
	}
	public boolean delete(Long id){
		repo.deleteById(id);
		if(repo.existsById(id)==false) {
			return true;
		}
		return false;
	}

}
