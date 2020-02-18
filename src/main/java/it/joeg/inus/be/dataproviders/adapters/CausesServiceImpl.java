package it.joeg.inus.be.dataproviders.adapters;

import it.joeg.inus.be.domain.entities.Causes;
import it.joeg.inus.be.domain.port.CausesService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.data.mongodb.core.query.Query;

/**
 *
 */
public class CausesServiceImpl implements CausesService {
    
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Causes> retrieveCauses(Optional<String> venueId) {
        return mongoTemplate.find(new Query(where("venueId").is(venueId)), Causes.class);
    }

}
