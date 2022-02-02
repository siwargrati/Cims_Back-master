package Cims.PFE.Dao;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import Cims.PFE.Entities.*;
@Transactional
public interface MissionRepository extends JpaRepository<Mission,Long> {
	@Query(value="SELECT * from mission   where id_affectation_p=?1 ",nativeQuery = true)
	List<Mission> getMission(Long idAffect);
	
	@Query(value="SELECT * from mission   where   etat_accomplie=true order by date DESC",nativeQuery = true)
	List<Mission> getMissionAccomplie();
	
	@Query(value="SELECT count(id_mission) from mission where date=(sELECT DATE( NOW() )) ",nativeQuery = true)
	Long getNbrMissionToday();
	
	@Query(value="SELECT distinct count(id_mission)as nbrMission,id_affectation_p as affectP FROM public.mission natural join affectation_partielle where id_personnel =?1 group by id_affectation_p ; ",nativeQuery = true)
	List<DashMissParAffPers> getMissParAffecP(Long id_personnel);
	
	@Query(value="SELECT  count(id_mission) as nbr,date_trunc ('months', date ) as mois FROM public.mission natural join affectation_partielle where id_personnel =?1 group by date_trunc ('months', date ) ; ",nativeQuery = true)
    List<DashMissParMois> getMissionParMois(Long id_personnel);
	
	@Query(value="SELECT  count(id_mission) as nbr FROM public.mission natural join affectation_partielle where id_personnel =?1 and etat_accomplie=true  ; ",nativeQuery = true)
	Long getNbrMissionAccomplie(Long id_personnel);
	
	@Query(value="SELECT  count(id_mission) as nbr FROM public.mission natural join affectation_partielle where id_personnel =?1 and etat_accomplie=false  ; ",nativeQuery = true)
	Long getNbrMissionNonAccomplie(Long id_personnel);
	
	@Transactional
	@Modifying
	@Query(value="delete from mission   where id_affectation_p=?1 ",nativeQuery = true)
	void deleteMission(Long idAffect);
	
	List<Mission> findByDate(LocalDate d);
	
	@Query(value="SELECT id_personnel FROM public.mission join affectation_partielle using(id_affectation_p) where file_id_file=?1 ",nativeQuery = true)
	Long getIdPersonnel(Long id_file);
	
	@Query(value="SELECT * FROM public.mission  where file_id_file=?1",nativeQuery = true)
	Mission getMissionFILE(Long id_file);
	
	
	@Query(value="SELECT id_personnel FROM public.mission join affectation_partielle using(id_affectation_p) where file2_id_file2=?1 ",nativeQuery = true)
	Long getIdPersonnel2(Long id_file2);
	
	@Query(value="SELECT * FROM public.mission  where file2_id_file2=?1",nativeQuery = true)
	Mission getMissionFILE2(Long id_file2);
}
