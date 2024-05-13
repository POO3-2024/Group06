package be.helha.apiprojetrpggroupe6.Services;

import be.helha.apiprojetrpggroupe6.Models.DTO.PersonnageDTO;
import be.helha.apiprojetrpggroupe6.Models.Personnage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service de gestion de personnage
 */
@Service
public class PersonnageService {

    /**
     * Récupère tous les personnages de la base de données
     * Renvoi uniquement l'id et le nom des personnages
     * @return List<PersonnageDTO> Renvoi la liste des personnages
     */
    public List<PersonnageDTO>getPersonnages(){
        List<PersonnageDTO> list = new ArrayList<>();
        list.add(new PersonnageDTO(1, "Gandalf"));
        list.add(new PersonnageDTO(2, "Gimli"));
        list.add(new PersonnageDTO(3, "Legolas"));
        return list;
    }
    /**
     * Récupère un personnage par son id
     * @param id id du personnage
     * @return Personnage Renvoi le personnage correspondant à l'id
     */
    public Personnage getPersonnageById(int id){
        return new Personnage(id, "Gandalf", 100, 1000);
    }
    /**
     * Ajoute un personnage à la base de données
     * @param personnage Personnage à ajouter
     * @return Personnage Renvoi le personnage ajouté
     */
    public Personnage addPersonnage(Personnage personnage){
        return personnage;
    }
    /**
     * Met à jour un personnage
     * @param personnage Personnage à mettre à jour
     * @return int Renvoi le nombre de lignes modifiées
     */
    public int updatePersonnage(Personnage personnage){
        return 1;
    }
    /**
     * Supprime un personnage
     * @param id id du personnage à supprimer
     * @return int Renvoi le nombre de lignes supprimées
     */
    public int deletePersonnage(int id){
        return 1;
    }
}
