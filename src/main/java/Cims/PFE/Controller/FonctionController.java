package Cims.PFE.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Cims.PFE.Dao.FonctionRepository;
import Cims.PFE.Dao.PersonnelRepository;
import Cims.PFE.Entities.Fonction;
import Cims.PFE.Entities.Personnel;
import Cims.PFE.Service.FonctionService;
import Cims.PFE.Service.PersonnelService;
import Cims.PFE.payload.response.MessageResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class FonctionController {
	
	@Autowired
	private FonctionService fonctionService;
	
	@Autowired
	private PersonnelRepository personnelRepository;
	
	@Autowired
	private FonctionRepository fonctionRepository;
	
	@GetMapping(value="/listFonctions")
	public List<Fonction> listFonctions(){
		return fonctionService.listAll();
	}
	
	@PostMapping(value="/addFonction")
	public ResponseEntity<MessageResponse> save(@RequestBody Fonction p) {
		List<Fonction> list=fonctionRepository.getFonction(p.getNom_fonction(), p.getType_fonction());
		if(list.isEmpty()) {
			fonctionService.save(p);
			return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("fonction ajoutée"));
		}else return ResponseEntity.badRequest().body(new MessageResponse("fonction existe déja !!!"));
		
	}
	
	@PutMapping(value="/updateFonction/{id}")
	public ResponseEntity<?> updatefonction(@PathVariable("id") Long id, @RequestBody Fonction fonction) {
		Fonction g=fonctionService.getById(id);
		List<Fonction> list=fonctionRepository.getFonction(fonction.getNom_fonction(), fonction.getType_fonction());
		if(list.isEmpty()) {
			 g.setNom_fonction(fonction.getNom_fonction());
			 g.setType_fonction(fonction.getType_fonction());
			    final Fonction updatedFonction = fonctionService.save(g);
			    return ResponseEntity.ok(new MessageResponse("fonction modifiée"));
		}else return ResponseEntity.badRequest().body(new MessageResponse("fonction existe déja !!!"));
	   
	}
	
	@GetMapping(value="/getFonction/{id}")
	public Fonction get(@PathVariable(name="id") Long id) {
		return fonctionService.getById(id);
	}
	
	@DeleteMapping(value="/deleteFonction/{id}")
	public ResponseEntity<?> delete(@PathVariable(name="id") Long id){
		List<Personnel> list=personnelRepository.getFonction(id);
		if(list.isEmpty()) {
			if(fonctionService.delete(id)) {
				 return ResponseEntity.ok(new MessageResponse("fonction suppriméé"));
				}	
		}else return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Vous devez supprimer les personnels avant de supprimer"));
		
	return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Échec lors de suppression"));	
				
		
	}
	
	
}
