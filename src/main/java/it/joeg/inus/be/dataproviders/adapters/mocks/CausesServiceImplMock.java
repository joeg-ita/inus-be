package it.joeg.inus.be.dataproviders.adapters.mocks;

import it.joeg.inus.be.domain.entities.Causes;
import it.joeg.inus.be.domain.port.CausesService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 */
public class CausesServiceImplMock implements CausesService {

    InMemoryStore inMemoryStore = InMemoryStore.getInstance();

    @Override
    public List<Causes> retrieveCauses(Optional<String> venueId) {
        List<Causes> result = new ArrayList<>();
        for (Map.Entry<String, Causes> c : inMemoryStore.getCauses().entrySet()) {
            if (c.getValue().getVenueId() != null
                    && c.getValue().isEnabled()
                    && (c.getValue().getVenueId().equalsIgnoreCase(venueId.orElse(c.getValue().getVenueId()))
                    || c.getValue().getVenueId().equalsIgnoreCase("*"))) {
                result.add(c.getValue());
            }
        }
        return result;
    }

}
