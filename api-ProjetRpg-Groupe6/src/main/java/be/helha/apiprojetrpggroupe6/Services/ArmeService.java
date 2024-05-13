package be.helha.apiprojetrpggroupe6.Services;

import be.helha.apiprojetrpggroupe6.Models.Arme;
import be.helha.apiprojetrpggroupe6.Models.DTO.ArmeDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArmeService {

    /**
     * Récupère toutes les armes de la base de données
     * Renvoi uniquement l'id et le nom des armes
     * @return List<Arme> Renvoi la liste des armes
     */
    public List<ArmeDTO> getArmes(){
        List<ArmeDTO> list = new ArrayList<>();
        list.add(new ArmeDTO(1, "Excalibur"));
        list.add(new ArmeDTO(2, "Hache"));
        list.add(new ArmeDTO(3, "Arc"));
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
    /**
     * Supprime une arme
     * @param id id de l'arme à supprimer
     * @return int Renvoi le nombre de lignes supprimées
     */
    public int deleteArme(int id) {
        return 1;
    }
}
