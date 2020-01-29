package it.joeg.inus.be.domain.port;

import it.joeg.inus.be.domain.entities.Registration;

/**
 * Servizio per la registrazione di un'app
 */
public interface RegistrationService {
    
    public void savePreRegistration(Registration registration);
    
    public void confirmRegistration(Registration registration);
}
