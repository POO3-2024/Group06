package be.helha.apiprojetrpggroupe6.controllers;


import be.helha.apiprojetrpggroupe6.Models.Arme;
import be.helha.apiprojetrpggroupe6.Models.DTO.ArmeDTO;
import be.helha.apiprojetrpggroupe6.Services.ArmeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ArmeController {

    private ArmeService armeService = new ArmeService();

    /**
     * Récupère toutes les armes de la base de données
     * @return List<Arme> Renvoi la liste des armes
     */
    @GetMapping("/armes")
    public ResponseEntity<List<ArmeDTO>> getArmes(){
        List<ArmeDTO> armes = armeService.getArmes();
        return new ResponseEntity<List<ArmeDTO>>(armes, HttpStatus.OK);
    }
    /**
     * Récupère une arme par son id
     * @param id id de l'arme
     * @return Arme Renvoi l'arme correspondant à l'id
     */
    @GetMapping("/armes/{id}")
    public ResponseEntity<Arme> getArmeById(@PathVariable int id){
        Arme arme = armeService.getArmeById(id);
        return new ResponseEntity<Arme>(arme, HttpStatus.OK);
    }
    /**
     * Ajoute une arme à la base de données
     * @param arme Arme à ajouter
     * @return Arme Renvoi l'arme ajoutée
     */
    @PostMapping("/armes")
    public ResponseEntity<Arme> addArme(@RequestBody Arme arme){
        Arme arme1 = armeService.addArme(arme);
        return new ResponseEntity<Arme>(arme1, HttpStatus.CREATED);
    }
    /**
     * Met à jour une arme
     * @param arme Arme à mettre à jour
     * @return int Renvoi le nombre de lignes modifiées
     */
    @PutMapping("/armes")
    public ResponseEntity<Integer> updateArme(@RequestBody Arme arme) {
        int result = armeService.updateArme(arme);
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }
    /**
     * Supprime une arme
     * @param id id de l'arme à supprimer
     * @return int Renvoi le nombre de lignes supprimées
     */
    @DeleteMapping("/armes/{id}")
    public ResponseEntity<Integer> deleteArme(@PathVariable int id) {
        int result = armeService.deleteArme(id);
        return new ResponseEntity<Integer>(result, HttpStatus.OK);
    }
}
