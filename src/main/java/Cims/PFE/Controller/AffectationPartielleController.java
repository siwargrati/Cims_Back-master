package Cims.PFE.Controller;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import Cims.PFE.Dao.CompteRepository;
import Cims.PFE.Dao.EtatRepository;
import Cims.PFE.Dao.MissionRepository;
import Cims.PFE.Dao.NotificationRepository;
import Cims.PFE.Dao.OrdreAffPAttRepository;
import Cims.PFE.Dao.OrdreAffPRepository;
import Cims.PFE.Dao.OrdreAffPersRepository;
import Cims.PFE.Dao.OrdreMissionRepository;
import Cims.PFE.Entities.AffectationPartielle;
import Cims.PFE.Entities.Compte;
import Cims.PFE.Entities.DashGouv;
import Cims.PFE.Entities.Etat_AffectationP;
import Cims.PFE.Entities.Mission;
import Cims.PFE.Entities.Notification;
import Cims.PFE.Entities.OrdreAffectationP;
import Cims.PFE.Entities.OrdreAffectationPAtt;
import Cims.PFE.Entities.OrdreAffectationPers;
import Cims.PFE.Entities.OrdreMission;
import Cims.PFE.Entities.Site;
import Cims.PFE.Entities.Structure;
import Cims.PFE.Entities.Type_Etat_AffP;
import Cims.PFE.Service.AffectationPartielleService;
import Cims.PFE.Service.MissionService;
import Cims.PFE.Service.OrdreAffPAttService;
import Cims.PFE.Service.OrdreAffPersService;
import Cims.PFE.Service.OrdreMissionService;
import Cims.PFE.Utils.DateChecker;
import Cims.PFE.payload.response.MessageResponse;
import Cims.PFE.security.services.CompteDetailsImpl;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AffectationPartielleController {
	@Autowired
	private AffectationPartielleService service;
	@Autowired
	private OrdreAffPRepository serviceOrdre;
	@Autowired
	private AffectationPartielleRepository Affrepo;
	@Autowired
	EtatRepository etatRepo;
	@Autowired
	private OrdreAffPAttRepository serviceOrdreAtt;
	@Autowired
	private OrdreAffPAttService serviceOrAtt;
	@Autowired
	private MissionService missionService;
	@Autowired
	private NotificationRepository notificationRepository;
	@Autowired
	private OrdreAffPRepository ordreAffPRepository;
	@Autowired
	private MissionRepository missionrepo;
	@Autowired
	private OrdreMissionService ordreMissService;
	@Autowired
	private OrdreMissionRepository ordreMissRepo;
	@Autowired
	private AffectationPartielleRepository apr;
	@Autowired
	CompteRepository compteRepository;
	@GetMapping(value="/listAffectation_P")
	public List<AffectationPartielle> listAffectationPartielle(){
		return service.listAll();
	}

	
	@PostMapping(value="/addAffectation_P")
	public ResponseEntity<?> save(@RequestBody AffectationPartielle p,Principal principal) {
		boolean ajouter = true;
		List<AffectationPartielle> list=apr.getAffectPartielle(p.getPersonnel().getId_personnel());
		if (list.size()<7)
		if((DateChecker.isHoliday(p.getDateDebut(),p.getDatefin())==false)&& DateChecker.isToday(p.getDateDebut())==false) {
			List<AffectationPartielle> aff=new ArrayList<AffectationPartielle>();

			
			if(Affrepo.existsByPersonnel(p.getPersonnel())) {
			aff=Affrepo.getAffectPartielle(p.getPersonnel().getId_personnel());
			int i=0;
			for(i=0;i<aff.size();i++) {
				for(int j=0;j<p.getJour().size();j++) {
					//if(aff.get(i).getDateDebut().equals(p.getDateDebut()) || aff.get(i).getDatefin().equals(p.getDatefin()) ) {
						if(aff.get(i).getDateDebut().isAfter(p.getDateDebut())  && aff.get(i).getDatefin().isBefore(p.getDatefin()) ) {
						if( aff.get(i).getJour().contains(p.getJour().get(j))) {
							ajouter=false;
						}
					}	
				}
			}
			}
			if(ajouter) {
			Etat_AffectationP etat=etatRepo.findByNomEtat(Type_Etat_AffP.EN_ATTENTE);
			p.setEtat(etat);
		if(service.save(p)!=null) {
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			String username = principal.getName();	
			CompteDetailsImpl user = (CompteDetailsImpl)auth.getPrincipal();
			if(user.getUsername().equals("ChefService")) {
				//ajouter notification pour directeur
				Compte compte=compteRepository.findByUsername("Directeur").orElseThrow(() -> new RuntimeException("Error: compte is not found."));
				Notification notif=new Notification();
				notif.setEtat(false);
				notif.setMessage("Nouvelle affectation partielle en attente ajoutée  par le Chef Service ");
				notif.setCompte(compte);
				notificationRepository.save(notif);
			}
		}
		OrdreAffectationPAtt o=new OrdreAffectationPAtt();
		o.setAffectationPartielle(p);
		OrdreAffectationPAtt ord = serviceOrAtt.save(o);	
		return ResponseEntity.ok(new MessageResponse(" Affectation partielle ajoutée"));
		}
		}
		return ResponseEntity.badRequest()
				.body(new MessageResponse("Error:Echec d'ajout d'affectation"));
	}

	
	@PutMapping(value="/updateAffectation_P/{id}")
	public ResponseEntity<?> update(@PathVariable(name="id") Long id,@RequestBody AffectationPartielle p){
		List<Date> days=new ArrayList<Date>();
		AffectationPartielle affP=service.getById(id);
		affP.setDateDebut(p.getDateDebut());
		affP.setDatefin(p.getDatefin());
		affP.setEtat(p.getEtat());
		affP.setJour(p.getJour());
		affP.setPersonnel(p.getPersonnel());
		affP.setSite(p.getSite());
		affP.setSujet(p.getSujet());
		final AffectationPartielle updatedAffect=service.save(affP);
		//ajouter notification pour chef service
		Compte compteChef=compteRepository.findByUsername("ChefService").orElseThrow(() -> new RuntimeException("Error: compte is not found."));
		Notification notifChef=new Notification();
		notifChef.setEtat(false);
		notifChef.setMessage("Affectation partielle N°"+p.getIdAffect()+" est "+p.getEtat().getNom_etat());
		notifChef.setCompte(compteChef);
		notificationRepository.save(notifChef);
		int i=0;
		LocalDateTime dates;
		LocalDate convDate;
		if(affP.getEtat().getId_etat()==2) {
			//creation de notification
			//pour personnel
			Long id_compte=notificationRepository.getCompte(affP.getPersonnel().getId_personnel());
			Compte compte=compteRepository.getOne(id_compte);
			Notification notif=new Notification();
			notif.setEtat(false);
			notif.setMessage("Nouvelle affectation partielle à partir de "+affP.getDateDebut());
			notif.setCompte(compte);
			notificationRepository.save(notif);
			//creation de mission
			int d=DateChecker.nbrDay(affP.getDateDebut(), affP.getDatefin(),affP.getJour());
			days= DateChecker.Days(affP.getDateDebut(), affP.getDatefin(),affP.getJour());
			for( i=0;i<d;i++) {
				Mission m=new Mission();
				m.setAffectationPartielle(affP);
				Instant timestamp = days.get(i).toInstant();
				dates = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault()); 
				 convDate=dates.toLocalDate();
				m.setDate(convDate);
				m.setEtat_accomplie(false);
				missionService.save(m);
				//creation d'ordre de mission
				OrdreMission om=new OrdreMission();
				om.setMission(m);
				ordreMissService.save(om);
			}
		}
		//creation d'ordre d'affectation
		OrdreAffectationP o=new OrdreAffectationP();
		o.setAffectationPartielle(affP);
		serviceOrdre.save(o);
		return ResponseEntity.ok(new MessageResponse("Affectation  partielle modifiée!"));
	}

	
	@GetMapping(value="/getAffectation_P/{id}")
	public AffectationPartielle get(@PathVariable(name="id") Long id) {
		return service.getById(id);
	}
	@DeleteMapping(value="/deleteAffectation_P/{id}")
	public ResponseEntity<?> delete(@PathVariable(name="id") Long id){
		AffectationPartielle p=service.getById(id);
		if(p.getEtat().getId_etat()==2) {
			serviceOrdre.deleteOrdre(id);
			List<Mission> list=missionrepo.getMission(id);
			int i=0;
			for(i=0;i<list.size();i++) {
				ordreMissRepo.deleteOrdreMission(list.get(i).getIdMission());
			}
			missionrepo.deleteMission(id);	
		}
		p.getJour().clear();
	service.delete(id);
		return ResponseEntity.ok(new MessageResponse("Affectation partielle supprimer!"));
	}

	

	

	
	@GetMapping(value="/listAffectation_P_EnAttente")
	public List<AffectationPartielle> listAffectationPartielleEnAttente(){
		Etat_AffectationP etat=etatRepo.findByNomEtat(Type_Etat_AffP.EN_ATTENTE);
		Etat_AffectationP etatRefusee=etatRepo.findByNomEtat(Type_Etat_AffP.REFUSEE);
		List<AffectationPartielle> list =Affrepo.findByEtat(etat);
		Compte compteChef=compteRepository.findByUsername("ChefService").orElseThrow(() -> new RuntimeException("Error: compte is not found."));

		LocalDate d= LocalDate.now();
		int i;
		for(i=0;i<list.size();i++) {
			if(list.get(i).getDateDebut().equals(d.plusDays(1))) {
				list.get(i).setEtat(etatRefusee);
				Affrepo.save(list.get(i));
				Notification notifChef=new Notification();
				notifChef.setEtat(false);
				notifChef.setMessage("Affectation partielle N°"+list.get(i).getIdAffect()+" est "+"REFUSEE");
				notifChef.setCompte(compteChef);
				notificationRepository.save(notifChef);
			}
		}
		return Affrepo.findByEtat(etat);
	}
	@GetMapping(value="/listAffectation_P_Validees")
	public List<AffectationPartielle> listAffectationPartielleValider(){
		Etat_AffectationP etat=etatRepo.findByNomEtat(Type_Etat_AffP.VALIDEE);
		return Affrepo.findByEtat(etat);
	}
	@GetMapping(value="/listAffectation_P_Refusees")
	public List<AffectationPartielle> listAffectationPartielleRefusees(){
		Etat_AffectationP etat=etatRepo.findByNomEtat(Type_Etat_AffP.REFUSEE);
		return Affrepo.findByEtat(etat);
	}

	
