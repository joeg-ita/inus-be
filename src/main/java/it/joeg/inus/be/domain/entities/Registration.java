package it.joeg.inus.be.domain.entities;

import java.time.LocalDateTime;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Registrazione terminale
 */
@Data
@Document(collection = "registrations")
public class Registration {
    
    @Id
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifyAt;
    
    private String otp;
    @NotEmpty
    private String venueId;
    @NotEmpty
    private String venueDescription;
    @NotEmpty
    private String venueDeviceLocation;
    private String deviceId;
    private String applicationId;
    
}
