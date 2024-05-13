package be.helha.apiprojetrpggroupe6.Services;

import be.helha.apiprojetrpggroupe6.Models.DTO.PersonnageDTO;
import be.helha.apiprojetrpggroupe6.Models.Personnage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonnageService {

    public List<PersonnageDTO>getPersonnages(){
        List<PersonnageDTO> list = new ArrayList<>();
        list.add(new PersonnageDTO(1, "Gandalf"));
        list.add(new PersonnageDTO(2, "Gimli"));
        list.add(new PersonnageDTO(3, "Legolas"));
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
    public int deletePersonnage(int id){
        return 1;
    }
}
