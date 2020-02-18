package it.joeg.inus.be.dataproviders.controllers;

import it.joeg.inus.be.dataproviders.controllers.models.OtpDTO;
import it.joeg.inus.be.dataproviders.controllers.models.RegistrationDTO;
import it.joeg.inus.be.domain.entities.Registration;
import it.joeg.inus.be.domain.exceptions.InvalidDeviceIdException;
import it.joeg.inus.be.domain.exceptions.InvalidOtpException;
import it.joeg.inus.be.domain.usecases.OTPUsecase;
import it.joeg.inus.be.domain.usecases.RegistrationUsecase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoint di registrazione di app-device
 */
@RestController
@RequestMapping("/v1/")
@CrossOrigin("*")
public class RegistrationController {

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private OTPUsecase otpUsecase;

    @Autowired
    private RegistrationUsecase registrationUsecase;

    @PostMapping(value = "/otp")
    public ResponseEntity<?> requireOTP(@RequestBody Registration registration) {
        LOG.info("Require Registration {}", registration);
        Registration reg = otpUsecase.generateRegistrationOTP(registration);

        if (StringUtils.hasText(reg.getOtp())) {
            OtpDTO dTO = new OtpDTO();
            dTO.setOTP(reg.getOtp());
            return ResponseEntity.ok().body(dTO);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unable to create OTP");
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> registerApp(@RequestBody RegistrationDTO registrationDTO) {
        LOG.info("Registration required {}", registrationDTO);
        try {
            Registration registration = registrationUsecase.deviceRegistration(registrationDTO);
            RegistrationDTO registrationResponse = new RegistrationDTO();
            registrationResponse.setApplicationId(registration.getApplicationId());
            return ResponseEntity.ok().body(registrationResponse);
        } catch (InvalidOtpException ex) {
            LOG.error(ex.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getLocalizedMessage());
        } catch (InvalidDeviceIdException ex) {
            LOG.error(ex.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getLocalizedMessage());
        } catch (Exception ex) {
            LOG.error(ex.getLocalizedMessage());
            return ResponseEntity.status(501).body(ex.getLocalizedMessage());
        }

    }

}
