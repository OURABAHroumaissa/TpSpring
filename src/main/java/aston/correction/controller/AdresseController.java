package aston.correction.controller;

import aston.correction.entity.Adresse;
import aston.correction.entity.Difficulte;
import aston.correction.entity.Matiere;
import aston.correction.service.AdresseService;
import aston.correction.service.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/adresse")
public class AdresseController {
    @Autowired
    private AdresseService adresseService;

    @GetMapping("")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Adresse> findAll(){
        return adresseService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Adresse findById(@PathVariable Long id) {
        return adresseService.findById(id);
    };

    @GetMapping("/ville/{ville}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Adresse> findByVille(@PathVariable String ville){
        return adresseService.findByVille(ville);
    }

    @GetMapping("/codePostal/{codePostal}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Adresse> findByCodePostal(@PathVariable String codePostal){
        return adresseService.findByCodePostal(codePostal);
    }

    @GetMapping("/ville/{ville}/codePostal/{codePostal}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Adresse> findByVilleOrCodePostal(@PathVariable String ville,@PathVariable String codePostal){
        return adresseService.findByVilleOrCodePostal(ville,codePostal);
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Adresse findByPersonne(@PathVariable Long id){
        return adresseService.findByPersonne(id);
    }
    
    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Adresse create(@RequestBody Adresse newAdresse) {
        return this.adresseService.create(newAdresse);
    };

    @PostMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Adresse update(@RequestBody Adresse adresse,@PathVariable Long id) {
        if (!id.equals(adresse.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "mauvaise matiere a mettre à jour");
        }
        return adresseService.update(adresse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code=HttpStatus.ACCEPTED)
    public Adresse delete(@PathVariable Long id) {
        return adresseService.delete(id);
    }
}
