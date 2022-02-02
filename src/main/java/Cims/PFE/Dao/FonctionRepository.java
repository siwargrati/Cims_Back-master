package Cims.PFE.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import Cims.PFE.Entities.Fonction;

@CrossOrigin("*")
@RepositoryRestResource
public interface FonctionRepository  extends JpaRepository<Fonction,Long>{


@Query(value="SELECT * from fonction  where  nom_fonction=?1 and type_fonction=?2",nativeQuery = true)
List<Fonction> getFonction(String nomfn, String type);

}
