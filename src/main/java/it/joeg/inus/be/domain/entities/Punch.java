package it.joeg.inus.be.domain.entities;

import java.time.LocalDateTime;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Timbratura
 */
@Data
@Document(collection = "punches")
public class Punch {

    @Id
    private String id;
    private String badgeId;
    private String cause;
    private String type;
    private LocalDateTime timestamp;
    private Coordinates coordinates;
    private String appId;
    
}
