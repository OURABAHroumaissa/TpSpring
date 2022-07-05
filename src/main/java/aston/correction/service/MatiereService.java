package aston.correction.service;

import aston.correction.entity.Adresse;
import aston.correction.entity.Difficulte;
import aston.correction.entity.Matiere;
import aston.correction.repository.MatiereRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MatiereService {

    @Autowired
    private MatiereRepo matiereRepo;


    public List<Matiere> findAll(){
        return matiereRepo.findAll();
    }

    public Matiere findById(Long id) {
        Optional<Matiere> optMatiere = matiereRepo.findById(id);
        if (optMatiere.isPresent()) {
            return optMatiere.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }



    public List<Matiere> findAllByDifficulte (Difficulte difficulte) {
        return matiereRepo.findAllByDifficulte(difficulte);
    }




    public Matiere create(Matiere newMatiere) {
        return matiereRepo.save(newMatiere);
    }


    public Matiere update(Matiere matiere) {
        if (! matiereRepo.existsById(matiere.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "impossible de trouver l matiere a mettre à jour");
        }
        return matiereRepo.save(matiere);
    }


    public Matiere delete (Long id) {
        if (! matiereRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "impossible de trouver la guitare à supprimer");
        }
        Matiere matiereDel = findById(id);
        matiereRepo.deleteById(id);
        if (matiereRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,
                    "erreur lors de la suppressiion de la guitare");
        }

        return matiereDel;
    }
}
