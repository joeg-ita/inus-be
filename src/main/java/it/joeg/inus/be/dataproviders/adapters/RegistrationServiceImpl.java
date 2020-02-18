package it.joeg.inus.be.dataproviders.adapters;

import it.joeg.inus.be.domain.entities.Registration;
import it.joeg.inus.be.domain.port.RegistrationService;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.data.mongodb.core.query.Query;

/**
 *
 */
public class RegistrationServiceImpl implements RegistrationService {
    
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void savePreRegistration(Registration registration) {
        registration.setCreatedAt(LocalDateTime.now());
        registration.setLastModifyAt(LocalDateTime.now());
        mongoTemplate.save(registration);
    }

    @Override
    public void confirmRegistration(Registration registration) {
        registration.setLastModifyAt(LocalDateTime.now());
        mongoTemplate.save(registration);
    }

    @Override
    public Optional<Registration> findRegistrationByOtp(String otp) {
        Registration registration = mongoTemplate.findOne(new Query(where("otp").is(otp)), Registration.class);
        return Optional.ofNullable(registration);
    }

    @Override
    public Optional<Registration> findDeviceId(String deviceId) {
        Registration registration = mongoTemplate.findOne(new Query(where("deviceId").is(deviceId)), Registration.class);
        return Optional.ofNullable(registration);
    }

}
