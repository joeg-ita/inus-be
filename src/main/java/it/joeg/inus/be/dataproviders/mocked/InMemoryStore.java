package it.joeg.inus.be.dataproviders.mocked;

import it.joeg.inus.be.domain.entities.Causes;
import it.joeg.inus.be.domain.entities.CausesType;
import it.joeg.inus.be.domain.entities.Punch;
import it.joeg.inus.be.domain.entities.Registration;
import it.joeg.inus.be.domain.entities.user.User;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bson.types.ObjectId;

/**
 *
 */
public class InMemoryStore {

    private static InMemoryStore instance;

    private InMemoryStore(){}
    
    public static InMemoryStore getInstance(){
        if(instance == null){
            instance = new InMemoryStore();
            populateCauses();
            populateUsers();
        }
        return instance;
    }
    
    private final static Map<String, Registration> REGISTRATION = new HashMap<>();
    
    private final static Map<String, Punch> PUNCHES = new HashMap<>();
    
    private final static Map<String, Causes> CAUSES = new HashMap<>();
    
    private final static Map<String, User> USERS = new HashMap<>();

    public Map<String, Registration> getRegistration() {
        return REGISTRATION;
    }

    public Map<String, Punch> getPunches() {
        return PUNCHES;
    }

    public Map<String, Causes> getCauses() {
        return CAUSES;
    }

    public Map<String, User> getUsers() {
        return USERS;
    } 
    
    private static void populateUsers() {
        User uAdmin = new User();
        uAdmin.setId("admin");
        Set<String> roles = new HashSet<>();
        roles.add("ADMIN");
        uAdmin.setRoles(roles);
        USERS.put(uAdmin.getId(), uAdmin);
        
        User uRegister = new User();
        uRegister.setId("registrator");
        Set<String> uRegisterRoles = new HashSet<>();
        uRegisterRoles.add("REGISTER");
        uRegister.setRoles(uRegisterRoles);
        USERS.put(uRegister.getId(), uRegister);
    }
    
    private static void populateCauses() {
        Causes c1 = new Causes();
        c1.setId(ObjectId.get().toString());
        c1.setVenueId("*");
        c1.setCode("0");
        c1.setShortDescription("Normale");
        c1.setDescription("Transito ingresso o uscita default");
        c1.setRegulation("");
        c1.setType(CausesType.ALL);
        c1.setEnabled(true);
        CAUSES.put(c1.getId(), c1);
        
        Causes c2 = new Causes();
        c2.setId(ObjectId.get().toString());
        c2.setVenueId("*");
        c2.setCode("1");
        c2.setShortDescription("Permesso breve");
        c2.setDescription("Permesso breve");
        c2.setRegulation("");
        c2.setType(CausesType.ALL);
        c2.setEnabled(true);
        CAUSES.put(c2.getId(), c2);
        
        Causes c3 = new Causes();
        c3.setId(ObjectId.get().toString());
        c3.setVenueId("*");
        c3.setCode("2");
        c3.setShortDescription("Motivi Personali/Familiari");
        c3.setDescription("Motivi Personali/Familiari");
        c3.setRegulation("");
        c3.setType(CausesType.ALL);
        c3.setEnabled(true);
        CAUSES.put(c3.getId(), c3);
        
        Causes c4 = new Causes();
        c4.setId(ObjectId.get().toString());
        c4.setVenueId("RM");
        c4.setCode("3");
        c4.setShortDescription("Banca Ore");
        c4.setDescription("Banca Ore");
        c4.setRegulation("");
        c4.setType(CausesType.ALL);
        c4.setEnabled(true);
        CAUSES.put(c4.getId(), c4);
    }
    
    String c = "[\n" +
"     {\"code\": \"0\", \"title\": \"Normale\", \"description\":\"Transito ingresso o uscita default\"},\n" +
"     {\"code\": \"1\", \"title\": \"Permesso breve\"},\n" +
"     {\"code\": \"2\", \"title\": \"Motivi Personali/Familiari\"},\n" +
"     {\"code\": \"3\", \"title\": \"Banca Ore\"},\n" +
"     {\"code\": \"4\", \"title\": \"L. 104 (Minore Handicap)\"},\n" +
"     {\"code\": \"5\", \"title\": \"L. 104 (Personale Handicap)\"},\n" +
"     {\"code\": \"6\", \"title\": \"L. 104 (Dip. 2H)\"},\n" +
"     {\"code\": \"11\", \"title\": \"L. 104 (Dip. 18H)\", \"description\": \"18 ore al mese\"},\n" +
"     {\"code\": \"7\", \"title\": \"Allattamento\"},\n" +
"     {\"code\": \"8\", \"title\": \"Permesso Studio\"},\n" +
"     {\"code\": \"9\", \"title\": \"Permesso Sindascale\"},\n" +
"     {\"code\": \"10\", \"title\": \"Permesso Organi Elettivi\"},\n" +
"     {\"code\": \"19\", \"title\": \"Servizio Eseterno autorizzato\"},\n" +
"     {\"code\": \"20\", \"title\": \"Pausa Pranzo Esterna\"}\n" +
" ]";
}
