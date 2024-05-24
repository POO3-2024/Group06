package be.helha.apiprojetrpggroupe6.Services;

import be.helha.apiprojetrpggroupe6.Models.Arme;
import be.helha.apiprojetrpggroupe6.Models.Personnage;
import org.springframework.stereotype.Service;

/**
 * Service servant à gérer les attaques
 */
@Service
public class AttaqueService {


    private PersonnageService personnageService;
    private ArmeService armeService;

    /**
     * Constructeur par défaut
     */
    public AttaqueService() {
        this.personnageService = new PersonnageService();
        this.armeService = new ArmeService();
    }

    /**
     * Constructeur pour les tests
     * @param test true si test
     */
    public AttaqueService(boolean test) {
        this.personnageService = new PersonnageService(true);
        this.armeService = new ArmeService(true);
    }


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
