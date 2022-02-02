package Cims.PFE.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	
	@GetMapping("/CORRESPONDANT")
	@PreAuthorize("hasRole('CORRESPONDANT')")
	public String personelAccess() {
		return "Espace personnel correspondant";
	}

	@GetMapping("/RH")
	@PreAuthorize("hasRole('RH')")
	public String RHAccess() {
		return "Espace responsable RH ";
	}

	@GetMapping("/chefService")
	@PreAuthorize("hasRole('CHEFSERVICE')")
	public String ChefAccess() {
		return "Espace chef service d'exploitation ";
	}
	@GetMapping("/directeur")
	@PreAuthorize("hasRole('DIRECTEUR')")
	public String DirecteurAccess() {
		return "Espace directeur";
	}
}
