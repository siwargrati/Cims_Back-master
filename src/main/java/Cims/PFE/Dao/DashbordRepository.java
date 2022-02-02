package Cims.PFE.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import Cims.PFE.Entities.Dashbord;


@CrossOrigin("*")
@RepositoryRestResource
public interface DashbordRepository extends JpaRepository<Dashbord,Long> {
@Query(value="SELECT count(id_affectation_p) as axeX, date_debut as axeY FROM public.affectation_partielle WHERE id_etat=2 GROUP BY date_debut;",nativeQuery = true)
List<Dashbord>DashAffpardate();
}
