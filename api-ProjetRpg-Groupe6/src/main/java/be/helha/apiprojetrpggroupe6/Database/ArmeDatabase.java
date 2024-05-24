package be.helha.apiprojetrpggroupe6.Database;


import be.helha.apiprojetrpggroupe6.Models.Arme;
import be.helha.apiprojetrpggroupe6.Models.DTO.ArmeDTO;
import be.helha.apiprojetrpggroupe6.dbConnection.ConnectionDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Cette classe fournit des méthodes pour interagir avec la base de données des armes.
 */
public class ArmeDatabase {


    private ConnectionDB connection;
    /**
     * Constructeur pour ArmeDatabase.
     *
     * @param connection la connexion à la base de données à utiliser.
     */
    public ArmeDatabase (ConnectionDB connection) {

        this.connection = connection;
    }
    /**
     * Récupère la liste de toutes les armes dans la base de données.
     *
     * @return une liste d'objets ArmeDTO.
     * @throws SQLException si une erreur de base de données survient.
     */
    public List<ArmeDTO> getArme() throws SQLException {

            List<ArmeDTO> list = new ArrayList<>();
            String query = "SELECT * FROM arme";

            try {
                ResultSet resultSet = connection.executeQuery(query);
                while(resultSet.next()){
                    int idArme = resultSet.getInt("Id_arme");
                    String nom = resultSet.getString("Nom");
                    ArmeDTO arme =  new ArmeDTO(idArme,nom);
                    list.add(arme);

                }
            }catch (Exception e){
                System.out.println("Aucune arme selectionnée");
            }

            return list;


    }

    /**
     * Récupère la liste de toutes les armes dans la base de données.
     *
     * @return un objets Arme.
     * @throws SQLException si une erreur de base de données survient.
     */
    public Arme getArmeByID(Integer id) throws SQLException {

        List<Arme> list = new ArrayList<>();
        String query = "SELECT * FROM arme WHERE Id_arme = '"+id+"' ";

        try {
            ResultSet resultSet = connection.executeQuery(query);
            while(resultSet.next()){
                int idArme = resultSet.getInt("Id_arme");
                String nom = resultSet.getString("Nom");
                int degats = resultSet.getInt("Degats");
                Arme arme =  new Arme(idArme,nom,degats);
                list.add(arme);

            }
        }catch (Exception e){
            System.out.println("Aucune selection faite");
        }

        return list.get(0);
    }
    /**
     * Récupère une arme par son nom.
     *
     * @return un objets Arme ou null si l'amre n'existe pas.
     * @throws SQLException si une erreur de base de données survient.
     */
    public Arme getArmeByName(String name) throws SQLException {

        List<Arme> list = new ArrayList<>();
        String query = "SELECT * FROM arme WHERE Nom = '"+name+"' ";

        try {
            ResultSet resultSet = connection.executeQuery(query);
            while(resultSet.next()){
                int idArme = resultSet.getInt("Id_arme");
                String nom = resultSet.getString("Nom");
                int degats = resultSet.getInt("Degats");
                Arme arme =  new Arme(idArme,nom,degats);
                list.add(arme);

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return list.get(0);
    }
    /**
     * Ajoute une nouvelle arme à la base de données.
     *
     * @param arme l'objet Arme à ajouter.
     * @throws SQLException si une erreur de base de données survient.
     * @throws Exception si l'arme voulant etre ajoutée existe déjà sur base de son nom
     */
    public Arme addArme(Arme arme) throws Exception {
        boolean verifArmeExist = false;
        List<ArmeDTO> listArme;
       
        if(!getArme().isEmpty()){
            listArme = getArme();
            for(ArmeDTO armeLoop : listArme){
                if (armeLoop.getNom().equals(arme.getNom())){
                    verifArmeExist = true;
                }
            }
        }


        if(!verifArmeExist){
            String query = "INSERT INTO arme (Nom, Degats) VALUES ('"+arme.getNom()+"','"+arme.getDegats()+"')";
            try {
                connection.executeUpdate(query);
                ResultSet res =  connection.executeQuery("select max(Id_arme) from arme");
                res.next();
                arme.setId(res.getInt(1));
                return arme;
            } catch (SQLException e) {
                System.err.println("SQL Exception during insert: " + e.getMessage());
                throw e;
            }
        }else {
            throw new Exception("Arme déjà existante");
        }

    }

    /**
     * Supprime une arme spécifique ou toutes les armes de la base de données.
     *
     * @param arme  un paramètre Optionnel contenant l'objet de type arme à supprimer.
     *             Si l'argument est vide, toutes les armes seront supprimées de la base de données.
     * @throws RuntimeException si une erreur de base de données survient.
     */
    public int deleteArme(Optional<Integer> arme){
        String query;
        if(arme.isPresent()){
            query = "DELETE FROM arme WHERE Id_arme = "+arme.get()+" ";
        }else {
            query = "DELETE FROM arme";
        }

        try {
           int result = connection.executeUpdate(query);
           return result;
        } catch (SQLException e) {
            throw new RuntimeException("Arme pas supprimée");
        }
    }

    /**
     * Modifie une arme dans la base de donnée
     *
     * @param arme l'objet arme modifié
     */
    public int updateArme(Arme arme) throws Exception {

        String query = "UPDATE arme SET Nom='"+arme.getNom()+"',degats="+arme.getDegats()+" WHERE Id_arme="+arme.getId()+"";


        try{
           int result = connection.executeUpdate(query);
           return result;
        }catch (SQLException e){
            throw new Exception("Modification annulée");
        }
    }

}
