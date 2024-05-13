package be.helha.apiprojetrpggroupe6.controllers;


import be.helha.apiprojetrpggroupe6.Models.Arme;
import be.helha.apiprojetrpggroupe6.Services.ArmeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<Arme>> getArmes(){
        List<Arme> armes = armeService.getArmes();
        return new ResponseEntity<List<Arme>>(armes, HttpStatus.OK);
    }
}
