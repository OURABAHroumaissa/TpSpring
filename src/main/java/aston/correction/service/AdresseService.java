package aston.correction.service;

import aston.correction.entity.Adresse;
import aston.correction.repository.AdresseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AdresseService {
    @Autowired
    private AdresseRepo adresseRepo;

    /**
     * methode qui recupere du Repo les Adresse
     * @return List<Adresse>
     */
    public List<Adresse> findAll(){
        return adresseRepo.findAll();
    }

    /**
     * methode qui permet de recuperer une Adresse par son id
     * @param id Long, id de la Adresse recherchée
     * @return Adresse
     */
    public Adresse findById(Long id) {
        Optional<Adresse> optAdresse = adresseRepo.findById(id);
        if (optAdresse.isPresent()) {
            return optAdresse.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * methode qui permet de trouver des Adresse par ville
     * @param ville String, ville recherchées
     * @return List<Adresse>
     */
    public List<Adresse> findByVille (String ville) {
        return adresseRepo.findByVille(ville);
    }

    public List<Adresse> findByCodePostal (String codePostal) {
        return adresseRepo.findByCodePostal(codePostal);
    }


    public List<Adresse> findByVilleOrCodePostal (String ville, String codePostal) {
        return adresseRepo.findByVilleOrCodePostal(ville,codePostal);
    }

    public Adresse findByPersonne (Long id) {
        return adresseRepo.findByPersonne(id);
    }



    /**
     * methode qui permet d'enregistrer une Adresse
     * @param newAdresse Adresse, l Adresse a enregistrée
     * @return Adresse, la Adresse enregistrée avec son id
     */
    public Adresse create(Adresse newAdresse) {
        return adresseRepo.save(newAdresse);
    }


    public Adresse update(Adresse adresse) {
        if (! adresseRepo.existsById(adresse.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "impossible de trouver l adresse a mettre à jour");
        }
        return adresseRepo.save(adresse);
    }


    public Adresse delete (Long id) {
        if (! adresseRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "impossible de trouver la guitare à supprimer");
        }
        Adresse adresseDel = findById(id);
        adresseRepo.deleteById(id);
        if (adresseRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,
                    "erreur lors de la suppressiion de la guitare");
        }

        return adresseDel;
    }
}