/*liste des affectations partielles de personnel correspondant connecté*/
@GetMapping(value="/listAffectation_P_Personnel")
public List<AffectationPartielle> AffectationPartiellePersonnel(Principal principal){
	Authentication auth=SecurityContextHolder.getContext().getAuthentication();
	String username = principal.getName();	
	CompteDetailsImpl user = (CompteDetailsImpl)auth.getPrincipal();
	Long id=user.getPersonnel().getId_personnel();
	return Affrepo.getAffectation(id);
}

//liste des jours 
@GetMapping(value="/listJours/{id}/{dateDebut}/{dateFin}")
public List<String> listJours(@PathVariable(name="id")Long id_personnel ,@PathVariable(name="dateDebut") Date  dateDebut,@PathVariable(name="dateFin") Date  dateFin){
	LocalDate date = dateDebut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	LocalDate dateF = dateFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	List<String> list=Affrepo.getJours(id_personnel, date,dateF);
	List<String> jours=new ArrayList<String>();
	jours.add("Lundi");
	jours.add("Mardi");
	jours.add("Mercredi");
	jours.add("Jeudi");
	jours.add("Vendredi");
	jours.add("Samedi");
	jours.removeAll(list);
	return jours;
}
	/*@GetMapping(value="/getNbrAffectP")
	public
	Long nbrAffectP(){
		return Affrepo.getNbrAffectpart();
	}
*/


}