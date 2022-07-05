package aston.correction.controller;

import aston.correction.entity.Difficulte;
import aston.correction.entity.Matiere;
import aston.correction.service.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/matiere")
public class MatiereController {
    @Autowired
    private MatiereService matiereService;

    @GetMapping("")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Matiere> findAll(){
        return matiereService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Matiere findById(@PathVariable Long id) {
        return matiereService.findById(id);
    };

    @GetMapping("/difficulte/{difficulte}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Matiere> findAllByDifficulte(@PathVariable Difficulte difficulte){
        return matiereService.findAllByDifficulte(difficulte);
    };

    @PostMapping("")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Matiere create(@RequestBody Matiere newMatiere) {
        return this.matiereService.create(newMatiere);
    };

    @PostMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Matiere update(@RequestBody Matiere matiere,@PathVariable Long id) {
        if (!id.equals(matiere.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "mauvaise matiere a mettre à jour");
        }
        return matiereService.update(matiere);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code=HttpStatus.ACCEPTED)
    public Matiere delete(@PathVariable Long id) {
        return matiereService.delete(id);
    }

}
