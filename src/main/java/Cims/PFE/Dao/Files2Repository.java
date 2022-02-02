package Cims.PFE.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import Cims.PFE.Entities.Files;
import Cims.PFE.Entities.Files2;

@CrossOrigin("*")
@RepositoryRestResource
@Transactional
public interface Files2Repository extends JpaRepository<Files2,Long>{
	@Query(value="SELECT * from files2   where id_mission=?1",nativeQuery = true)
	List<Files2> listFiels(Long idMission);
}
