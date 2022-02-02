package Cims.PFE.Controller;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Cims.PFE.Dao.PersonnelRepository;
import Cims.PFE.Dao.RoleRepository;
import Cims.PFE.Dao.AffectationPartielleRepository;
import Cims.PFE.Dao.AffectationTotaleRepository;
import Cims.PFE.Dao.CompteRepository;
import Cims.PFE.Dao.OrdreAffPRepository;
import Cims.PFE.Dao.OrdreAffPersRepository;
import Cims.PFE.Entities.ERole;
import Cims.PFE.Entities.OrdreAffectationP;
import Cims.PFE.Entities.OrdreAffectationPers;
import Cims.PFE.Entities.OrdreAffectationTot;
import Cims.PFE.Entities.Personnel;
import Cims.PFE.Entities.Role;
import Cims.PFE.Entities.Site;
import Cims.PFE.Entities.AffectationPartielle;
import Cims.PFE.Entities.AffectationTotale;
import Cims.PFE.Entities.Compte;
import Cims.PFE.Service.AffectationTotaleService;
import Cims.PFE.Service.OrdreAffPersService;
import Cims.PFE.Service.OrdreAffTotService;
import Cims.PFE.Service.PersonnelService;
import Cims.PFE.payload.request.SignupRequest;
import Cims.PFE.payload.response.MessageResponse;
import Cims.PFE.security.jwt.JwtUtils;
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
@RestController
public class PersonnelController {
	@Autowired
	private PersonnelService personnelService;
	
	@Autowired
	private PersonnelRepository personnelRepository;
	@Autowired
	CompteRepository compteRepository;
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	private AffectationPartielleRepository Affrepo;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	private OrdreAffPersRepository serviceOrdre;
	
	@Autowired
	OrdreAffTotService serviceOrt;
	
	@Autowired
	private AffectationTotaleRepository serviceAffTo;
	@Autowired
	private OrdreAffPersService serviceOr;
	
