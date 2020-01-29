package it.joeg.inus.be.domain.entities;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * Timbratura
 */
@Data
public class Punch {

    private String badgeId;
    private String cause;
    private String type;
    private LocalDateTime timestamp;
    private Coordinates coordinates;
    private String appId;
    
}
