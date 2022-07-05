package aston.correction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import aston.correction.entity.Formateur;
import aston.correction.entity.Personne;
import aston.correction.entity.Stagiaire;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonneRepo extends JpaRepository<Personne, Long> {
	
	List<Personne> findByNom(String nom);
	
	@Query("select p from Personne p where p.adresse.ville = :ville")
	List<Personne> findAllByVille(@Param("ville") String ville);

	@Query("select f from Formateur f left join f.competences c where c.nom = :nom")
	List<Formateur> findAllFormateurByMatiere(@Param("nom") String nom);

	@Query("select f from Formateur f")
	List<Formateur> findFormateur();

	@Query("select s from Stagiaire s")
	List<Stagiaire> findAllStagiaire();

	@Query("select s.formateur from Stagiaire s where s.id = :id")
	Formateur findByStagiaire(@Param("id") Long id);

	@Query("select s from Stagiaire s join s.formateur f where f.id = :id")
	List<Stagiaire> findAllStagiaireByFormateur(@Param("id") Long id);

	@Query("select f from Formateur f where f.experience > :experience")
	List<Formateur> findAllFormateurExperienceGreaterThan(@Param("experience") int experience);
	
	
	

}
