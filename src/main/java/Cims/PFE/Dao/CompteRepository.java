package Cims.PFE.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Cims.PFE.Entities.Compte;
import Cims.PFE.Entities.Personnel;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {
	Optional<Compte> findByUsername(String username);

	Boolean existsByUsername(String username);
   Compte findByPassword(String password);
	Compte findByPersonnel(Personnel p);
}
