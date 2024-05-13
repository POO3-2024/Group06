package be.helha.apiprojetrpggroupe6.Services;

import be.helha.apiprojetrpggroupe6.Models.Personnage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonnageService {

    public List<Personnage>getPersonnages(){
        List<Personnage> list = new ArrayList<>();
        list.add(new Personnage(1, "Gandalf", 100, 1000));
        list.add(new Personnage(2, "Gimli", 50, 500));
        list.add(new Personnage(3, "Legolas", 75, 750));
        return list;
    }
    public Personnage getPersonnageById(int id){
        return new Personnage(id, "Gandalf", 100, 1000);
    }
    public Personnage addPersonnage(Personnage personnage){
        return personnage;
    }
    public int updatePersonnage(Personnage personnage){
        return 1;
    }
}
