package Cims.PFE.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import Cims.PFE.Dao.AffectationPartielleRepository;
import Cims.PFE.Dao.AffectationTotaleRepository;
import Cims.PFE.Dao.PersonnelRepository;
import Cims.PFE.Dao.RoleRepository;
import Cims.PFE.Dao.CompteRepository;
import Cims.PFE.Entities.AffectationPartielle;
import Cims.PFE.Entities.AffectationTotale;
import Cims.PFE.Entities.ERole;
import Cims.PFE.Entities.OrdreAffectationPers;
import Cims.PFE.Entities.OrdreAffectationTot;
import Cims.PFE.Entities.Personnel;
import Cims.PFE.Entities.Role;
import Cims.PFE.Entities.Site;
import Cims.PFE.Service.AffectationTotaleService;
import Cims.PFE.Service.OrdreAffTotService;
import Cims.PFE.Service.PersonnelService;
import Cims.PFE.Service.SiteService;
import Cims.PFE.payload.response.MessageResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AffectationTotaleController {
	
	@Autowired
	private AffectationTotaleService service;
	
	@Autowired
	CompteRepository compteRepository;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	private PersonnelService personnelService;
	
	@Autowired
	private SiteService SiteService;
	
	@Autowired
	private AffectationTotaleRepository affRepository;
	
	@Autowired
	PersonnelRepository repo;
	
	@Autowired
	PersonnelController persCont;
	
	@Autowired
	OrdreAffTotService serviceOr;
	
	@Autowired
	SiteController siteCont;
	
	@Autowired
	private AffectationPartielleRepository Affrepo;
	
	@GetMapping(value="/listAffectation_T")
	public List<AffectationTotale> listAffectationTotale(){
		return service.listAll();
	}
	
	
	@PostMapping(value="/addAffectation_T")
	public ResponseEntity<?> save(@RequestBody AffectationTotale affT) {
		switch (affT.getStructure().getDirection()) {
		case "Direction g??n??rale" :{affT.getStructure().setDirection_a("?????????? ????????");
		  switch (affT.getStructure().getDivision()) {
		      case "-" :{affT.getStructure().setDivision_a("-");affT.getStructure().setService_a("-");}
		      break;
		          case "unit?? de contr??le de gestion":{affT.getStructure().setDivision_a("???????? ???????????? ???? ??????????????");affT.getStructure().setService_a("-");}
		      break;
		      case "unit?? audit interne ":{affT.getStructure().setDivision_a("???????? ???????????????? ????????????????");
		                                  switch(affT.getStructure().getService()) {
		                                     case "-":affT.getStructure().setService_a("-");
		                                     break;
		                                     case "service de budget ":affT.getStructure().setService_a("???????? ??????????????????");
		                                     break;}}
		     break;}}
		break;
		case "D??partement de coordiantion technique et de gestion":{
		affT.getStructure().setDirection_a("?????? ?????????????? ?????????? ????????????????");
		switch (affT.getStructure().getDivision()) {
		    case "-" : {affT.getStructure().setDivision_a("-");affT.getStructure().setService_a("-");}
		    break;
		   case "unit?? de fomrmation et marketing ":{affT.getStructure().setDivision_a("???????? ?????????????? ????????????????");affT.getStructure().setService_a("-");}
		   break;
		   }}
		break;

		case "Direction des affaires administratives et financi??res":{affT.getStructure().setDirection_a("???????????? ???????????? ???????????????? ????????????????");
		         switch (affT.getStructure().getDivision()) {
		                case "-" : {affT.getStructure().setDivision_a("-");affT.getStructure().setService_a("-");}
		                break;
		                case "division des affaires administratives" :{affT.getStructure().setDivision_a("?????? ???????????? ????????????????");
		                                                              switch (affT.getStructure().getService()) {
		                                                              case "-":affT.getStructure().setService_a("-");
		                                                              break;
		                                                              case "service des ressources humaines":affT.getStructure().setService_a("?????? ?????????????? ??????????????");
		                                                              break;
		                                                              case "service des moyens communs":affT.getStructure().setService_a("?????????????? ???????? ????????????");
		                                                              break;
		                                                              }}
		                break;
		                  case "division des affaires financi??res" :{affT.getStructure().setDivision_a("?????? ???????????? ??????????????");}
		                                                             switch (affT.getStructure().getService()) {
		                                                             case "-":affT.getStructure().setService_a("-");
		                                                             break;
		                                                             case "service des finances":affT.getStructure().setService_a("?????? ??????????????");
		                                                             break;
		                                                             case "service de comptabilit??":affT.getStructure().setService_a("???????? ????????????????");
		                                                               break;
		                                                             case "service des march??ss ": affT.getStructure().setService_a("?????? ??????????????");
		                                                                 break;
		                                                             }
		                  break;}}
		break;
		case "Direction d\"exploitationn , assistance, de r??seaux et s??curit??":{affT.getStructure().setDirection_a("?????????? ???????????????? ?????????????????? ???????????????? ????????????");
		          switch (affT.getStructure().getDivision()) {
		               case "-" :{affT.getStructure().setDivision_a("-");affT.getStructure().setService_a("-");}
		               break;
		               case "division d\"exploitation et de gestion des sites" :{affT.getStructure().setDivision_a("?????? ???????????? ???????????? ????????????");
		                                                                          switch(affT.getStructure().getService()) {
		                                                                          case "-":affT.getStructure().setService_a("-");
		                                                                          break;
		                                                                          case "service d\"exploitation":affT.getStructure().setService_a("?????? ??????????????");
		                                                                          break;
		                                                                          case "service de gestion des sites":affT.getStructure().setService_a("???????? ?????????? ????????????");
		                                                                          break;
		                                                                          }
		               }
		                break;
		               case "division d\"assistance technique et maintenance" :{affT.getStructure().setDivision_a("?????? ???????????????? ???????????? ????????????????");
		                                                                        switch (affT.getStructure().getService()) {
		                                                                        case "service de maintenance ":  affT.getStructure().setService_a("???????? ??????????????");
		                                                                        break;
		                                                                        case"-":affT.getStructure().setService_a("-");
		                                                                        break;
		                                                                        }}
		                  break;
		                   case "division de r??seaux et s??curit??" :{affT.getStructure().setDivision_a("?????? ?????????????? ????????????");
		                                                             switch (affT.getStructure().getService()) {
		                                                             case"-":affT.getStructure().setService_a("-");
		                                                             break;
		                                                             case"service de r??seaux et s??curit?? ":affT.getStructure().setService_a("???????? ???????????? ????????????");
		                                                             break;
		                                                             }}
		               break;}}
		break;
		case "Direction d\"??tudes et d??veloppement informatique":{affT.getStructure().setDirection_a("?????????? ???????????????? ???????????? ?????????????????? ??????????????????");
		      switch (affT.getStructure().getDivision()) {
		                       case "-" :{affT.getStructure().setDivision_a("-");affT.getStructure().setService_a("-");}
		                       break;
		                case "divison des ??tudes " :{affT.getStructure().setDivision_a("?????? ????????????????");
		                                              switch (affT.getStructure().getService()) {
		                                              case"-":affT.getStructure().setService_a("-");
		                                              break;
		                                              case"service des ??tudes":affT.getStructure().setService_a("?????? ????????????????");
		                                              break;
		                                              case"service d\"architecture du S.I":affT.getStructure().setService_a("???????? ?????????? ?????? ??????????????????");
		                                              }
		                }
		                break;
		               case "division de developpement" :{affT.getStructure().setDivision_a("?????? ??????????????");
		                                                   switch (affT.getStructure().getService()) {
		                                                   case"-":affT.getStructure().setService_a("-");
		                                                   break;
		                                                   case"service developpement du S.I ":affT.getStructure().setService_a("?????? ?????????? ?????? ??????????????????");
		                                                   break;
		                                                   case "service maintenance et documentation du S.I ":affT.getStructure().setService_a("???????? ?????????? ???????????? ?????? ?????????????????? ");
		                                                   break;
		                                                   }
		               }
		               break;}}
		break;
		}
		if(affRepository.existsByPersonnel(affT.getPersonnel())==false) {
			
		Set<Role> roles = new HashSet<>();
		Role CorrespondantRole = roleRepository.findByName(ERole.ROLE_CORRESPONDANT)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		Role userRole = roleRepository.findByName(ERole.ROLE_PERSONNEL)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(CorrespondantRole);
		roles.add(userRole);
		
	 Long id=affT.getPersonnel().getId_personnel();
	 if(service.save(affT)!= null) {
			 Personnel pers=personnelService.getById( id);
			 pers.getUser().setRoles(roles);
			 
			 pers.setSite(affT.getSite());
			 pers.setStructure(affT.getStructure());

			 String date=LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

			 pers.setDateAffectation(date);
			 
			 final Personnel updatedPersonnel=personnelService.save(pers);
			 			 
			// pers.setSite(affT.getSite());
			//final Personnel updatedPersonnel=personnelService.save(pers);			 
		 }
	 	 OrdreAffectationTot o=new OrdreAffectationTot();
		 o.setAffectationTotale(affT);
		 OrdreAffectationTot ord = serviceOr.save(o);
		 return ResponseEntity.ok(new MessageResponse("Affectation totale ajout??e!"));
	}
		
		 return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Echec d'ajout d'affectation! le personnel a une affectation totale"));
		 }
	
	
	@PutMapping(value="/updateAffectation_T/{id}")
	public ResponseEntity<?> update(@PathVariable(name="id") Long id,@RequestBody AffectationTotale t){
		switch (t.getStructure().getDirection()) {
		case "Direction g??n??rale" :{t.getStructure().setDirection_a("?????????? ????????");
		  switch (t.getStructure().getDivision()) {
		      case "-" :{t.getStructure().setDivision_a("-");t.getStructure().setService_a("-");}
		      break;
		          case "unit?? de contr??le de gestion":{t.getStructure().setDivision_a("???????? ???????????? ???? ??????????????");t.getStructure().setService_a("-");}
		      break;
		      case "unit?? audit interne ":{t.getStructure().setDivision_a("???????? ???????????????? ????????????????");
		                                  switch(t.getStructure().getService()) {
		                                     case "-":t.getStructure().setService_a("-");
		                                     break;
		                                     case "service de budget ":t.getStructure().setService_a("???????? ??????????????????");
		                                     break;}}
		     break;}}
		break;
		case "D??partement de coordiantion technique et de gestion":{
		t.getStructure().setDirection_a("?????? ?????????????? ?????????? ????????????????");
		switch (t.getStructure().getDivision()) {
		    case "-" : {t.getStructure().setDivision_a("-");t.getStructure().setService_a("-");}
		    break;
		   case "unit?? de fomrmation et marketing ":{t.getStructure().setDivision_a("???????? ?????????????? ????????????????");t.getStructure().setService_a("-");}
		   break;
		   }}
		break;

		case "Direction des affaires administratives et financi??res":{t.getStructure().setDirection_a("???????????? ???????????? ???????????????? ????????????????");
		         switch (t.getStructure().getDivision()) {
		                case "-" : {t.getStructure().setDivision_a("-");t.getStructure().setService_a("-");}
		                break;
		                case "division des affaires administratives" :{t.getStructure().setDivision_a("?????? ???????????? ????????????????");
		                                                              switch (t.getStructure().getService()) {
		                                                              case "-":t.getStructure().setService_a("-");
		                                                              break;
		                                                              case "service des ressources humaines":t.getStructure().setService_a("?????? ?????????????? ??????????????");
		                                                              break;
		                                                              case "service des moyens communs":t.getStructure().setService_a("?????????????? ???????? ????????????");
		                                                              break;
		                                                              }}
		                break;
		                  case "division des affaires financi??res" :{t.getStructure().setDivision_a("?????? ???????????? ??????????????");}
		                                                             switch (t.getStructure().getService()) {
		                                                             case "-":t.getStructure().setService_a("-");
		                                                             break;
		                                                             case "service des finances":t.getStructure().setService_a("?????? ??????????????");
		                                                             break;
		                                                             case "service de comptabilit??":t.getStructure().setService_a("???????? ????????????????");
		                                                               break;
		                                                             case "service des march??ss ": t.getStructure().setService_a("?????? ??????????????");
		                                                                 break;
		                                                             }
		                  break;}}
		break;
		case "Direction d\"exploitationn , assistance, de r??seaux et s??curit??":{t.getStructure().setDirection_a("?????????? ???????????????? ?????????????????? ???????????????? ????????????");
		          switch (t.getStructure().getDivision()) {
		               case "-" :{t.getStructure().setDivision_a("-");t.getStructure().setService_a("-");}
		               break;
		               case "division d\"exploitation et de gestion des sites" :{t.getStructure().setDivision_a("?????? ???????????? ???????????? ????????????");
		                                                                          switch(t.getStructure().getService()) {
		                                                                          case "-":t.getStructure().setService_a("-");
		                                                                          break;
		                                                                          case "service d\"exploitation":t.getStructure().setService_a("?????? ??????????????");
		                                                                          break;
		                                                                          case "service de gestion des sites":t.getStructure().setService_a("???????? ?????????? ????????????");
		                                                                          break;
		                                                                          }
		               }
		                break;
		               case "division d\"assistance technique et maintenance" :{t.getStructure().setDivision_a("?????? ???????????????? ???????????? ????????????????");
		                                                                        switch (t.getStructure().getService()) {
		                                                                        case "service de maintenance ":  t.getStructure().setService_a("???????? ??????????????");
		                                                                        break;
		                                                                        case"-":t.getStructure().setService_a("-");
		                                                                        break;
		                                                                        }}
		                  break;
		                   case "division de r??seaux et s??curit??" :{t.getStructure().setDivision_a("?????? ?????????????? ????????????");
		                                                             switch (t.getStructure().getService()) {
		                                                             case"-":t.getStructure().setService_a("-");
		                                                             break;
		                                                             case"service de r??seaux et s??curit?? ":t.getStructure().setService_a("???????? ???????????? ????????????");
		                                                             break;
		                                                             }}
		               break;}}
		break;
		case "Direction d\"??tudes et d??veloppement informatique":{t.getStructure().setDirection_a("?????????? ???????????????? ???????????? ?????????????????? ??????????????????");
		      switch (t.getStructure().getDivision()) {
		                       case "-" :{t.getStructure().setDivision_a("-");t.getStructure().setService_a("-");}
		                       break;
		                case "divison des ??tudes " :{t.getStructure().setDivision_a("?????? ????????????????");
		                                              switch (t.getStructure().getService()) {
		                                              case"-":t.getStructure().setService_a("-");
		                                              break;
		                                              case"service des ??tudes":t.getStructure().setService_a("?????? ????????????????");
		                                              break;
		                                              case"service d\"architecture du S.I":t.getStructure().setService_a("???????? ?????????? ?????? ??????????????????");
		                                              }
		                }
		                break;
		               case "division de developpement" :{t.getStructure().setDivision_a("?????? ??????????????");
		                                                   switch (t.getStructure().getService()) {
		                                                   case"-":t.getStructure().setService_a("-");
		                                                   break;
		                                                   case"service developpement du S.I ":t.getStructure().setService_a("?????? ?????????? ?????? ??????????????????");
		                                                   break;
		                                                   case "service maintenance et documentation du S.I ":t.getStructure().setService_a("???????? ?????????? ???????????? ?????? ?????????????????? ");
		                                                   break;
		                                                   }
		               }
		               break;}}
		break;
		}
		List<AffectationPartielle> list=Affrepo.getSiteParPersonnel(t.getSite().getIdSite(),t.getPersonnel().getId_personnel(),t.getStructure().getId_struct());
		AffectationTotale affT=service.getById(id);
		
		affT.setPersonnel(t.getPersonnel());
		
		if(list.isEmpty()) {
			affT.setSite(t.getSite());
			
		}else return ResponseEntity.badRequest().body(new MessageResponse(t.getPersonnel().getNom()+" a une affectation partielle dans le site  "+t.getSite().getNom_etablissement_fr()));
		
		final AffectationTotale updatedAffect=service.save(affT);
		
		return ResponseEntity.ok(new MessageResponse("Affectation  totale modifi??e!"));
	}
	
	
	@GetMapping(value="/getAffectation_T/{id}")
	public AffectationTotale get(@PathVariable(name="id") Long id) {
		return service.getById(id);
	}
	
	
	@DeleteMapping(value="/deleteAffectation_T/{id}")
	public ResponseEntity<?> delete(@PathVariable(name="id") Long id){
		List<AffectationPartielle> list=Affrepo.getAffectPartielle(id);
		if(list.isEmpty()) {
			if(service.delete(id)==true) {
			return ResponseEntity.ok(new MessageResponse("Affectation totale supprimer!"));
			}
		}		else return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Vous devez supprimer les affectations partielles de personnel avant de supprimer"));

		
		return ResponseEntity.badRequest().body(new MessageResponse("Erreur: ??chec lors de suppression"));	
	}
	
	
	@GetMapping(value="/list_T_Personnel")
	public List<Personnel> listAffecPersonnel(){
		List<Personnel> list=new ArrayList<Personnel>();
		for(int i=0;i<affRepository.count();i++) {
			Long id=affRepository.getId().get(i);
		Personnel p=personnelService.getById(id);
		if(list.contains(p)==false) {
			list.add(p);
		}
		
		}
	
		return list;
	}
	
}
