package Cims.PFE.Dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import Cims.PFE.Entities.OrdreMission;
@CrossOrigin("*")
@RepositoryRestResource
public interface OrdreMissionRepository extends JpaRepository<OrdreMission,Long>{

	@Query(value="SELECT * from ordre_mission where id_mission=?1 ",nativeQuery = true)
	OrdreMission getOrdreM_Of_Miss(Long idMission);
	
	@Transactional
	@Modifying
	@Query(value="delete from ordre_mission   where mission_id_mission=?1 ",nativeQuery = true)
	void deleteOrdreMission(Long idMission);
}
