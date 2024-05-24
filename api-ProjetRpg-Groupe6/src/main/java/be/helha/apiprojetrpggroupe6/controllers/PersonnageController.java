package be.helha.apiprojetrpggroupe6.controllers;

import be.helha.apiprojetrpggroupe6.Models.DTO.PersonnageDTO;
import be.helha.apiprojetrpggroupe6.Models.Personnage;
import be.helha.apiprojetrpggroupe6.Services.PersonnageService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller de la classe Personnage
 * Permet de faire le lien entre les requêtes http et les services de la classe Personnage
 */
@RestController
@RequestMapping("/api")
public class PersonnageController {

    /**
     * Service de la classe Personnage
     */
    private PersonnageService personnageService = new PersonnageService();

    /**
     * Récupère tous les personnages de la base de donénes
     * Endpoint : /api/personnages
     * @return Renvoi la liste des personnages
     * @return String Renvoi un message d'erreur
     *
     */
    @GetMapping("/personnages")
    public ResponseEntity<?> getPersonnages(){
        try {
            List<PersonnageDTO> personnages = personnageService.getPersonnages();
            return new ResponseEntity<List<PersonnageDTO>>(personnages, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Récupère un personnage par son id
     * Endpoint : /api/personnages/1
     * @param id id du personnage
     * @return Personnage Renvoi le personnage correspondant à l'id
     * @return String Renvoi un message d'erreur
     */
    @GetMapping("/personnages/{id}")
    public ResponseEntity<?> getPersonnageById(@PathVariable("id") int id){
        try {
            Personnage personnage = personnageService.getPersonnageById(id);
            return new ResponseEntity<Personnage>(personnage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Ajoute un personnage à la base de données
     * Endpoint : /api/personnages
     * @param personnage Personnage à ajouter
     * @return Personnage Renvoi le personnage ajouté
     * @return String Renvoi un message d'erreur
     */
    @PostMapping("/personnages")
    public ResponseEntity<?> addPersonnage(@RequestBody Personnage personnage){
        try {
            Personnage personnage1 = personnageService.addPersonnage(personnage);
            return new ResponseEntity<Personnage>(personnage1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Met à jour un personnage
     * Endpoint : /api/personnages
     * @param personnage Personnage à mettre à jour
     * @return int Renvoi le nombre de lignes modifiées
     * @return String Renvoi un message d'erreur
     */
    @PutMapping("/personnages")
    public ResponseEntity<?> updatePersonnage(@RequestBody Personnage personnage){
        try {
            int result = personnageService.updatePersonnage(personnage);
            return new ResponseEntity<Integer>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Supprime un personnage
     * Endpoint : /api/personnages/1
     * @param id id du personnage à supprimer
     * @return int Renvoi le nombre de lignes supprimées
     * @return String Renvoi un message d'erreur
     */
    @DeleteMapping("/personnages/{id}")
    public ResponseEntity<?> deletePersonnage(@PathVariable("id") int id){
        try {
            int result = personnageService.deletePersonnage(id);
            return new ResponseEntity<Integer>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
