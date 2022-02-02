package Cims.PFE.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import Cims.PFE.Entities.OrdreAffectationP;
import Cims.PFE.Entities.OrdreAffectationPAtt;
@CrossOrigin("*")
@RepositoryRestResource
public interface OrdreAffPAttRepository extends JpaRepository<OrdreAffectationPAtt,Long>{
	@Query(value="SELECT * from ordre_affectationpatt   where id_affectation_p=?1 ",nativeQuery = true)
	OrdreAffectationPAtt getAffectation(Long idAffect);
		
	@Query(value="SELECT * from ordre_affectationpatt   where idAffect=?1 ",nativeQuery = true)
	List<OrdreAffectationPAtt> getAffectationatt(Long idAffect);
	
	@Query(value="SELECT count (*) from ordre_affectationpatt ",nativeQuery = true)
	Long getNbAffP();	
	
	
	@Query(value="SELECT * from ordre_affectationpatt where id_affectation_p=?1 ",nativeQuery = true)
	OrdreAffectationPAtt getOrdreOfAffAtt(Long idAffect);
	
	@Transactional
	@Modifying
	@Query(value="delete from ordre_affectationpatt   where id_affectation_p=?1 ",nativeQuery = true)
	void deleteOrdre(Long idAffect);
}
