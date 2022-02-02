package Cims.PFE.Controller;

import java.util.List;

import javax.transaction.Transactional;

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

import Cims.PFE.Dao.OrdreMissionRepository;
import Cims.PFE.Entities.OrdreMission;
import Cims.PFE.Service.OrdreMissionService;
import Cims.PFE.payload.response.MessageResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
@Transactional
public class OrdreMissionController {
	@Autowired
	private OrdreMissionService service;
	
	@Autowired
	private OrdreMissionRepository repo;
	
	@GetMapping(value="/listOrdresM")
	public List<OrdreMission> listOrdre(){
		return service.listAll();
	}
	
	@PostMapping(value="/addOrdreM")
	public OrdreMission save(@RequestBody OrdreMission o) {
		return service.save(o);
	}
	
	@PutMapping(value="/updateOrdreM/{id}")
	public  ResponseEntity<?> update(@PathVariable(name="id") Long id,@RequestBody OrdreMission o){
		OrdreMission ordre=service.getById(id);
		ordre.setTransport(o.getTransport());
		ordre.setMoyenDeTransport(o.getMoyenDeTransport());
		ordre.setDeplacement(o.getDeplacement());
		ordre.setHebergement(o.getHebergement());
		//ordre.setProprietaire(o.getProprietaire());
		//ordre.setNbkm(o.getNbkm());
		final OrdreMission updatedOrdre=service.save(ordre);
		return ResponseEntity.ok(new MessageResponse("Ordre modifiée!"));
	}
	
	@GetMapping(value="/getOrdreM/{id}")
	public OrdreMission get(@PathVariable(name="id") Long id) {
		return service.getById(id);
	}
	
	@DeleteMapping(value="/deleteOrdreM/{id}")
	public void delete(@PathVariable(name="id") Long id){
		service.delete(id);
	}
	@GetMapping(value="/getOrdreOfMiss/{id}")
	public OrdreMission getOrdreOfMiss(@PathVariable(name="id")Long idMission) {
		return repo.getOrdreM_Of_Miss(idMission);
	}
}
