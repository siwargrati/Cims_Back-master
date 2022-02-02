package Cims.PFE.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import Cims.PFE.Entities.Gouvernorat;
import Cims.PFE.Entities.Site;

@CrossOrigin("*")
@RepositoryRestResource
public interface SiteRepository  extends JpaRepository<Site,Long>{
@Query(value="SELECT * from site join gouvernorat using(id_gouv) where id_gouv=?1",nativeQuery = true)
List<Site> getss(Long id_gouv);

@Query(value="SELECT id_site from affectation_totale  where id_site=?1",nativeQuery = true)
List<Long> existsAffTotale(Long id);


@Query(value="SELECT * from site  where id_gouv=?1 and nom_etablissement_fr=?2 and nom_etablissement_ar=?3 and nature_etablissement_fr=?4 and nature_etablissement_ar=?5 and qualite_direction_fr=?6 and qualite_direction_ar=?7 ",nativeQuery = true)
List<Site> getSite(Long id_gouv, String nomfr, String nomar, String naturefr, String naturear, String qualitefr, String qualitear);

}
