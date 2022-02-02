package Cims.PFE.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import Cims.PFE.Entities.Etat_AffectationP;
import Cims.PFE.Entities.Type_Etat_AffP;

@CrossOrigin("*")
@RepositoryRestResource
public interface EtatRepository extends JpaRepository<Etat_AffectationP,Long>{
	Etat_AffectationP findByNomEtat(Type_Etat_AffP e);
}
