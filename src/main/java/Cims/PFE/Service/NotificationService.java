package Cims.PFE.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Cims.PFE.Dao.NotificationRepository;
import Cims.PFE.Entities.Notification;

@Service
public class NotificationService {
	@Autowired
	private NotificationRepository notificationRepository;
	
	public List<Notification> listAll(){
		List<Notification> notifications = new ArrayList<>();
		notificationRepository.findAll().forEach(notifications::add);
		return notifications;
	}
	
	public Notification save(Notification n) {
		return notificationRepository.save(n);
	}
	
	
	public Notification getById(Long id) {
		return notificationRepository.findById(id).get();
	}
	public void delete(Long id){
		notificationRepository.deleteById(id);
	}
}
