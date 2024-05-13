package be.helha.apiprojetrpggroupe6.controllers;

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

    @GetMapping("/personnages")
    public ResponseEntity<List<Personnage>> getPersonnages(){
        List<Personnage> personnages = personnageService.getPersonnages();
        return new ResponseEntity<List<Personnage>>(personnages, HttpStatus.OK);
    }
    @GetMapping("/personnages/{id}")
    public ResponseEntity<Personnage> getPersonnageById(@PathVariable int id){
        Personnage personnage = personnageService.getPersonnageById(id);
        return new ResponseEntity<Personnage>(personnage, HttpStatus.OK);
    }
    @PostMapping("/personnages")
    public ResponseEntity<Personnage> addPersonnage(@RequestBody Personnage personnage){
        Personnage personnage1 = personnageService.addPersonnage(personnage);
        return new ResponseEntity<Personnage>(personnage1, HttpStatus.CREATED);
    }
}
