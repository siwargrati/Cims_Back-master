package Cims.PFE.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Cims.PFE.Dao.CompteRepository;
import Cims.PFE.Dao.MissionRepository;
import Cims.PFE.Dao.NotificationRepository;
import Cims.PFE.Entities.Compte;
import Cims.PFE.Entities.Mission;
import Cims.PFE.Entities.Notification;
import Cims.PFE.Service.MissionService;
import Cims.PFE.payload.response.MessageResponse;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class MissionController {

	@Autowired
	private MissionRepository missionRepository;
	
	@Autowired
	private MissionService missionService;
	

    @Autowired
	private NotificationRepository notificationRepository;
    
    @Autowired
	private CompteRepository compteRepository;
    
	@RequestMapping(value = "/addNotif/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> addNotif(@PathVariable(name="id") Long id) {
		Mission m = missionService.getById(id);
		Long id_compte=notificationRepository.getCompte(m.getAffectationPartielle().getPersonnel().getId_personnel());
		Compte compte=compteRepository.getOne(id_compte);
		Notification notif=new Notification();
		notif.setEtat(false);
		notif.setMessage("Vous avez une mission à justifier pour le règlement des frais");
		notif.setCompte(compte);
		notificationRepository.save(notif);
		
		return ResponseEntity.ok(new MessageResponse("Notification envoyée"));

	}
    
	@RequestMapping(value = "/addNotif2/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> addNotif2(@PathVariable(name="id") Long id) {
		Mission m = missionService.getById(id);
		Long id_compte=notificationRepository.getCompte(m.getAffectationPartielle().getPersonnel().getId_personnel());
		Compte compte=compteRepository.getOne(id_compte);
		Notification notif=new Notification();
		notif.setEtat(false);
		notif.setMessage("Les frais de votre mission ont été reglé par le service RH");
		notif.setCompte(compte);
		notificationRepository.save(notif);
		
		return ResponseEntity.ok(new MessageResponse("Notification envoyée"));

	}
	@GetMapping(value="/listMissionAffectation/{id}")
	public List<Mission> listMissionAffectation(@PathVariable(name="id")Long idAffect){
		return missionRepository.getMission(idAffect);
	}
	
	@GetMapping(value="/getMission/{id}")
	public Mission get(@PathVariable(name="id") Long id) {
		return missionService.getById(id);
	}
	
	@PutMapping(value="/updateMission/{id}")
	public ResponseEntity<?> update(@PathVariable(name="id") Long id,@RequestBody Mission m){
		Mission mission=missionService.getById(id);
		mission.setHeureArrivee(m.getHeureArrivee());
		mission.setHeureDepart(m.getHeureDepart());
		mission.setProprietaire(m.getProprietaire());
		mission.setNbkm(m.getNbkm());
		missionService.save(mission);
		
	  	if(missionService.save(m)!=null) {
    		Compte compteRH=compteRepository.findByUsername("ResponsableRH").orElseThrow(() -> new RuntimeException("Error: compte is not found."));
    		Notification notifRH=new Notification();
    		notifRH.setEtat(false);
    		notifRH.setMessage("Vous avez l'ordre Mission N°"+m.getIdMission()+" à valider");
    		notifRH.setCompte(compteRH);
    		notificationRepository.save(notifRH);
    		// ajouter notfication pour le ChefService
    		Compte compteChef=compteRepository.findByUsername("ChefService").orElseThrow(() -> new RuntimeException("Error: compte is not found."));
    		Notification notifChef=new Notification();
    		notifChef.setEtat(false);
    		notifChef.setMessage("Vous avez l'ordre Mission N°"+m.getIdMission()+" à valider");
    		notifChef.setCompte(compteChef);
    		notificationRepository.save(notifChef);
    		// ajouter notfication pour le Directeur
    		Compte compte=compteRepository.findByUsername("Directeur").orElseThrow(() -> new RuntimeException("Error: compte is not found."));
			Notification notif=new Notification();
			notif.setEtat(false);
			notif.setMessage("Vous avez l'ordre Mission N°"+m.getIdMission()+" à valider");
			notif.setCompte(compte);
			notificationRepository.save(notif);
    	}
		
		return ResponseEntity.ok(new MessageResponse("Mission modifiée"));
		
	}

	
	
// les missions (accomplie)
	@GetMapping(value="/listMissionAffectationAccomplie")
	public List<Mission> listMissionAffectationAccomplie(){
		return missionRepository.getMissionAccomplie();
	}
	
	
	@GetMapping("/searchByDate")
    public ResponseEntity<List<Mission>> searchCustomerByEmail(@RequestParam("date") String date) {
		  LocalDate localDate = LocalDate.parse(date);
		List<Mission> mission = missionRepository.findByDate(localDate);
        if (mission != null) {
           
            return new ResponseEntity<List<Mission>>(mission, HttpStatus.OK);
        }
        return new ResponseEntity<List<Mission>>(HttpStatus.NO_CONTENT);
    }
}
