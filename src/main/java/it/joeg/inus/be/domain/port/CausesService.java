package it.joeg.inus.be.domain.port;

import it.joeg.inus.be.domain.entities.Causes;
import java.util.List;
import java.util.Optional;

/**
 * Servizio per lottenimento delle causali
 */
public interface CausesService {
    
    public List<Causes> retrieveCauses(Optional<String> venueId);
    
}
