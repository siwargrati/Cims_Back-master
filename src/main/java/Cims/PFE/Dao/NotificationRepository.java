package Cims.PFE.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Cims.PFE.Entities.Compte;
import Cims.PFE.Entities.Notification;

public interface NotificationRepository extends JpaRepository<Notification,Long>{

	@Query(value="SELECT * from notification   where id=?1 order by etat  ",nativeQuery = true)
	List<Notification> getNotifications(Long id);
	@Query(value="SELECT id from comptes   where personnel_id_personnel=?1",nativeQuery = true)
	Long getCompte(Long id_personnel);
}
