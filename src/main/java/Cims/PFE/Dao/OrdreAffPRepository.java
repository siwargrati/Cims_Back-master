package Cims.PFE.Dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import Cims.PFE.Entities.OrdreAffectationP;
@CrossOrigin("*")
@RepositoryRestResource
public interface OrdreAffPRepository extends JpaRepository<OrdreAffectationP,Long>{
	@Query(value="SELECT * from ordre_affectationp   where id_affectation_p=?1 ",nativeQuery = true)
	OrdreAffectationP getAffectation(Long idAffect);
	
	@Query(value="SELECT * from ordre_affectationp where id_affectation_p=?1 ",nativeQuery = true)
	OrdreAffectationP getOrdreOfAff(Long idAffect);
	
	@Transactional
	@Modifying
	@Query(value="delete from ordre_affectationp   where id_affectation_p=?1 ",nativeQuery = true)
	void deleteOrdre(Long idAffect);
}
