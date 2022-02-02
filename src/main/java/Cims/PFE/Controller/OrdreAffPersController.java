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
//import Cims.PFE.Entities.OrdreAffectationP;
import Cims.PFE.Entities.OrdreAffectationPers;
import Cims.PFE.Service.OrdreAffPersService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class OrdreAffPersController {
	@Autowired
	private OrdreAffPersService service;
	@Autowired
	private OrdreAffPersRepository repo;
	
	@GetMapping(value="/listOrdresAffPers")
	public List<OrdreAffectationPers> listOrdre(){
		return service.listAll();
	}
	
	@PostMapping(value="/addOrdreAffPers")
	public OrdreAffectationPers save(@RequestBody OrdreAffectationPers o) {
		return service.save(o);
	}
	
	@PutMapping(value="/updateOrdreAffPers/{id}")
	public  ResponseEntity<OrdreAffectationPers> update(@PathVariable(name="id") Long id,@RequestBody OrdreAffectationPers o){
		OrdreAffectationPers ordre=service.getById(id);
		ordre.setPersonnel(o.getPersonnel());
	
		final OrdreAffectationPers updatedOrdre=service.save(ordre);
		return ResponseEntity.ok(updatedOrdre);
	}
	
	@GetMapping(value="/getOrdreAffPers/{id}")
	public OrdreAffectationPers get(@PathVariable(name="id") Long id) {
		return service.getById(id);
	}
	
	@DeleteMapping(value="/deleteOrdreAffPers/{id}")
	public void delete(@PathVariable(name="id") Long id){
		service.delete(id);
	}
	@GetMapping(value="/getOrdreOfAffPers/{id}")
	public OrdreAffectationPers getOrdreOfAffPers(@PathVariable(name="id")Long id_personnel) {
		return repo.getOrdreOfAffPers(id_personnel);
	}

}
