package Cims.PFE.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import Cims.PFE.Dao.CompteRepository;
import Cims.PFE.Dao.FilesRepository;
import Cims.PFE.Dao.MissionRepository;
import Cims.PFE.Dao.NotificationRepository;
import Cims.PFE.Entities.Compte;
import Cims.PFE.Entities.Files;
import Cims.PFE.Entities.Mission;
import Cims.PFE.Entities.Notification;
import Cims.PFE.Service.FilesService;
import Cims.PFE.Service.MissionService;
import Cims.PFE.payload.response.MessageResponse;
import Cims.PFE.payload.response.UploadFileResponse;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class FilesController {
	private static final Logger logger = LoggerFactory.getLogger(FilesController.class);

    @Autowired
    private FilesService filesService;
    
    @Autowired
	private MissionService missionService;
    
    @Autowired
   private FilesRepository filesRepo;
    
    @Autowired
	private NotificationRepository notificationRepository;
    
    @Autowired
	private CompteRepository compteRepository;
    
    @Autowired
	private MissionRepository missionrepo;

   //upload file (id mission)
    @PostMapping("/upload/{id}")
    public ResponseEntity<MessageResponse> uploadFile(@RequestParam("file") MultipartFile file,@PathVariable(name="id")Long id) {
      String message = "";
      boolean test=true;
      try {
    	  Mission m= missionService.getById(id);
    	  if(m.getHeureArrivee().equals("") || m.getHeureDepart().equals("")) {
    		  test=false;
    	  }
    	  
    	  if(test) {
    		  Files f= filesService.storeFile(file);
    	    	
    	    	
    	    	//f.setMission(m);
    	    //	filesService.save(f);
    	    	m.setFile(f);
    	    	m.setEtat_accomplie(true);
    	    	// ajouter notfication pour le responsableRH
    	    	if(missionService.save(m)!=null) {
    	    		Compte compteRH=compteRepository.findByUsername("ResponsableRH").orElseThrow(() -> new RuntimeException("Error: compte is not found."));
    	    		Notification notifRH=new Notification();
    	    		notifRH.setEtat(false);
    	    		notifRH.setMessage("Mission N°"+m.getIdMission()+" est terminé");
    	    		notifRH.setCompte(compteRH);
    	    		notificationRepository.save(notifRH);
    	    		// ajouter notfication pour le ChefService
    	    		Compte compteChef=compteRepository.findByUsername("ChefService").orElseThrow(() -> new RuntimeException("Error: compte is not found."));
    	    		Notification notifChef=new Notification();
    	    		notifChef.setEtat(false);
    	    		notifChef.setMessage("Mission N°"+m.getIdMission()+" est terminé");
    	    		notifChef.setCompte(compteChef);
    	    		notificationRepository.save(notifChef);
    	    		// ajouter notfication pour le Directeur
    	    		Compte compte=compteRepository.findByUsername("Directeur").orElseThrow(() -> new RuntimeException("Error: compte is not found."));
    				Notification notif=new Notification();
    				notif.setEtat(false);
    				notif.setMessage("Mission N°"+m.getIdMission()+" est terminé");
    				notif.setCompte(compte);
    				notificationRepository.save(notif);
    	    	}
    	    	
    	        message = "Téléchargement du fichier réussi: " + file.getOriginalFilename();
    	        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
    	  }
    	  message = "erreur " + file.getOriginalFilename();
    	  return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
      } catch (Exception e) {
        message = "Impossible de télécharger le fichier: " + file.getOriginalFilename() + "!";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
      }
    }


    
    //download file par id file
    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
        
        Files dbFile = filesService.getFile(fileId);
        Mission m=missionrepo.getMissionFILE(fileId);
     Long id_compte=notificationRepository.getCompte(missionrepo.getIdPersonnel(fileId));
     Compte compte=compteRepository.getOne(id_compte);
     Notification notif=new Notification();
     notif.setEtat(false);
     notif.setMessage("Fichier de mission de date "+m.getDate()+" a été téléchargé avec succès");
     notif.setCompte(compte);
     notificationRepository.save(notif);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }
      
    
      
}
