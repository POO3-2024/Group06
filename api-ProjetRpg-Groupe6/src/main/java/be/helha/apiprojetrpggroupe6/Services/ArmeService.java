package be.helha.apiprojetrpggroupe6.Services;

import be.helha.apiprojetrpggroupe6.Database.ArmeDatabase;
import be.helha.apiprojetrpggroupe6.Models.Arme;
import be.helha.apiprojetrpggroupe6.Models.DTO.ArmeDTO;
import be.helha.apiprojetrpggroupe6.dbConnection.ConnectionDB;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Service de gestion d'arme
 */
@Service
public class ArmeService {


    private ArmeDatabase armeDatabase;

    /**
     * Constructeur pour ArmeService avec une connexion par défaut
     */
    public ArmeService() {
        this.armeDatabase = new ArmeDatabase(ConnectionDB.getConnection());
    }

    /**
     * Constructeur pour ArmeService avec une connexion pour les tests
     * @param test : Détermine si la base de données est utilisée pour les tests ou la production
     */
    public ArmeService(boolean test) {
        this.armeDatabase = new ArmeDatabase(ConnectionDB.getConnection(true));
    }

    /**
     * Récupère toutes les armes de la base de données
     * Renvoi uniquement l'id et le nom des armes
     * @return Renvoi la liste des armes
     */
    public List<ArmeDTO> getArmes() throws Exception{
        List<ArmeDTO> list = new ArrayList<>();
        list = armeDatabase.getArme();
        return list;
    }
    /**
     * Récupère une arme par son id
     * @param id id de l'arme
     * @return Arme Renvoi l'arme correspondant à l'id
     */
    public Arme getArmeById(int id) throws Exception {
        return this.armeDatabase.getArmeByID(id);
    }
    /**
     * Ajoute une arme à la base de données
     * @param arme Arme à ajouter
     * @return Arme Renvoi l'arme ajoutée
     */
    public Arme addArme(Arme arme) throws Exception{
        return this.armeDatabase.addArme(arme);
    }
    /**
     * Met à jour une arme
     * @param arme Arme à mettre à jour
     * @return int Renvoi le nombre de lignes modifiées
     */
    public int updateArme(Arme arme) throws Exception{
      List<ArmeDTO> list = getArmes();
      boolean verifDejaExist = false;
      for(ArmeDTO armeVerif : list){
          if(armeVerif.getNom().equals(arme.getNom()) && armeVerif.getId() != arme.getId()){
              verifDejaExist = true;
          }
      }
       if(!verifDejaExist){
              return this.armeDatabase.updateArme(arme);
           }else{
                throw new SQLException("Arme déjà existante");
         }

    }
    /**
     * Supprime une arme
     * @param id id de l'arme à supprimer
     * @return int Renvoi le nombre de lignes supprimées
     */
    public int deleteArme(int id)  throws Exception{
        return this.armeDatabase.deleteArme(Optional.of(id));
    }
}
