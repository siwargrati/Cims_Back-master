package Cims.PFE.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Cims.PFE.Dao.AffectationPartielleRepository;
import Cims.PFE.Dao.DashbordRepository;
import Cims.PFE.Dao.MissionRepository;
import Cims.PFE.Dao.PersonnelRepository;
import Cims.PFE.Entities.DashGouv;
import Cims.PFE.Entities.DashMissParAffPers;
import Cims.PFE.Entities.DashMissParMois;
import Cims.PFE.Entities.Dashboard;
import Cims.PFE.Entities.Dashbord;
import Cims.PFE.security.services.CompteDetailsImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
@RestController
public class DashbordController {
	@Autowired
	private AffectationPartielleRepository Affrepo;
	
	@Autowired
	private PersonnelRepository personnelRepository;
	
	@Autowired
	private MissionRepository missionRepository;
	
	@GetMapping(value="/listerAffpardate")
	List<Dashboard>lister(){
		return Affrepo.DashAffpardate();
	}
	
	@GetMapping(value="/getNbrMissionToday")
	Long nbr(){
		return missionRepository.getNbrMissionToday();
	}
	
	@GetMapping(value="/getNbrAffectValidee")
	Long AffectValidee(){
		return Affrepo.getNbrAffectValidee();
	}
	
	@GetMapping(value="/getNbrAffectRefusee")
	Long AffectRefusee(){
		return Affrepo.getNbrAffectRefusee();
	}
	
	@GetMapping(value="/getNbrPersonnel")
	Long nbrPersonnel(){
		return personnelRepository.getNbrPersonnel();
	}
	
	@GetMapping(value="/listerAffparGouv")
	List<DashGouv>listerDashGouv(){
		return Affrepo.DashAffparGouv();
	}
	/***********Personnel**********/
	
	//nbr mission par affectation partielle (par personnel)
	@GetMapping(value="/listerMissParAffecP")
	List<DashMissParAffPers>listerMissParAffecP(Principal principal){
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String username = principal.getName();	
		CompteDetailsImpl user = (CompteDetailsImpl)auth.getPrincipal();
		return missionRepository.getMissParAffecP(user.getPersonnel().getId_personnel());
	}
	
	//nbr mission par mois  (par personnel)
		@GetMapping(value="/listerMissParMois")
		List<DashMissParMois>listerMissParMois(Principal principal){
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			String username = principal.getName();	
			CompteDetailsImpl user = (CompteDetailsImpl)auth.getPrincipal();
			return missionRepository.getMissionParMois(user.getPersonnel().getId_personnel());
		}
		
		//nbr mission (accomplie)
		@GetMapping(value="/nbrMissionAccomplie")
		Long nbrMissAcc(Principal principal){
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			String username = principal.getName();	
			CompteDetailsImpl user = (CompteDetailsImpl)auth.getPrincipal();
			return missionRepository.getNbrMissionAccomplie(user.getPersonnel().getId_personnel());
		}
		
		//nbr mission (non accomplie)
		@GetMapping(value="/nbrMissionNonAccomplie")
			Long nbrMissNonAcc(Principal principal){
			Authentication auth=SecurityContextHolder.getContext().getAuthentication();
			String username = principal.getName();	
			CompteDetailsImpl user = (CompteDetailsImpl)auth.getPrincipal();
			return missionRepository.getNbrMissionNonAccomplie(user.getPersonnel().getId_personnel());
			}
		
		
	
}
