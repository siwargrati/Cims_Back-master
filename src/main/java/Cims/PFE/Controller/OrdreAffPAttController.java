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

import Cims.PFE.Dao.OrdreAffPAttRepository;
import Cims.PFE.Entities.OrdreAffectationPAtt;
import Cims.PFE.Service.OrdreAffPAttService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class OrdreAffPAttController {
	@Autowired
	private OrdreAffPAttService service;
	@Autowired
	private OrdreAffPAttRepository repo;
	
	@GetMapping(value="/listOrdresAffPAtt")
	public List<OrdreAffectationPAtt> listOrdre(){
		return service.listAll();
	}
	
	@PostMapping(value="/addOrdreAffPAtt")
	public OrdreAffectationPAtt save(@RequestBody OrdreAffectationPAtt o) {
		return service.save(o);
	}
	
	@PutMapping(value="/updateOrdreAffPAtt/{id}")
	public  ResponseEntity<OrdreAffectationPAtt> update(@PathVariable(name="id") Long id,@RequestBody OrdreAffectationPAtt o){
		OrdreAffectationPAtt ordre=service.getById(id);
		ordre.setAffectationPartielle(o.getAffectationPartielle());
	
		final OrdreAffectationPAtt updatedOrdre=service.save(ordre);
		return ResponseEntity.ok(updatedOrdre);
	}
	
	@GetMapping(value="/getOrdreAffPAtt/{id}")
	public OrdreAffectationPAtt get(@PathVariable(name="id") Long id) {
		return service.getById(id);
	}
	
	@DeleteMapping(value="/deleteOrdreAffPAtt/{id}")
	public void delete(@PathVariable(name="id") Long id){
		service.delete(id);
	}
	@GetMapping(value="/getOrdreOfAffAtt/{id}")
	public OrdreAffectationPAtt getOrdreOfAffAtt(@PathVariable(name="id")Long idAffect) {
		return repo.getOrdreOfAffAtt(idAffect);
	}
	
}
