package be.helha.apiprojetrpggroupe6.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NotFoundController {

    @RequestMapping("/**")
    public ResponseEntity<String> notFound(){
        return new ResponseEntity<String>("Cette page n'existe pas", HttpStatus.NOT_FOUND);
    }
}
