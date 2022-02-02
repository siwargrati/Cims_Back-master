package Cims.PFE.Controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Cims.PFE.Dao.AffectationPartielleRepository;
import Cims.PFE.Entities.Personnel;
import Cims.PFE.Service.AffectationPartielleService;
import Cims.PFE.Service.OrdreAffPAttService;
import Cims.PFE.Service.OrdreAffPService;
import Cims.PFE.Service.OrdreAffPersService;
import Cims.PFE.Service.OrdreAffTotService;
import Cims.PFE.Service.OrdreMissionService;
import Cims.PFE.Service.PersonnelService;
import Cims.PFE.Utils.generatePdf;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
@RestController
public class PdfController {

	@Autowired
	private OrdreAffPService ordreService;
	
	@Autowired
	private AffectationPartielleRepository AffRepo;
	
	@Autowired
	private OrdreAffPAttService ordreServiceAtt;
	
	@Autowired
	private OrdreAffPersService ordreServicePers;

	@Autowired
	private OrdreAffTotService ordreServiceTot;
	
	
	@Autowired
	private OrdreMissionService ordreMissionService;
	
	//afficher le pdf dans le navigateur
	//ordre aff
	@RequestMapping(value = "/pdfreport/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> Report(@PathVariable(name="id") Long id) throws IOException {
		
		

		ByteArrayInputStream bis = generatePdf.Report(ordreService.getById(id));

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=report.pdf");
		//headers.add("Content-Disposition", "attachment; filename=report.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
		//save pdf file fi bureau
	}
	
	//ordre aff attente
		@RequestMapping(value = "/pdfreportAtt/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
		public ResponseEntity<InputStreamResource> ReportAtt(@PathVariable(name="id") Long id) throws IOException {
			
			

			ByteArrayInputStream bis = generatePdf.ReportAtt(ordreServiceAtt.getById(id));

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=report.pdf");
			//headers.add("Content-Disposition", "attachment; filename=report.pdf");

			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
					.body(new InputStreamResource(bis));
			//save pdf file fi bureau
		}
		
		
		//ordre aff attente inf
				@RequestMapping(value = "/pdfreportAttInf/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
				public ResponseEntity<InputStreamResource> ReportAttInf(@PathVariable(name="id") Long id) throws IOException {
					
					

					ByteArrayInputStream bis = generatePdf.ReportAttInf(ordreServiceAtt.getById(id));

					HttpHeaders headers = new HttpHeaders();
					headers.add("Content-Disposition", "inline; filename=report.pdf");
					//headers.add("Content-Disposition", "attachment; filename=report.pdf");

					return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
							.body(new InputStreamResource(bis));
					//save pdf file fi bureau
				}
			
	
	
	//ordre aff
		@RequestMapping(value = "/pdfreportpers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
		public ResponseEntity<InputStreamResource> ReportPers(@PathVariable(name="id") Long id) throws IOException {
			
			

			ByteArrayInputStream bis = generatePdf.ReportPers(ordreServicePers.getById(id));

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=report.pdf");
			//headers.add("Content-Disposition", "attachment; filename=report.pdf");

			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
					.body(new InputStreamResource(bis));
			//save pdf file fi bureau
		}
	
		//ordre aff totale
		@RequestMapping(value = "/pdfreporttot/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
		public ResponseEntity<InputStreamResource> ReportTot(@PathVariable(name="id") Long id) throws IOException {
			
			

			ByteArrayInputStream bis = generatePdf.ReportTot(ordreServiceTot.getById(id));

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", "inline; filename=report.pdf");
			//headers.add("Content-Disposition", "attachment; filename=report.pdf");

			return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
					.body(new InputStreamResource(bis));
			//save pdf file fi bureau
		}
	
	// ordre mission
	@RequestMapping(value = "/missionReport/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> missionReport(@PathVariable(name="id") Long id) throws IOException {
		
		

		ByteArrayInputStream bis = generatePdf.ReportMission(ordreMissionService.getById(id));

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "inline; filename=reportMission.pdf");

		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
				.body(new InputStreamResource(bis));
	}
}
