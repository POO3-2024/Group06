package be.helha.apiprojetrpggroupe6.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller qui gère les erreurs 404
 * Permet de renvoyer une erreur 404 lorsqu'une url n'existe pas
 */
@RestController
@RequestMapping("/api")
public class NotFoundController {

    /**
     * Renvoi une erreur 404 lorsque l'url n'existe pas
     * @return String Renvoi une erreur 404
     */
    @RequestMapping("/**")
    public ResponseEntity<String> notFound(){
        return new ResponseEntity<String>("Cette page n'existe pas", HttpStatus.NOT_FOUND);
    }
}
