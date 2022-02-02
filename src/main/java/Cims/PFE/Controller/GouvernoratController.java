package Cims.PFE.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Cims.PFE.Entities.Gouvernorat;
import Cims.PFE.Service.GouvernoratService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class GouvernoratController {
	@Autowired
	private GouvernoratService gouvernoratService;
	
	@GetMapping(value="/listGouvernorats")
	public List<Gouvernorat> listGouvernorats(){
		return gouvernoratService.listAll();
	}

}
