package Cims.PFE.Dao;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

//import Cims.PFE.Entities.OrdreAffectationP;
import Cims.PFE.Entities.OrdreAffectationPers;
import Cims.PFE.Entities.OrdreAffectationTot;
@Transactional
@CrossOrigin("*")
@RepositoryRestResource
public interface OrdreAffTotRepository extends JpaRepository<OrdreAffectationTot,Long>{
	@Query(value="SELECT * from ordre_affectation_tot   where id_affectationT=?1 ",nativeQuery = true)
	OrdreAffectationTot getAffectationTot(Long idAffectT);
	
	
	@Query(value="SELECT * from ordre_affectation_tot  where id_affectationT=?1 ",nativeQuery = true)
	OrdreAffectationTot getOrdreOfAffTot(Long idAffectT);
	
	@Transactional
	@Modifying
	@Query(value="delete from ordre_affectation_tot   where id_affectationT=?1 ",nativeQuery = true)
	void deleteOrdre(Long idAffectT);
}
