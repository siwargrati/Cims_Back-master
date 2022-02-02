package Cims.PFE.Controller;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
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

import Cims.PFE.Dao.AffectationPartielleRepository;
import Cims.PFE.Dao.AffectationTotaleRepository;
import Cims.PFE.Dao.SiteRepository;
import Cims.PFE.Entities.AffectationPartielle;
import Cims.PFE.Entities.Gouvernorat;
import Cims.PFE.Entities.Site;
import Cims.PFE.Service.SiteService;
import Cims.PFE.payload.response.MessageResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
@RestController
public class SiteController {
	
	@Autowired
	SiteRepository repo;
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private AffectationTotaleRepository affRepository;
	
	@Autowired
	private AffectationPartielleRepository Affrepo;
	
	

	
	@GetMapping(value="/listSites")
	public List<Site> listSites(){
		return siteService.listAll();
	}
	
	@PostMapping(value="/addSite")
	public ResponseEntity<?> save(@RequestBody Site s) {
		List<Site> list=repo.getSite(s.getGouvernorat().getid_gouv(), s.getNom_etablissement_fr() ,s.getNom_etablissement_ar(), s.getNature_etablissement_fr(), s.getNature_etablissement_ar(), s.getQualite_direction_fr(),s.getQualite_direction_ar() );
	if(list.isEmpty()) {
		 siteService.save(s);
			return ResponseEntity.ok(new MessageResponse("Site ajouter"));
	}else return ResponseEntity.badRequest().body(new MessageResponse(": Site existe déja !!!"));
		
	}
	
	@PutMapping(value="/updateSite/{id}")
	public ResponseEntity<?> update(@PathVariable(name="id") Long id,@RequestBody Site s){
		List<Site> list=repo.getSite(s.getGouvernorat().getid_gouv(), s.getNom_etablissement_fr() ,s.getNom_etablissement_ar(), s.getNature_etablissement_fr(), s.getNature_etablissement_ar(), s.getQualite_direction_fr(),s.getQualite_direction_ar());
		Site site=siteService.getById(id);
		if(list.isEmpty()) {
			site.setNom_etablissement_fr(s.getNom_etablissement_fr());
			site.setNom_etablissement_ar(s.getNom_etablissement_ar());
			site.setNature_etablissement_fr(s.getNature_etablissement_fr());
			site.setNature_etablissement_ar(s.getNature_etablissement_ar());
			site.setQualite_direction_fr(s.getQualite_direction_fr());
			site.setQualite_direction_ar(s.getQualite_direction_ar());

			site.setGouvernorat(s.getGouvernorat());
			final Site updatedSite=siteService.save(site);
			return ResponseEntity.ok(new MessageResponse("Site modifier"));
		}else return ResponseEntity.badRequest().body(new MessageResponse(": Site existe déja !!!"));
		
	}
	
	@GetMapping(value="/getSite/{id}")
	public Site get(@PathVariable(name="id") Long id) {
		return siteService.getById(id);
	}
	
	@DeleteMapping(value="/deleteSite/{id}")
	public ResponseEntity<?> delete(@PathVariable(name="id") Long id){
		List<AffectationPartielle> list=Affrepo.getSite(id);
		if(repo.existsAffTotale(id).isEmpty() && list.isEmpty()) {
			if(siteService.delete(id)) {
			return ResponseEntity.ok(new MessageResponse(" Site supprimé"));
			}else  return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Vous devez supprimer les affectations avant de supprimer"));
		}
		else {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Erreur:Impossible de supprimer"));	
		}
		
	}
	
	@GetMapping(value="/listSiteG/{id}")
	public List<Site> siteG(@PathVariable(name="id")Long id_gouv) {
		return repo.getss(id_gouv);
	}
	
	//id gouvernorat + id personnel
	@GetMapping(value="/listSiteGP/{id}/{id_pers}")
	public List<Site> siteGP(@PathVariable(name="id")Long id_gouv,@PathVariable(name="id_pers")Long id_pers) {
		List<Site> liste=repo.getss(id_gouv);
		Long id_site=affRepository.getSiteOfAffT(id_pers);
		Site s=repo.getOne(id_site);
		liste.remove(s);
		return liste;
	}
	
}