package aston.correction.service;

import aston.correction.entity.*;
import aston.correction.repository.PersonneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PersonneService {
    @Autowired
    private PersonneRepo personneRepo;


    public List<Personne> findAll(){
        return personneRepo.findAll();
    }

    public Personne findById(Long id) {
        Optional<Personne> optPersonne = personneRepo.findById(id);
        if (optPersonne.isPresent()) {
            return optPersonne.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<Personne> findAllByVille (String ville) {
        return personneRepo.findAllByVille(ville);
    }

    public List<Formateur> findAllFormateurByMatiere (String nom) {
        return personneRepo.findAllFormateurByMatiere(nom);
    }

    public List<Formateur> findFormateur () {
        return personneRepo.findFormateur();
    }

    public List<Stagiaire> findAllStagiaire () {
        return personneRepo.findAllStagiaire();
    }

    public Formateur findByStagiaire (Long id) {
        return personneRepo.findByStagiaire(id);
    }

    public List<Stagiaire> findAllStagiaireByFormateur (Long id) {
        return personneRepo.findAllStagiaireByFormateur(id);
    }

    public List<Formateur> findAllFormateurExperienceGreaterThan (int experience) {
        return personneRepo.findAllFormateurExperienceGreaterThan(experience);
    }

    public Personne create(Formateur newFormateur) {
        return personneRepo.save(newFormateur);
    }

    public Personne create(Stagiaire newStagiaire) {
        return personneRepo.save(newStagiaire);
    }


    public Personne update(Formateur formateur) {
        if (! personneRepo.existsById(formateur.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "impossible de trouver l matiere a mettre à jour");
        }
        return personneRepo.save(formateur);
    }

    public Personne update(Stagiaire stagiaire) {
        if (! personneRepo.existsById(stagiaire.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "impossible de trouver l matiere a mettre à jour");
        }
        return personneRepo.save(stagiaire);
    }


    public Personne delete (Long id) {
        if (! personneRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "impossible de trouver la guitare à supprimer");
        }
        Personne formateurDel = findById(id);
        personneRepo.deleteById(id);
        if (personneRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,
                    "erreur lors de la suppressiion de la guitare");
        }

        return formateurDel;
    }


}
