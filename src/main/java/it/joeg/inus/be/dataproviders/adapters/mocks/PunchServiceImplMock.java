package it.joeg.inus.be.dataproviders.adapters.mocks;

import it.joeg.inus.be.domain.entities.Punch;
import it.joeg.inus.be.domain.port.PunchService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.bson.types.ObjectId;

/**
 *
 */
public class PunchServiceImplMock implements PunchService {

    InMemoryStore inMemoryStore = InMemoryStore.getInstance();

    @Override
    public void save(Punch punch) {
        if (punch.getId() == null) {
            punch.setId(ObjectId.get().toString());
        }
        inMemoryStore.getPunches().put(punch.getBadgeId(), punch);
    }

    @Override
    public List<Punch> retrievePunches(String badgeId) {
        List<Punch> result = new ArrayList<>();
        for (Map.Entry<String, Punch> p : inMemoryStore.getPunches().entrySet()) {
            if (p.getValue().getBadgeId() != null && p.getValue().getBadgeId().equals(badgeId)) {
                result.add(p.getValue());
            }
        }
        return result;
    }

    @Override
    public Optional<Punch> retrieveLastPunch(String badgeId) {
        Punch result = null;
        for (Map.Entry<String, Punch> p : inMemoryStore.getPunches().entrySet()) {
            if (p.getValue().getBadgeId() != null && p.getValue().getBadgeId().equals(badgeId)) {
                if (result != null && result.getTimestamp().isBefore(p.getValue().getTimestamp())) {
                    result = p.getValue();
                } else {
                    result = p.getValue();
                }
            }
        }
        return Optional.ofNullable(result);
    }

}
