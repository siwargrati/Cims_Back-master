package Cims.PFE.Dao;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import Cims.PFE.Entities.AffectationPartielle;
import Cims.PFE.Entities.DashGouv;
import Cims.PFE.Entities.Dashboard;
import Cims.PFE.Entities.Dashbord;
import Cims.PFE.Entities.Etat_AffectationP;
import Cims.PFE.Entities.OrdreAffectationP;
import Cims.PFE.Entities.Personnel;
import Cims.PFE.Entities.Site;
@CrossOrigin("*")
@Transactional
@RepositoryRestResource
public interface AffectationPartielleRepository extends JpaRepository<AffectationPartielle, Long> {
@Query(value="SELECT * from affectation_partielle   where id_personnel=?1 and id_etat=2",nativeQuery = true)
List<AffectationPartielle> getAffectation(Long id_personnel);

List<AffectationPartielle> findByEtat(Etat_AffectationP e);

@Query(value="SELECT * from affectation_partielle   where id_personnel=?1 ",nativeQuery = true)
List<AffectationPartielle> getAffectPartielle(Long id_personnel);

@Query(value="select count(id_affectation_p) as axeX,date_trunc ('months', date_debut ) as axeY  from public.affectation_partielle WHERE id_etat=2 GROUP BY date_trunc ('months', date_debut ) ORDER BY date_trunc ('months', date_debut)",nativeQuery = true)
List<Dashboard>DashAffpardate();

@Query(value="SELECT count(id_affectation_p) from public.affectation_partielle where id_etat=2",nativeQuery = true)
Long getNbrAffectValidee();

@Query(value="SELECT count(id_affectation_p) from public.affectation_partielle where id_etat=3",nativeQuery = true)
Long getNbrAffectRefusee();

@Query(value="SELECT count(id_affectation_p) as X ,gouvernorat.nom_gouv as Y FROM public.affectation_partielle  join site  using (id_site) natural join gouvernorat where id_etat=2  group by gouvernorat.nom_gouv ; ",nativeQuery = true)
List<DashGouv>DashAffparGouv();

boolean existsByPersonnel(Personnel personnel);

@Query(value="SELECT distinct  jour FROM public.affectation_partielle_jour join affectation_partielle on affectation_partielle_jour.affectation_partielle_id_affectation_p= affectation_partielle.id_affectation_p join personnel using(id_personnel) where id_personnel=?1 and date_debut=?2 or date_fin=?3 ",nativeQuery = true)
List<String> getJours(Long id_personnel,LocalDate dateDebut,LocalDate dateFin);

@Query(value="SELECT * from affectation_partielle   where id_site=?1 ",nativeQuery = true)
List<AffectationPartielle> getSite(Long id_site);

@Query(value="SELECT * from affectation_partielle   where id_site=?1 and id_personnel=?2 ",nativeQuery = true)
List<AffectationPartielle> getSiteParPersonnel(Long id_site, Long id_personnel, Long id_struct);

	@Query(value="SELECT * from affectation_partielle where id_affectation_p=?1 ",nativeQuery = true)
	AffectationPartielle getAffectationP(Long idAffect);
}
