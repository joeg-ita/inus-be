package it.joeg.inus.be.domain.entities.user;

import java.util.Set;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
@Data
public class User {
    
    @Id
    private String id;
    
    private String name;
    
    private String username;
    
    private String email;
    
    private String account;
    
    private String fiscalCode;
    
    private String password;
    
    private boolean active;
    
    private Set<String> roles;
    
   
}
