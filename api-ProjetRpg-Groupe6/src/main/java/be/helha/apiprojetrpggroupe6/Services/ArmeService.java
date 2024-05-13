package be.helha.apiprojetrpggroupe6.Services;

import be.helha.apiprojetrpggroupe6.Models.Arme;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArmeService {

    /**
     * Récupère toutes les armes de la base de données
     * @return List<Arme> Renvoi la liste des armes
     */
    public List<Arme> getArmes(){
        List<Arme> list = new ArrayList<>();
        list.add(new Arme(1, "Excalibur", 100));
        list.add(new Arme(2, "Hache", 50));
        list.add(new Arme(3, "Arc", 75));
        return list;
    }
    /**
     * Récupère une arme par son id
     * @param id id de l'arme
     * @return Arme Renvoi l'arme correspondant à l'id
     */
    public Arme getArmeById(int id){
        return new Arme(id, "Excalibur", 100);
    }
    /**
     * Ajoute une arme à la base de données
     * @param arme Arme à ajouter
     * @return Arme Renvoi l'arme ajoutée
     */
    public Arme addArme(Arme arme){
        return arme;
    }
    /**
     * Met à jour une arme
     * @param arme Arme à mettre à jour
     * @return int Renvoi le nombre de lignes modifiées
     */
    public int updateArme(Arme arme) {
        return 1;
    }
}
