package be.helha.apiprojetrpggroupe6.controllers;

import be.helha.apiprojetrpggroupe6.Models.Arme;
import be.helha.apiprojetrpggroupe6.Models.Personnage;
import be.helha.apiprojetrpggroupe6.Services.ArmeService;
import be.helha.apiprojetrpggroupe6.Services.AttaqueService;
import be.helha.apiprojetrpggroupe6.Services.PersonnageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller servant à gérer les attaques
 *
 */
@RestController
@RequestMapping("/api")
public class AttaqueController {

    /**
     * Service de la classe Attaque
     */
    private AttaqueService attaqueService = new AttaqueService();

    /**
     * Attaque un personnage avec une arme
     * Endpoint : /api/attaques/1/1
     * @param id_personnage id du personnage
     * @param id_arme id de l'arme
     * @return Personnage Renvoi le personnage attaqué
     * @return String Renvoi un message d'erreur
     */
    @GetMapping("/attaques/{id_personnage}/{id_arme}")
    public ResponseEntity<?> attaquer(@PathVariable("id_personnage") int id_personnage,@PathVariable("id_arme") int id_arme){
        try {
            Personnage personnageAttaque = this.attaqueService.attaquer(id_personnage, id_arme);
            return new ResponseEntity<Personnage>(personnageAttaque, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
