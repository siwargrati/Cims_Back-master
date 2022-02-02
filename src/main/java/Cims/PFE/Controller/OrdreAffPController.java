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

import Cims.PFE.Dao.OrdreAffPRepository;
import Cims.PFE.Entities.OrdreAffectationP;
import Cims.PFE.Service.OrdreAffPService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class OrdreAffPController {
	@Autowired
	private OrdreAffPService service;
	@Autowired
	private OrdreAffPRepository repo;
	
	@GetMapping(value="/listOrdresAffP")
	public List<OrdreAffectationP> listOrdre(){
		return service.listAll();
	}
	
	@PostMapping(value="/addOrdreAffP")
	public OrdreAffectationP save(@RequestBody OrdreAffectationP o) {
		return service.save(o);
	}
	
	@PutMapping(value="/updateOrdreAffP/{id}")
	public  ResponseEntity<OrdreAffectationP> update(@PathVariable(name="id") Long id,@RequestBody OrdreAffectationP o){
		OrdreAffectationP ordre=service.getById(id);
		ordre.setAffectationPartielle(o.getAffectationPartielle());
	
		final OrdreAffectationP updatedOrdre=service.save(ordre);
		return ResponseEntity.ok(updatedOrdre);
	}
	
	@GetMapping(value="/getOrdreAffP/{id}")
	public OrdreAffectationP get(@PathVariable(name="id") Long id) {
		return service.getById(id);
	}
	
	@DeleteMapping(value="/deleteOrdreAffP/{id}")
	public void delete(@PathVariable(name="id") Long id){
		service.delete(id);
	}
	@GetMapping(value="/getOrdreOfAff/{id}")
	public OrdreAffectationP getOrdreOfAff(@PathVariable(name="id")Long idAffect) {
		return repo.getOrdreOfAff(idAffect);
	}

}
