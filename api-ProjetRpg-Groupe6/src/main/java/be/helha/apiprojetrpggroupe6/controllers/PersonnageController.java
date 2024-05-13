package be.helha.apiprojetrpggroupe6.controllers;

import be.helha.apiprojetrpggroupe6.Models.Personnage;
import be.helha.apiprojetrpggroupe6.Services.PersonnageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonnageController {

    private PersonnageService personnageService = new PersonnageService();

    @GetMapping("/personnages")
    public ResponseEntity<List<Personnage>> getPersonnages(){
        List<Personnage> personnages = personnageService.getPersonnages();
        return new ResponseEntity<List<Personnage>>(personnages, HttpStatus.OK);
    }
}
