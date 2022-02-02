package Cims.PFE.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Cims.PFE.Dao.OrdreAffPersRepository;
import Cims.PFE.Dao.OrdreAffTotRepository;
//import Cims.PFE.Entities.OrdreAffectationP;
import Cims.PFE.Entities.OrdreAffectationPers;
import Cims.PFE.Entities.OrdreAffectationTot;
import Cims.PFE.Service.OrdreAffPersService;
import Cims.PFE.Service.OrdreAffTotService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class OrdreAffTotController {
	@Autowired
	private OrdreAffTotService service;
	@Autowired
	private OrdreAffTotRepository repo;
	
	@GetMapping(value="/listOrdresAffTot")
	public List<OrdreAffectationTot> listOrdre(){
		return service.listAll();
	}
	
	@PostMapping(value="/addOrdreAffTot")
	public OrdreAffectationTot save(@RequestBody OrdreAffectationTot o) {
		return service.save(o);
	}
	
	@PutMapping(value="/updateOrdreAffTot/{id}")
	public  ResponseEntity<OrdreAffectationTot> update(@PathVariable(name="id") Long id,@RequestBody OrdreAffectationTot o){
		OrdreAffectationTot ordre=service.getById(id);
		ordre.setAffectationTotale(o.getAffectationTotale());
	
		final OrdreAffectationTot updatedOrdre=service.save(ordre);
		return ResponseEntity.ok(updatedOrdre);
	}
	
	@GetMapping(value="/getOrdreAffTot/{id}")
	public OrdreAffectationTot get(@PathVariable(name="id") Long id) {
		return service.getById(id);
	}
	
	@DeleteMapping(value="/deleteOrdreAffTot/{id}")
	public void delete(@PathVariable(name="id") Long id){
		service.delete(id);
	}
	@GetMapping(value="/getOrdreOfAffTot/{id}")
	public OrdreAffectationTot getOrdreOfAffTot(@PathVariable(name="id")Long idAffectT) {
		return repo.getOrdreOfAffTot(idAffectT);
	}

}
