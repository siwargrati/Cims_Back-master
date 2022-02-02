package Cims.PFE.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import Cims.PFE.Entities.Structure;
@CrossOrigin("*")
@RepositoryRestResource
public interface StructureRepository extends JpaRepository<Structure,Long>{
	
	@Query(value="SELECT * from structure  where direction=?1 and direction_a=?2 and division=?3 and division_a=?4 and service=?5 and service_a=?6 ",nativeQuery = true)
	List<Structure> getStruct(String direction ,String direction_a , String division,String division_a, String service, String service_a);
}