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
		case "Direction générale" :{affT.getStructure().setDirection_a("إدارة عامة");
		  switch (affT.getStructure().getDivision()) {
		      case "-" :{affT.getStructure().setDivision_a("-");affT.getStructure().setService_a("-");}
		      break;
		          case "unité de contrôle de gestion":{affT.getStructure().setDivision_a("وحدة التحكم في الإدارة");affT.getStructure().setService_a("-");}
		      break;
		      case "unité audit interne ":{affT.getStructure().setDivision_a("وحدة المراجعة الداخلية");
		                                  switch(affT.getStructure().getService()) {
		                                     case "-":affT.getStructure().setService_a("-");
		                                     break;
		                                     case "service de budget ":affT.getStructure().setService_a("خدمة الميزانية");
		                                     break;}}
		     break;}}
		break;
		case "Département de coordiantion technique et de gestion":{
		affT.getStructure().setDirection_a("قسم التنسيق الفني والإدارة");
		switch (affT.getStructure().getDivision()) {
		    case "-" : {affT.getStructure().setDivision_a("-");affT.getStructure().setService_a("-");}
		    break;
		   case "unité de fomrmation et marketing ":{affT.getStructure().setDivision_a("وحدة التدريب والتسويق");affT.getStructure().setService_a("-");}
		   break;
		   }}
		break;

		case "Direction des affaires administratives et financières":{affT.getStructure().setDirection_a("مديرية الشؤون الادارية والمالية");
		         switch (affT.getStructure().getDivision()) {
		                case "-" : {affT.getStructure().setDivision_a("-");affT.getStructure().setService_a("-");}
		                break;
		                case "division des affaires administratives" :{affT.getStructure().setDivision_a("قسم الشئون الادارية");
		                                                              switch (affT.getStructure().getService()) {
		                                                              case "-":affT.getStructure().setService_a("-");
		                                                              break;
		                                                              case "service des ressources humaines":affT.getStructure().setService_a("قسم الموارد البشرية");
		                                                              break;
		                                                              case "service des moyens communs":affT.getStructure().setService_a("المشترك يعني الخدمة");
		                                                              break;
		                                                              }}
		                break;
		                  case "division des affaires financières" :{affT.getStructure().setDivision_a("قسم الشؤون المالية");}
		                                                             switch (affT.getStructure().getService()) {
		                                                             case "-":affT.getStructure().setService_a("-");
		                                                             break;
		                                                             case "service des finances":affT.getStructure().setService_a("قسم المالية");
		                                                             break;
		                                                             case "service de comptabilité":affT.getStructure().setService_a("خدمة المحاسبة");
		                                                               break;
		                                                             case "service des marchéss ": affT.getStructure().setService_a("قسم الاسواق");
		                                                                 break;
		                                                             }
		                  break;}}
		break;
		case "Direction d\"exploitationn , assistance, de réseaux et sécurité":{affT.getStructure().setDirection_a("توجيه العمليات والمساعدة والشبكات والأمن");
		          switch (affT.getStructure().getDivision()) {
		               case "-" :{affT.getStructure().setDivision_a("-");affT.getStructure().setService_a("-");}
		               break;
		               case "division d\"exploitation et de gestion des sites" :{affT.getStructure().setDivision_a("قسم عمليات وإدارة الموقع");
		                                                                          switch(affT.getStructure().getService()) {
		                                                                          case "-":affT.getStructure().setService_a("-");
		                                                                          break;
		                                                                          case "service d\"exploitation":affT.getStructure().setService_a("قسم التشغيل");
		                                                                          break;
		                                                                          case "service de gestion des sites":affT.getStructure().setService_a("خدمة إدارة الموقع");
		                                                                          break;
		                                                                          }
		               }
		                break;
		               case "division d\"assistance technique et maintenance" :{affT.getStructure().setDivision_a("قسم المساعدة الفنية والصيانة");
		                                                                        switch (affT.getStructure().getService()) {
		                                                                        case "service de maintenance ":  affT.getStructure().setService_a("خدمة الصيانة");
		                                                                        break;
		                                                                        case"-":affT.getStructure().setService_a("-");
		                                                                        break;
		                                                                        }}
		                  break;
		                   case "division de réseaux et sécurité" :{affT.getStructure().setDivision_a("قسم الشبكات والأمن");
		                                                             switch (affT.getStructure().getService()) {
		                                                             case"-":affT.getStructure().setService_a("-");
		                                                             break;
		                                                             case"service de réseaux et sécurité ":affT.getStructure().setService_a("خدمة الشبكة والأمن");
		                                                             break;
		                                                             }}
		               break;}}
		break;
		case "Direction d\"études et développement informatique":{affT.getStructure().setDirection_a("إدارة الدراسات وتطوير تكنولوجيا المعلومات");
		      switch (affT.getStructure().getDivision()) {
		                       case "-" :{affT.getStructure().setDivision_a("-");affT.getStructure().setService_a("-");}
		                       break;
		                case "divison des études " :{affT.getStructure().setDivision_a("قسم الدراسات");
		                                              switch (affT.getStructure().getService()) {
		                                              case"-":affT.getStructure().setService_a("-");
		                                              break;
		                                              case"service des études":affT.getStructure().setService_a("قسم الدراسات");
		                                              break;
		                                              case"service d\"architecture du S.I":affT.getStructure().setService_a("خدمة هندسة نظم المعلومات");
		                                              }
		                }
		                break;
		               case "division de developpement" :{affT.getStructure().setDivision_a("قسم التنمية");
		                                                   switch (affT.getStructure().getService()) {
		                                                   case"-":affT.getStructure().setService_a("-");
		                                                   break;
		                                                   case"service developpement du S.I ":affT.getStructure().setService_a("قسم تطوير نظم المعلومات");
		                                                   break;
		                                                   case "service maintenance et documentation du S.I ":affT.getStructure().setService_a("خدمة صيانة وتوثيق نظم المعلومات ");
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
		 return ResponseEntity.ok(new MessageResponse("Affectation totale ajoutée!"));
	}
		
		 return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Echec d'ajout d'affectation! le personnel a une affectation totale"));
		 }
	
	
	@PutMapping(value="/updateAffectation_T/{id}")
	public ResponseEntity<?> update(@PathVariable(name="id") Long id,@RequestBody AffectationTotale t){
		switch (t.getStructure().getDirection()) {
		case "Direction générale" :{t.getStructure().setDirection_a("إدارة عامة");
		  switch (t.getStructure().getDivision()) {
		      case "-" :{t.getStructure().setDivision_a("-");t.getStructure().setService_a("-");}
		      break;
		          case "unité de contrôle de gestion":{t.getStructure().setDivision_a("وحدة التحكم في الإدارة");t.getStructure().setService_a("-");}
		      break;
		      case "unité audit interne ":{t.getStructure().setDivision_a("وحدة المراجعة الداخلية");
		                                  switch(t.getStructure().getService()) {
		                                     case "-":t.getStructure().setService_a("-");
		                                     break;
		                                     case "service de budget ":t.getStructure().setService_a("خدمة الميزانية");
		                                     break;}}
		     break;}}
		break;
		case "Département de coordiantion technique et de gestion":{
		t.getStructure().setDirection_a("قسم التنسيق الفني والإدارة");
		switch (t.getStructure().getDivision()) {
		    case "-" : {t.getStructure().setDivision_a("-");t.getStructure().setService_a("-");}
		    break;
		   case "unité de fomrmation et marketing ":{t.getStructure().setDivision_a("وحدة التدريب والتسويق");t.getStructure().setService_a("-");}
		   break;
		   }}
		break;

		case "Direction des affaires administratives et financières":{t.getStructure().setDirection_a("مديرية الشؤون الادارية والمالية");
		         switch (t.getStructure().getDivision()) {
		                case "-" : {t.getStructure().setDivision_a("-");t.getStructure().setService_a("-");}
		                break;
		                case "division des affaires administratives" :{t.getStructure().setDivision_a("قسم الشئون الادارية");
		                                                              switch (t.getStructure().getService()) {
		                                                              case "-":t.getStructure().setService_a("-");
		                                                              break;
		                                                              case "service des ressources humaines":t.getStructure().setService_a("قسم الموارد البشرية");
		                                                              break;
		                                                              case "service des moyens communs":t.getStructure().setService_a("المشترك يعني الخدمة");
		                                                              break;
		                                                              }}
		                break;
		                  case "division des affaires financières" :{t.getStructure().setDivision_a("قسم الشؤون المالية");}
		                                                             switch (t.getStructure().getService()) {
		                                                             case "-":t.getStructure().setService_a("-");
		                                                             break;
		                                                             case "service des finances":t.getStructure().setService_a("قسم المالية");
		                                                             break;
		                                                             case "service de comptabilité":t.getStructure().setService_a("خدمة المحاسبة");
		                                                               break;
		                                                             case "service des marchéss ": t.getStructure().setService_a("قسم الاسواق");
		                                                                 break;
		                                                             }
		                  break;}}
		break;
		case "Direction d\"exploitationn , assistance, de réseaux et sécurité":{t.getStructure().setDirection_a("توجيه العمليات والمساعدة والشبكات والأمن");
		          switch (t.getStructure().getDivision()) {
		               case "-" :{t.getStructure().setDivision_a("-");t.getStructure().setService_a("-");}
		               break;
		               case "division d\"exploitation et de gestion des sites" :{t.getStructure().setDivision_a("قسم عمليات وإدارة الموقع");
		                                                                          switch(t.getStructure().getService()) {
		                                                                          case "-":t.getStructure().setService_a("-");
		                                                                          break;
		                                                                          case "service d\"exploitation":t.getStructure().setService_a("قسم التشغيل");
		                                                                          break;
		                                                                          case "service de gestion des sites":t.getStructure().setService_a("خدمة إدارة الموقع");
		                                                                          break;
		                                                                          }
		               }
		                break;
		               case "division d\"assistance technique et maintenance" :{t.getStructure().setDivision_a("قسم المساعدة الفنية والصيانة");
		                                                                        switch (t.getStructure().getService()) {
		                                                                        case "service de maintenance ":  t.getStructure().setService_a("خدمة الصيانة");
		                                                                        break;
		                                                                        case"-":t.getStructure().setService_a("-");
		                                                                        break;
		                                                                        }}
		                  break;
		                   case "division de réseaux et sécurité" :{t.getStructure().setDivision_a("قسم الشبكات والأمن");
		                                                             switch (t.getStructure().getService()) {
		                                                             case"-":t.getStructure().setService_a("-");
		                                                             break;
		                                                             case"service de réseaux et sécurité ":t.getStructure().setService_a("خدمة الشبكة والأمن");
		                                                             break;
		                                                             }}
		               break;}}
		break;
		case "Direction d\"études et développement informatique":{t.getStructure().setDirection_a("إدارة الدراسات وتطوير تكنولوجيا المعلومات");
		      switch (t.getStructure().getDivision()) {
		                       case "-" :{t.getStructure().setDivision_a("-");t.getStructure().setService_a("-");}
		                       break;
		                case "divison des études " :{t.getStructure().setDivision_a("قسم الدراسات");
		                                              switch (t.getStructure().getService()) {
		                                              case"-":t.getStructure().setService_a("-");
		                                              break;
		                                              case"service des études":t.getStructure().setService_a("قسم الدراسات");
		                                              break;
		                                              case"service d\"architecture du S.I":t.getStructure().setService_a("خدمة هندسة نظم المعلومات");
		                                              }
		                }
		                break;
		               case "division de developpement" :{t.getStructure().setDivision_a("قسم التنمية");
		                                                   switch (t.getStructure().getService()) {
		                                                   case"-":t.getStructure().setService_a("-");
		                                                   break;
		                                                   case"service developpement du S.I ":t.getStructure().setService_a("قسم تطوير نظم المعلومات");
		                                                   break;
		                                                   case "service maintenance et documentation du S.I ":t.getStructure().setService_a("خدمة صيانة وتوثيق نظم المعلومات ");
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
		
		return ResponseEntity.ok(new MessageResponse("Affectation  totale modifiée!"));
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

		
		return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Échec lors de suppression"));	
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
