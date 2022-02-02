package Cims.PFE.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

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

import Cims.PFE.Dao.GradeRepository;
import Cims.PFE.Dao.PersonnelRepository;
import Cims.PFE.Entities.Grade;
import Cims.PFE.Entities.Personnel;
import Cims.PFE.Service.GradeService;
import Cims.PFE.Service.PersonnelService;
import Cims.PFE.payload.response.MessageResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
@Transactional
public class GradeController {
	
	@Autowired
	private GradeService gradeService;
	
	@Autowired
	private PersonnelRepository personnelRepository;
	
	@Autowired
	private GradeRepository gradeRepository;
	
	@GetMapping(value="/listGrades")
	public List<Grade> listGrades(){
		return gradeService.listAll();
	}
	
	@PostMapping(value="/addGrade")
	public ResponseEntity<MessageResponse> save(@RequestBody Grade p) {
		List<Grade> list=gradeRepository.getGrade(p.getNom_grade_fr(), p.getNom_grade_ar(), p.getCategorie_grade_fr(), p.getCategorie_grade_ar());
		if(list.isEmpty()) {
			gradeService.save(p);
			return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Grade ajoutée"));
		}else return ResponseEntity.badRequest().body(new MessageResponse("Grade existe déja !!!"));
		
	}
	
	@PutMapping(value="/updateGrade/{id}")
	public ResponseEntity<?> updateGrade(@PathVariable("id") Long id, @RequestBody Grade grade) {
	    Grade g=gradeService.getById(id);
		List<Grade> list=gradeRepository.getGrade(grade.getNom_grade_fr(), grade.getNom_grade_ar(), grade.getCategorie_grade_fr(), grade.getCategorie_grade_ar());
		if(list.isEmpty()) {
			 g.setNom_grade_fr(grade.getNom_grade_fr());
			 g.setNom_grade_ar(grade.getNom_grade_ar());
			 g.setCategorie_grade_fr(grade.getCategorie_grade_fr());
			 g.setCategorie_grade_ar(grade.getCategorie_grade_ar());
			    final Grade updatedGrade = gradeService.save(g);
			    return ResponseEntity.ok(new MessageResponse("Grade modifiée"));
		}else return ResponseEntity.badRequest().body(new MessageResponse("Grade existe déja !!!"));
	   
	}
	
	@GetMapping(value="/getGrade/{id}")
	public Grade get(@PathVariable(name="id") Long id) {
		return gradeService.getById(id);
	}
	
	@DeleteMapping(value="/deleteGrade/{id}")
	public ResponseEntity<?> delete(@PathVariable(name="id") Long id){
		List<Personnel> list=personnelRepository.getGrade(id);
		if(list.isEmpty()) {
			if(gradeService.delete(id)) {
				 return ResponseEntity.ok(new MessageResponse("Grade supprimer"));
				}	
		}else return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Vous devez supprimer les personnels avant de supprimer"));
		
	return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Échec lors de suppression"));	
				
		
	}
	
	
}
