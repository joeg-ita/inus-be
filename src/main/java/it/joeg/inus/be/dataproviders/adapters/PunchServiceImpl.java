package it.joeg.inus.be.dataproviders.adapters;

import it.joeg.inus.be.domain.entities.Punch;
import it.joeg.inus.be.domain.port.PunchService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Implementazione servizio timbratura
 */
public class PunchServiceImpl implements PunchService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void save(Punch punch) {
        mongoTemplate.save(punch);
    }

    @Override
    public void saveMany(List<Punch> punches) {
        mongoTemplate.insertAll(punches);
    }

    @Override
    public List<Punch> retrievePunches(String badgeId) {
        return mongoTemplate.find(new Query(where("badgeId").is(badgeId)
                .andOperator(where("timestamp").gte(LocalDateTime.now().minusDays(30)))), Punch.class);
    }

    @Override
    public Optional<Punch> retrieveLastPunch(String badgeId) {
        List<Order> sortproperties = new ArrayList<>();
        sortproperties.add(new Order(Sort.Direction.DESC, "_id"));
        List<Punch> tmpList = mongoTemplate.find(new Query().limit(1).with(Sort.by(sortproperties)), Punch.class);
        if (!tmpList.isEmpty()) {
            return Optional.ofNullable(tmpList.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public long retrievePunches(long minutes) {
        return mongoTemplate.find(new Query(where("timestamp").gte(LocalDateTime.now().minusMinutes(minutes))), Punch.class).size();
    }

}
