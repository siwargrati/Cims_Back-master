package Cims.PFE.Dao;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import Cims.PFE.Entities.Fonction;
import Cims.PFE.Entities.Personnel;
@CrossOrigin("*")
@RepositoryRestResource
public interface PersonnelRepository extends JpaRepository<Personnel,Long>{
	Boolean existsByEmail(String email);
	@Query(value="SELECT count(id_personnel) from personnel  ",nativeQuery = true)
	Long getNbrPersonnel();
	@Query(value="SELECT * from personnel where id_grade=?1  ",nativeQuery = true)
	List<Personnel> getGrade(Long id);
	@Query(value="SELECT * from personnel where id_struct=?1  ",nativeQuery = true)
	List<Personnel> getStruct(Long id);
	@Query(value="SELECT * from personnel where id_fonction=?1  ",nativeQuery = true)
	List<Personnel> getFonction(Long id);
	@Query(value="SELECT * from personnel where id_site=?1  ",nativeQuery = true)
	List<Personnel> getSite(Long id);

/*	@Query(value="SELECT * from personnel p where p.id_personnel = userId ",nativeQuery = true)
	List<Personnel> findPersonnelByUserId(Long userId);
*/	
	@Query(value="SELECT * from personnel  where id_personnel=?1 ",nativeQuery = true)
	List<Personnel> getPersonnel(Long id_personnel);
}