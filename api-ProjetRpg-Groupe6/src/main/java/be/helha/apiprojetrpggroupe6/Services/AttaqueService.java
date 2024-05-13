package be.helha.apiprojetrpggroupe6.Services;

import be.helha.apiprojetrpggroupe6.Models.Arme;
import be.helha.apiprojetrpggroupe6.Models.Personnage;
import org.springframework.stereotype.Service;

/**
 * Service servant à gérer les attaques
 */
@Service
public class AttaqueService {

    /**
     * Attaque un personnage avec une arme
     * @param personnage Personnage à attaquer
     * @param arme Arme avec laquelle attaquer
     * @return Personnage Personnage attaqué
     */
    public Personnage attaquer(Personnage personnage, Arme arme){
        personnage.infligerDegats(arme.getDegats());
        return personnage;
    }
}
