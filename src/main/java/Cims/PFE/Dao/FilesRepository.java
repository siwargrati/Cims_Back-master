package Cims.PFE.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import Cims.PFE.Entities.Files;

@CrossOrigin("*")
@RepositoryRestResource
@Transactional
public interface FilesRepository extends JpaRepository<Files,Long>{
	@Query(value="SELECT * from files   where id_mission=?1",nativeQuery = true)
	List<Files> listFiels(Long idMission);
}
