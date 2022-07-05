package aston.correction.controller;

import aston.correction.entity.Adresse;
import aston.correction.entity.Formateur;
import aston.correction.entity.Personne;
import aston.correction.entity.Stagiaire;
import aston.correction.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/personne")
public class PersonneController {

    @Autowired
    private PersonneService personneService;

    @GetMapping("")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Personne> findAll(){
        return personneService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Personne findById(@PathVariable Long id) {
        return personneService.findById(id);
    };

    @GetMapping("/ville/{ville}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Personne> findAllByVille(@PathVariable String ville){
        return personneService.findAllByVille(ville);
    }

    @GetMapping("/nom/{nom}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Formateur> findAllFormateurByMatiere(@PathVariable String nom){
        return personneService.findAllFormateurByMatiere(nom);
    }

    @GetMapping("/formateur")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Formateur> findFormateur(){
        return personneService.findFormateur();
    }

    @GetMapping("/stagiaire")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Stagiaire> findAllStagiaire(){
        return personneService.findAllStagiaire();
    }

    @GetMapping("/stagiaire/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Formateur findByStagiaire (@PathVariable Long id) {
        return personneService.findByStagiaire(id);
    }
    @GetMapping("/formateur/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Stagiaire> findAllStagiaireByFormateur (@PathVariable Long id) {
        return personneService.findAllStagiaireByFormateur(id);
    }
    @GetMapping("/experience/{experience}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Formateur> findAllFormateurExperienceGreaterThan (@PathVariable int experience) {
        return personneService.findAllFormateurExperienceGreaterThan(experience);
    }

    @PostMapping("/formateur")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Personne create(@RequestBody Formateur newFormateur) {
        return personneService.create(newFormateur);
    };


    @PostMapping("/stagiaire")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Personne create(@RequestBody Stagiaire newStagiaire) {
        return personneService.create(newStagiaire);
    };

    @PostMapping("/formateur/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Formateur update(@RequestBody Formateur formateur,@PathVariable Long id) {
        if (!id.equals(formateur.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "mauvaise formateur a mettre à jour");
        }
        return (Formateur) personneService.update(formateur);
    }

    @PostMapping("/stagiaire/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Stagiaire update(@RequestBody Stagiaire stagiaire,@PathVariable Long id) {
        if (!id.equals(stagiaire.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "mauvaise stagiaire a mettre à jour");
        }
        return (Stagiaire) personneService.update(stagiaire);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code=HttpStatus.ACCEPTED)
    public Personne delete(@PathVariable Long id) {
        return personneService.delete(id);
    }

}
