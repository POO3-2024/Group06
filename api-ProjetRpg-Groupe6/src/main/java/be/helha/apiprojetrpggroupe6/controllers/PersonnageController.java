package be.helha.apiprojetrpggroupe6.controllers;

import be.helha.apiprojetrpggroupe6.Models.DTO.PersonnageDTO;
import be.helha.apiprojetrpggroupe6.Models.Personnage;
import be.helha.apiprojetrpggroupe6.Services.PersonnageService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonnageController {

    private PersonnageService personnageService = new PersonnageService();

    /**
     * Récupère tous les personnages de la base de donénes
     * @return List<Personnage>
     */
    @GetMapping("/personnages")
    public ResponseEntity<List<PersonnageDTO>> getPersonnages(){
        List<PersonnageDTO> personnages = personnageService.getPersonnages();
        return new ResponseEntity<List<PersonnageDTO>>(personnages, HttpStatus.OK);
    }

    /**
     * Récupère un personnage par son id
     * @param id id du personnage
     * @return Personnage Renvoi le personnage correspondant à l'id
     */
    @GetMapping("/personnages/{id}")
    public ResponseEntity<Personnage> getPersonnageById(@PathVariable int id){
        Personnage personnage = personnageService.getPersonnageById(id);
        return new ResponseEntity<Personnage>(personnage, HttpStatus.OK);
    }
    /**
     * Ajoute un personnage à la base de données
     * @param personnage Personnage à ajouter
     * @return Personnage Renvoi le personnage ajouté
     */
    @PostMapping("/personnages")
    public ResponseEntity<Personnage> addPersonnage(@RequestBody Personnage personnage){
        Personnage personnage1 = personnageService.addPersonnage(personnage);
        return new ResponseEntity<Personnage>(personnage1, HttpStatus.CREATED);
    }
    /**
     * Met à jour un personnage
     * @param personnage Personnage à mettre à jour
     * @return int Renvoi le nombre de lignes modifiées
     */
    @PutMapping("/personnages")
    public ResponseEntity<Integer> updatePersonnage(@RequestBody Personnage personnage){
        int result = personnageService.updatePersonnage(personnage);
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }
    /**
     * Supprime un personnage
     * @param id id du personnage à supprimer
     * @return int Renvoi le nombre de lignes supprimées
     */
    @DeleteMapping("/personnages/{id}")
    public ResponseEntity<Integer> deletePersonnage(@PathVariable int id){
        int result = personnageService.deletePersonnage(id);
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }
}
