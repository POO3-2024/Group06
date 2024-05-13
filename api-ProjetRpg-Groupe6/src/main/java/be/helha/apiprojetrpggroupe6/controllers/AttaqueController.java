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

    private PersonnageService personnageService = new PersonnageService();
    private ArmeService armeService = new ArmeService();
    private AttaqueService attaqueService = new AttaqueService();

    /**
     * Attaque un personnage avec une arme
     * @param id_personnage id du personnage
     * @param id_arme id de l'arme
     * @return Personnage Renvoi le personnage attaqué
     */
    @GetMapping("/attaques/{id_personnage}/{id_arme}")
    public ResponseEntity<Personnage> attaquer(@PathVariable int id_personnage,@PathVariable int id_arme){
        Personnage personnage = this.personnageService.getPersonnageById(id_personnage);
        Arme arme = this.armeService.getArmeById(id_arme);

        Personnage personnageAttaque = this.attaqueService.attaquer(personnage, arme);

        return new ResponseEntity<Personnage>(personnageAttaque, HttpStatus.OK);
    }
}