	@Autowired
	private AffectationTotaleService serviceAff;
	// liste des personnels sans affectation totale
	@GetMapping(value="/listPersonnelsSansAffecT")
	public List<Personnel> listPersonnelSAffect(){
		List<Personnel> liste=personnelService.listAll();
		List<Personnel> l =new ArrayList<Personnel>();
		for(int i=0;i<liste.size();i++) {
			if(liste.get(i).getAffectationt().isEmpty())  {
				l.add(liste.get(i));
			}
		}
		return l;
	}

	
	@GetMapping(value="/listPersonnels")
	public List<Personnel> listPersonnel(){
		return personnelService.listAll();
	}
	@PutMapping(value="/updatePersonnel/{id}")
	public ResponseEntity<?> update(@PathVariable(name="id") Long id,@RequestBody Personnel p){
		switch (p.getStructure().getDirection()) {
		
		case "Aucune" :{p.getStructure().setDirection_a("Aucune");
		  switch (p.getStructure().getDivision()) {
		      case "Aucune" :{p.getStructure().setDivision_a("Aucune");p.getStructure().setService_a("Aucune");}
		      break;}}
		                                         
		break;
		
		case "Direction générale" :{p.getStructure().setDirection_a("إدارة عامة");
		  switch (p.getStructure().getDivision()) {
		      case "-" :{p.getStructure().setDivision_a("-");p.getStructure().setService_a("-");}
		      break;
		          case "unité de contrôle de gestion":{p.getStructure().setDivision_a("وحدة التحكم في الإدارة");p.getStructure().setService_a("-");}
		      break;
		      case "unité audit interne ":{p.getStructure().setDivision_a("وحدة المراجعة الداخلية");
		                                  switch(p.getStructure().getService()) {
		                                     case "-":p.getStructure().setService_a("-");
		                                     break;
		                                     case "service de budget ":p.getStructure().setService_a("خدمة الميزانية");
		                                     break;}}
		     break;}}
		break;
		case "Département de coordiantion technique et de gestion":{
		p.getStructure().setDirection_a("قسم التنسيق الفني والإدارة");
		switch (p.getStructure().getDivision()) {
		    case "-" : {p.getStructure().setDivision_a("-");p.getStructure().setService_a("-");}
		    break;
		   case "unité de fomrmation et marketing ":{p.getStructure().setDivision_a("وحدة التدريب والتسويق");p.getStructure().setService_a("-");}
		   break;
		   }}
		break;

		case "Direction des affaires administratives et financières":{p.getStructure().setDirection_a("مديرية الشؤون الادارية والمالية");
		         switch (p.getStructure().getDivision()) {
		                case "-" : {p.getStructure().setDivision_a("-");p.getStructure().setService_a("-");}
		                break;
		                case "division des affaires administratives" :{p.getStructure().setDivision_a("قسم الشئون الادارية");
		                                                              switch (p.getStructure().getService()) {
		                                                              case "-":p.getStructure().setService_a("-");
		                                                              break;
		                                                              case "service des ressources humaines":p.getStructure().setService_a("قسم الموارد البشرية");
		                                                              break;
		                                                              case "service des moyens communs":p.getStructure().setService_a("المشترك يعني الخدمة");
		                                                              break;
		                                                              }}
		                break;
		                  case "division des affaires financières" :{p.getStructure().setDivision_a("قسم الشؤون المالية");}
		                                                             switch (p.getStructure().getService()) {
		                                                             case "-":p.getStructure().setService_a("-");
		                                                             break;
		                                                             case "service des finances":p.getStructure().setService_a("قسم المالية");
		                                                             break;
		                                                             case "service de comptabilité":p.getStructure().setService_a("خدمة المحاسبة");
		                                                               break;
		                                                             case "service des marchéss ": p.getStructure().setService_a("قسم الاسواق");
		                                                                 break;
		                                                             }
		                  break;}}
		break;
		case "Direction d\"exploitationn , assistance, de réseaux et sécurité":{p.getStructure().setDirection_a("توجيه العمليات والمساعدة والشبكات والأمن");
		          switch (p.getStructure().getDivision()) {
		               case "-" :{p.getStructure().setDivision_a("-");p.getStructure().setService_a("-");}
		               break;
		               case "division d\"exploitation et de gestion des sites" :{p.getStructure().setDivision_a("قسم عمليات وإدارة الموقع");
		                                                                          switch(p.getStructure().getService()) {
		                                                                          case "-":p.getStructure().setService_a("-");
		                                                                          break;
		                                                                          case "service d\"exploitation":p.getStructure().setService_a("قسم التشغيل");
		                                                                          break;
		                                                                          case "service de gestion des sites":p.getStructure().setService_a("خدمة إدارة الموقع");
		                                                                          break;
		                                                                          }
		               }
		                break;
		               case "division d\"assistance technique et maintenance" :{p.getStructure().setDivision_a("قسم المساعدة الفنية والصيانة");
		                                                                        switch (p.getStructure().getService()) {
		                                                                        case "service de maintenance ":  p.getStructure().setService_a("خدمة الصيانة");
		                                                                        break;
		                                                                        case"-":p.getStructure().setService_a("-");
		                                                                        break;
		                                                                        }}
		                  break;
		                   case "division de réseaux et sécurité" :{p.getStructure().setDivision_a("قسم الشبكات والأمن");
		                                                             switch (p.getStructure().getService()) {
		                                                             case"-":p.getStructure().setService_a("-");
		                                                             break;
		                                                             case"service de réseaux et sécurité ":p.getStructure().setService_a("خدمة الشبكة والأمن");
		                                                             break;
		                                                             }}
		               break;}}
		break;
		case "Direction d\"études et développement informatique":{p.getStructure().setDirection_a("إدارة الدراسات وتطوير تكنولوجيا المعلومات");
		      switch (p.getStructure().getDivision()) {
		                       case "-" :{p.getStructure().setDivision_a("-");p.getStructure().setService_a("-");}
		                       break;
		                case "divison des études " :{p.getStructure().setDivision_a("قسم الدراسات");
		                                              switch (p.getStructure().getService()) {
		                                              case"-":p.getStructure().setService_a("-");
		                                              break;
		                                              case"service des études":p.getStructure().setService_a("قسم الدراسات");
		                                              break;
		                                              case"service d\"architecture du S.I":p.getStructure().setService_a("خدمة هندسة نظم المعلومات");
		                                              }
		                }
		                break;
		               case "division de developpement" :{p.getStructure().setDivision_a("قسم التنمية");
		                                                   switch (p.getStructure().getService()) {
		                                                   case"-":p.getStructure().setService_a("-");
		                                                   break;
		                                                   case"service developpement du S.I ":p.getStructure().setService_a("قسم تطوير نظم المعلومات");
		                                                   break;
		                                                   case "service maintenance et documentation du S.I ":p.getStructure().setService_a("خدمة صيانة وتوثيق نظم المعلومات ");
		                                                   break;
		                                                   }
		               }
		               break;}}
		break;
		}
		Personnel pers=personnelService.getById(id);
		pers.setNom(p.getNom());
		pers.setPrenom(p.getPrenom());
		pers.setNom_AR(p.getNom_AR());
		pers.setPrenom_AR(p.getPrenom_AR());
		pers.setSexe(p.getSexe());
		pers.setAdresse(p.getAdresse());
		pers.setDateNaissance(p.getDateNaissance());
		pers.setTelephone(p.getTelephone());
		pers.setDate_recrutement(p.getDate_recrutement());
		pers.setDate_promotion(p.getDate_promotion());
		pers.setDateEchelle(p.getDateEchelle());
		pers.setEchellon(p.getEchellon());
		pers.setEchelle(p.getEchelle());
		pers.setDateEchellon(p.getDateEchellon());
		pers.setCin(p.getCin());
		pers.setMatricule_CNSS(p.getMatricule_CNSS());
		pers.setMatricule_CNRPS(p.getMatricule_CNRPS());
		pers.setPoste_occupe(p.getPoste_occupe());
		pers.setSolde_repos(p.getSolde_repos());
		pers.setSoldeExceptionnel(p.getSoldeExceptionnel());
		pers.setSoldeinitial(p.getSoldeinitial());
		pers.setGrade(p.getGrade());
		pers.setStructure(p.getStructure());
		pers.setEmail(p.getEmail());
		pers.setDateAffectation(p.getDateAffectation());
		pers.setSite(p.getSite());
		final Personnel updatedPersonnel=personnelService.save(pers);
		
		
		return ResponseEntity.ok(new MessageResponse("personnel Modifié"));
	}
	@GetMapping(value="/getPersonnel/{id}")
	public Personnel get(@PathVariable(name="id") Long id) {
		return personnelService.getById(id);
	}
	@DeleteMapping(value="/deletePersonnel/{id}")
	public ResponseEntity<?> delete(@PathVariable(name="id") Long id){
		List<AffectationPartielle> list=Affrepo.getAffectPartielle(id);
		if(list.isEmpty()) {
			if(personnelService.delete(id)==true) {
				return ResponseEntity.ok(new MessageResponse("Personnel supprimer"));
			}	else return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Échec lors de suppression"));	
		}
		else return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Vous devez supprimer les affectations de personnel avant de supprimer"));
		//return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Échec lors de suppression"));	
	}

	
	@PostMapping("/addPersonnel")
	public ResponseEntity<?> registerUser(@RequestBody Personnel p ,SignupRequest signUpRequest) {
		switch (p.getStructure().getDirection()) {
		
		case "Aucune" :{p.getStructure().setDirection_a("Aucune");
		  switch (p.getStructure().getDivision()) {
		      case "Aucune" :{p.getStructure().setDivision_a("Aucune");p.getStructure().setService_a("Aucune");}
		      break;}}
		                                         
		break;
		
		
		case "Direction générale" :{p.getStructure().setDirection_a("إدارة عامة");
		  switch (p.getStructure().getDivision()) {
		      case "-" :{p.getStructure().setDivision_a("-");p.getStructure().setService_a("-");}
		      break;
		          case "unité de contrôle de gestion":{p.getStructure().setDivision_a("وحدة التحكم في الإدارة");p.getStructure().setService_a("-");}
		      break;
		      case "unité audit interne ":{p.getStructure().setDivision_a("وحدة المراجعة الداخلية");
		                                  switch(p.getStructure().getService()) {
		                                     case "-":p.getStructure().setService_a("-");
		                                     break;
		                                     case "service de budget ":p.getStructure().setService_a("خدمة الميزانية");
		                                     break;}}
		     break;}}
		break;
		case "Département de coordiantion technique et de gestion":{
		p.getStructure().setDirection_a("قسم التنسيق الفني والإدارة");
		switch (p.getStructure().getDivision()) {
		    case "-" : {p.getStructure().setDivision_a("-");p.getStructure().setService_a("-");}
		    break;
		   case "unité de fomrmation et marketing ":{p.getStructure().setDivision_a("وحدة التدريب والتسويق");p.getStructure().setService_a("-");}
		   break;
		   }}
		break;

		case "Direction des affaires administratives et financières":{p.getStructure().setDirection_a("مديرية الشؤون الادارية والمالية");
		         switch (p.getStructure().getDivision()) {
		                case "-" : {p.getStructure().setDivision_a("-");p.getStructure().setService_a("-");}
		                break;
		                case "division des affaires administratives" :{p.getStructure().setDivision_a("قسم الشئون الادارية");
		                                                              switch (p.getStructure().getService()) {
		                                                              case "-":p.getStructure().setService_a("-");
		                                                              break;
		                                                              case "service des ressources humaines":p.getStructure().setService_a("قسم الموارد البشرية");
		                                                              break;
		                                                              case "service des moyens communs":p.getStructure().setService_a("المشترك يعني الخدمة");
		                                                              break;
		                                                              }}
		                break;
		                  case "division des affaires financières" :{p.getStructure().setDivision_a("قسم الشؤون المالية");}
		                                                             switch (p.getStructure().getService()) {
		                                                             case "-":p.getStructure().setService_a("-");
		                                                             break;
		                                                             case "service des finances":p.getStructure().setService_a("قسم المالية");
		                                                             break;
		                                                             case "service de comptabilité":p.getStructure().setService_a("خدمة المحاسبة");
		                                                               break;
		                                                             case "service des marchéss ": p.getStructure().setService_a("قسم الاسواق");
		                                                                 break;
		                                                             }
		                  break;}}
		break;
		case "Direction d\"exploitationn , assistance, de réseaux et sécurité":{p.getStructure().setDirection_a("توجيه العمليات والمساعدة والشبكات والأمن");
		          switch (p.getStructure().getDivision()) {
		               case "-" :{p.getStructure().setDivision_a("-");p.getStructure().setService_a("-");}
		               break;
		               case "division d\"exploitation et de gestion des sites" :{p.getStructure().setDivision_a("قسم عمليات وإدارة الموقع");
		                                                                          switch(p.getStructure().getService()) {
		                                                                          case "-":p.getStructure().setService_a("-");
		                                                                          break;
		                                                                          case "service d\"exploitation":p.getStructure().setService_a("قسم التشغيل");
		                                                                          break;
		                                                                          case "service de gestion des sites":p.getStructure().setService_a("خدمة إدارة الموقع");
		                                                                          break;
		                                                                          }
		               }
		                break;
		               case "division d\"assistance technique et maintenance" :{p.getStructure().setDivision_a("قسم المساعدة الفنية والصيانة");
		                                                                        switch (p.getStructure().getService()) {
		                                                                        case "service de maintenance ":  p.getStructure().setService_a("خدمة الصيانة");
		                                                                        break;
		                                                                        case"-":p.getStructure().setService_a("-");
		                                                                        break;
		                                                                        }}
		                  break;
		                   case "division de réseaux et sécurité" :{p.getStructure().setDivision_a("قسم الشبكات والأمن");
		                                                             switch (p.getStructure().getService()) {
		                                                             case"-":p.getStructure().setService_a("-");
		                                                             break;
		                                                             case"service de réseaux et sécurité ":p.getStructure().setService_a("خدمة الشبكة والأمن");
		                                                             break;
		                                                             }}
		               break;}}
		break;
		case "Direction d\"études et développement informatique":{p.getStructure().setDirection_a("إدارة الدراسات وتطوير تكنولوجيا المعلومات");
		      switch (p.getStructure().getDivision()) {
		                       case "-" :{p.getStructure().setDivision_a("-");p.getStructure().setService_a("-");}
		                       break;
		                case "divison des études " :{p.getStructure().setDivision_a("قسم الدراسات");
		                                              switch (p.getStructure().getService()) {
		                                              case"-":p.getStructure().setService_a("-");
		                                              break;
		                                              case"service des études":p.getStructure().setService_a("قسم الدراسات");
		                                              break;
		                                              case"service d\"architecture du S.I":p.getStructure().setService_a("خدمة هندسة نظم المعلومات");
		                                              }
		                }
		                break;
		               case "division de developpement" :{p.getStructure().setDivision_a("قسم التنمية");
		                                                   switch (p.getStructure().getService()) {
		                                                   case"-":p.getStructure().setService_a("-");
		                                                   break;
		                                                   case"service developpement du S.I ":p.getStructure().setService_a("قسم تطوير نظم المعلومات");
		                                                   break;
		                                                   case "service maintenance et documentation du S.I ":p.getStructure().setService_a("خدمة صيانة وتوثيق نظم المعلومات ");
		                                                   break;
		                                                   }
		               }
		               break;}}
		break;
		}
		signUpRequest.setUsername(p.getPrenom()+"."+p.getNom());

		
		signUpRequest.setPassword(p.getNom()+"cims");
		Compte compte = new Compte(signUpRequest.getUsername(), 
							 encoder.encode(signUpRequest.getPassword()),null);
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_PERSONNEL)
		.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_PERSONNEL)
					 .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}
		if(personnelRepository.existsByEmail(p.getEmail())==false) {
		if( personnelService.save(p)!= null) {
			compte.setRoles(roles);
			p.setUser(compte);
			compte.setPersonnel(p);
			compteRepository.save(compte);
		}}
		else return ResponseEntity.badRequest().body(new MessageResponse("Echec d'ajout de personnel :personnel existe déja "));
		
		OrdreAffectationPers o=new OrdreAffectationPers();
		o.setPersonnel(p);
		OrdreAffectationPers ord = serviceOr.save(o);
		
		if (((p.getSite().getIdSite()) != 1))  {
			
			
			Set<Role> roles1 = new HashSet<>();
			Role CorrespondantRole = roleRepository.findByName(ERole.ROLE_CORRESPONDANT)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			Role userRole = roleRepository.findByName(ERole.ROLE_PERSONNEL)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles1.add(CorrespondantRole);
			roles1.add(userRole);
			
			Personnel pers=personnelService.getById(p.getId_personnel());
			pers.getUser().setRoles(roles1);
			
			
			AffectationTotale A=new AffectationTotale();
			A.setSite(p.getSite());
			A.setStructure(p.getStructure());
			A.setPersonnel(p);
			AffectationTotale aff = serviceAff.save(A);
			
			
			OrdreAffectationTot ot=new OrdreAffectationTot();
			ot.setAffectationTotale(aff);
			OrdreAffectationTot ordt = serviceOrt.save(ot);
			
			
		}
		
		return ResponseEntity.ok(new MessageResponse("Personnel ajouté avec succés"));
	}

}