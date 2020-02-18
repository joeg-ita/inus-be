package it.joeg.inus.be.domain.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 */
@Data
@Document(collection = "causes")
public class Causes {

    @Id
    private String id;
    private String venueId;
    private String code;
    private String shortDescription;
    private String description;
    private String regulation;
    private CausesType type;
    private boolean enabled;
    
}
