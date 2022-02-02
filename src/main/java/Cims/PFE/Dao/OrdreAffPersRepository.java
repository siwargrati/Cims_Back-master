package Cims.PFE.Dao;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

//import Cims.PFE.Entities.OrdreAffectationP;
import Cims.PFE.Entities.OrdreAffectationPers;
@Transactional
@CrossOrigin("*")
@RepositoryRestResource
public interface OrdreAffPersRepository extends JpaRepository<OrdreAffectationPers,Long>{
	@Query(value="SELECT * from ordre_affectation_pers   where id_personnel_p=?1 ",nativeQuery = true)
	OrdreAffectationPers getAffectationPers(Long id_personnel);
	
	
	@Query(value="SELECT * from ordre_affectation_pers where id_personnel_p=?1 ",nativeQuery = true)
	OrdreAffectationPers getOrdreOfAffPers(Long id_personnel);
	
	@Transactional
	@Modifying
	@Query(value="delete from ordre_affectation_pers   where id_personnel_p=?1 ",nativeQuery = true)
	void deleteOrdre(Long id_personnel);
}
