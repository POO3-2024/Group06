package be.helha.apiprojetrpggroupe6.controllers;


import be.helha.apiprojetrpggroupe6.Models.Arme;
import be.helha.apiprojetrpggroupe6.Models.DTO.ArmeDTO;
import be.helha.apiprojetrpggroupe6.Services.ArmeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Controller de la classe Arme
 * Permet de faire le lien entre les requêtes http et les services de la classe Arme
 */
@RestController
@RequestMapping("/api")
public class ArmeController {

    /**
     * Service de la classe Arme
     */
    private ArmeService armeService = new ArmeService();

    /**
     * Récupère toutes les armes de la base de données
     * Endpoint : /api/armes
     * @return Renvoi la liste des armes
     * @return String Renvoi un message d'erreur
     */
    @GetMapping("/armes")
    public ResponseEntity<?> getArmes(){
        try {
            List<ArmeDTO> armes = armeService.getArmes();
            return new ResponseEntity<List<ArmeDTO>>(armes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>( e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Récupère une arme par son id
     * Endpoint : /api/armes/1
     * @param id id de l'arme
     * @return Arme Renvoi l'arme correspondant à l'id
     * @return String Renvoi un message d'erreur
     */
    @GetMapping("/armes/{id}")
    public ResponseEntity<?> getArmeById(@PathVariable("id") int id){
        try {
            Arme arme = armeService.getArmeById(id);
            return new ResponseEntity<Arme>(arme, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Ajoute une arme à la base de données
     * Endpoint : /api/armes
     * @param arme Arme à ajouter
     * @return Arme Renvoi l'arme ajoutée
     * @return String Renvoi un message d'erreur
     */
    @PostMapping("/armes")
    public ResponseEntity<?> addArme(@RequestBody Arme arme){
        try {
            Arme arme1 = armeService.addArme(arme);
            return new ResponseEntity<Arme>(arme1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Met à jour une arme
     * Endpoint : /api/armes
     * @param arme Arme à mettre à jour
     * @return int Renvoi le nombre de lignes modifiées
     * @return String Renvoi un message d'erreur
     */
    @PutMapping("/armes")
    public ResponseEntity<?> updateArme(@RequestBody Arme arme) {
        Arme armeModif = new Arme(arme.getId(),arme.getNom(),arme.getDegats());
        try {

            int result = armeService.updateArme(armeModif);
            return new ResponseEntity<Integer>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),  HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * Supprime une arme
     * Endpoint : /api/armes/1
     * @param id id de l'arme à supprimer
     * @return int Renvoi le nombre de lignes supprimées
     * @return String Renvoi un message d'erreur
     */
    @DeleteMapping("/armes/{id}")
    public ResponseEntity<?> deleteArme(@PathVariable("id") int id) {
        try {
            int result = armeService.deleteArme(id);
            return new ResponseEntity<Integer>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
