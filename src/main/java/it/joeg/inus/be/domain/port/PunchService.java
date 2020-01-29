package it.joeg.inus.be.domain.port;

import it.joeg.inus.be.domain.entities.Punch;
import java.util.List;

/**
 * Servizio per il salvataggio dei transiti
 */
public interface PunchService {
    
    public void save(Punch punch);
    
    public List<Punch> retrievePunches(String badgeId);
}
