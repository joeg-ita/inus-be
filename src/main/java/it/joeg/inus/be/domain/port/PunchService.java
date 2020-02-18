package it.joeg.inus.be.domain.port;

import it.joeg.inus.be.domain.entities.Punch;
import java.util.List;
import java.util.Optional;

/**
 * Servizio per il salvataggio dei transiti
 */
public interface PunchService {
    
    public void save(Punch punch);
    
    public void saveMany(List<Punch> punches);
    
    public List<Punch> retrievePunches(String badgeId);
    
    public Optional<Punch> retrieveLastPunch(String badgeId);
}
