package it.joeg.inus.be.domain.entities;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * Registrazione
 */
@Data
public class Registration {
    
    private String otp;
    private String venueId;
    private String venueDescription;
    private String venueDeviceLocation;
    private String applicationId;
    private LocalDateTime createdAt;
    
}
