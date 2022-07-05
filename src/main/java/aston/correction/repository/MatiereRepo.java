package aston.correction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import aston.correction.entity.Difficulte;
import aston.correction.entity.Matiere;
import org.springframework.stereotype.Repository;

@Repository
public interface MatiereRepo extends JpaRepository<Matiere, Long> {
	// par @NamedQuery
	List<Matiere> findAllByDifficulte(@Param("dif") Difficulte difficulte); 

}
