package Cims.PFE.Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Cims.PFE.Dao.NotificationRepository;
import Cims.PFE.Entities.Notification;
import Cims.PFE.Service.NotificationService;
import Cims.PFE.security.services.CompteDetailsImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
@RestController
public class NotificationController {
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Autowired
	private NotificationService notificationService;
	

//liste des notifications (etat=false) par personnel 
	@GetMapping(value="/listNotification")
	public List<Notification> listNotificationFalse(Principal principal){
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String username = principal.getName();	
		CompteDetailsImpl user = (CompteDetailsImpl)auth.getPrincipal();
		//Long id=user.getPersonnel().getId_personnel();
		return notificationRepository.getNotifications(user.getId());
	}
	
//modifier l'etat de notification = true(vue) par personnel	
	@PutMapping(value="/updateNotification")
	public void makeNotificationsTrue(Principal principal) {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String username = principal.getName();	
		CompteDetailsImpl user = (CompteDetailsImpl)auth.getPrincipal();
		//Long id=user.getPersonnel().getId_personnel();
		List<Notification> list=new ArrayList<Notification>();
		list=notificationRepository.getNotifications(user.getId());
		int i=0;
		Notification n;
		Long idN;
		for(i=0;i<list.size();i++) {
			idN=list.get(i).getId_notification();
			n=notificationService.getById(idN);
			n.setEtat(true);
			notificationService.save(n);
		}
	}
	
	
	
}
