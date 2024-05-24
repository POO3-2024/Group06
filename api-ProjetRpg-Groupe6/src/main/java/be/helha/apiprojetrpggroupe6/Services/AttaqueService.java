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
     * Services nécessaires pour attaquer
     */
    private PersonnageService personnageService = new PersonnageService();
    private ArmeService armeService = new ArmeService();
    /**
     * Attaque un personnage avec une arme
     * @param id_personnage Personnage à attaquer
     * @param id_arme Arme avec laquelle attaquer
     * @return perso Personnage attaqué
     */
    public Personnage attaquer(int id_personnage, int id_arme) throws Exception {
        Personnage perso = personnageService.getPersonnageById(id_personnage);
        Arme arme = armeService.getArmeById(id_arme);
        if (perso == null || arme == null) {
            throw new Exception("Personnage ou arme introuvable");
        }
        perso.infligerDegats(arme.getDegats());
        personnageService.updatePersonnage(perso);
        return perso;
    }
}
