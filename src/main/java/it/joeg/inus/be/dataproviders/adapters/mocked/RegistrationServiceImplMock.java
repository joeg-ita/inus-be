package it.joeg.inus.be.dataproviders.adapters.mocked;

import it.joeg.inus.be.dataproviders.mocked.InMemoryStore;
import it.joeg.inus.be.domain.entities.Registration;
import it.joeg.inus.be.domain.port.RegistrationService;
import java.util.Map;
import java.util.Optional;
import org.bson.types.ObjectId;

/**
 *
 */
public class RegistrationServiceImplMock implements RegistrationService {

    InMemoryStore inMemoryStore = InMemoryStore.getInstance();

    @Override
    public void savePreRegistration(Registration registration) {
        if (registration.getId() == null) {
            registration.setId(ObjectId.get().toString());
        }
        inMemoryStore.getRegistration().put(registration.getId(), registration);
    }

    @Override
    public void confirmRegistration(Registration registration) {
        inMemoryStore.getRegistration().put(registration.getId(), registration);
    }

    @Override
    public Optional<Registration> findRegistrationByOtp(String otp) {
        Optional<Registration> result = Optional.empty();
        for (Map.Entry<String, Registration> regs : inMemoryStore.getRegistration().entrySet()) {
            if (regs.getValue().getOtp() != null && regs.getValue().getOtp().equals(otp)) {
                result = Optional.ofNullable(regs.getValue());
            }
        }
        return result;
    }

    @Override
    public Optional<Registration> findDeviceId(String deviceId) {
        Optional<Registration> result = Optional.empty();
        for (Map.Entry<String, Registration> regs : inMemoryStore.getRegistration().entrySet()) {
            if (regs.getValue().getDeviceId() != null && regs.getValue().getDeviceId().equals(deviceId)) {
                result = Optional.ofNullable(regs.getValue());
            }
        }
        return result;
    }

    @Override
    public Optional<Registration> findRegistrationByApplicationId(String appId) {
        Optional<Registration> result = Optional.empty();
        for (Map.Entry<String, Registration> regs : inMemoryStore.getRegistration().entrySet()) {
            if (regs.getValue().getApplicationId() != null && regs.getValue().getApplicationId().equals(appId)) {
                result = Optional.ofNullable(regs.getValue());
            }
        }
        return result;
    }

}
