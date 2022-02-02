package Cims.PFE.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import Cims.PFE.Entities.Gouvernorat;

@CrossOrigin("*")
@RepositoryRestResource
public interface GouvernoratRepository extends JpaRepository<Gouvernorat,Long>{
 

}
