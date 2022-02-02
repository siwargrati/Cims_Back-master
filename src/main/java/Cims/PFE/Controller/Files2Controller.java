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
import Cims.PFE.Dao.Files2Repository;
import Cims.PFE.Dao.FilesRepository;
import Cims.PFE.Dao.MissionRepository;
import Cims.PFE.Dao.NotificationRepository;
import Cims.PFE.Entities.Compte;
import Cims.PFE.Entities.Files;
import Cims.PFE.Entities.Files2;
import Cims.PFE.Entities.Mission;
import Cims.PFE.Entities.Notification;
import Cims.PFE.Service.Files2Service;
import Cims.PFE.Service.FilesService;
import Cims.PFE.Service.MissionService;
import Cims.PFE.payload.response.MessageResponse;
import Cims.PFE.payload.response.UploadFileResponse;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class Files2Controller {
	private static final Logger logger = LoggerFactory.getLogger(Files2Controller.class);

    @Autowired
    private Files2Service filesService;
    
    @Autowired
	private MissionService missionService;
    
    @Autowired
   private Files2Repository filesRepo;
    
    @Autowired
	private NotificationRepository notificationRepository;
    
    @Autowired
	private CompteRepository compteRepository;
    
    @Autowired
	private MissionRepository missionrepo;

   //upload file (id mission)
    @PostMapping("/upload2/{id}")
    public ResponseEntity<MessageResponse> uploadFile2(@RequestParam("file2") MultipartFile file2,@PathVariable(name="id")Long id) {
      String message = "";
      boolean test=true;
      try {
    	  Mission m= missionService.getById(id);
    	  if(m.getHeureArrivee().equals("") || m.getHeureDepart().equals("")) {
    		  test=false;
    	  }
    	  
    	  if(test) {
    		  Files2 f= filesService.storeFile(file2);
    	    	
    	    	
    	    	//f.setMission(m);
    	    //	filesService.save(f);
    	    	m.setFile2(f);
    	    	m.setEtat_accomplie(true);
    	    	// ajouter notfication pour le responsableRH
    	    	if(missionService.save(m)!=null) {
    	    		Compte compteRH=compteRepository.findByUsername("ResponsableRH").orElseThrow(() -> new RuntimeException("Error: compte is not found."));
    	    		Notification notifRH=new Notification();
    	    		notifRH.setEtat(false);
    	    		notifRH.setMessage("Vous avez une mission à régler");
    	    		notifRH.setCompte(compteRH);
    	    		notificationRepository.save(notifRH);
    	    	
    	    	}
    	    	
    	        message = "Téléchargement du fichier réussi: " + file2.getOriginalFilename();
    	        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
    	  }
    	  message = "erreur " + file2.getOriginalFilename();
    	  return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
      } catch (Exception e) {
        message = "Impossible de télécharger le fichier: " + file2.getOriginalFilename() + "!";
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
      }
    }


    
    //download file par id file
    @GetMapping("/downloadFile2/{fileId}")
    public ResponseEntity<Resource> downloadFile2(@PathVariable Long fileId) {
        
        Files2 dbFile = filesService.getFile2(fileId);
        Mission m=missionrepo.getMissionFILE2(fileId);
     Long id_compte=notificationRepository.getCompte(missionrepo.getIdPersonnel2(fileId));
     Compte compte=compteRepository.getOne(id_compte);
     Notification notif=new Notification();
     notif.setEtat(false);
     notif.setMessage("Fichier de mission de date "+m.getDate()+" a été téléchargé avec succès");
     notif.setCompte(compte);
     notificationRepository.save(notif);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType2()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName2() + "\"")
                .body(new ByteArrayResource(dbFile.getData2()));
    }
      
    
      
}
