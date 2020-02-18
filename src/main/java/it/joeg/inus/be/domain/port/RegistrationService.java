package it.joeg.inus.be.domain.port;

import it.joeg.inus.be.domain.entities.Registration;
import java.util.Optional;

/**
 * Servizio per la registrazione di un'app
 */
public interface RegistrationService {
    
    public void savePreRegistration(Registration registration);
    
    public void confirmRegistration(Registration registration);
    
    public Optional<Registration> findRegistrationByOtp(String otp);
    
    public Optional<Registration> findDeviceId(String deviceId);
    
    
}
