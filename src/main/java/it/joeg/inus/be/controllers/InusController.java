package it.joeg.inus.be.controllers;

import it.joeg.inus.be.controllers.dto.RegistrationDTO;
import it.joeg.inus.be.controllers.dto.CauseDTO;
import it.joeg.inus.be.controllers.dto.PunchDTO;
import it.joeg.inus.be.dataproviders.OTPServiceImpl;
import it.joeg.inus.be.domain.usecases.OTPUsecase;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 *
 */
@RestController
@RequestMapping("/v1/")
@CrossOrigin("*")
public class InusController {

    private static final Logger LOG = LoggerFactory.getLogger(InusController.class);
    
    @Autowired
    private OTPUsecase otp;
    
    @PostMapping(value = "/transit")
    public ResponseEntity<PunchDTO> postPunch(@RequestBody PunchDTO punch){
        LOG.info("Punch acquired {}", punch);
        return ResponseEntity.ok().body(punch);
    }
    
    @GetMapping(value = "/otp")
    public ResponseEntity<String> getOTP(){
        LOG.info("Requested OTP");
        return ResponseEntity.ok().body(otp.generateOTP());
    }
    
    @PostMapping(value = "/register")
    public ResponseEntity<RegistrationDTO> postOTP(@RequestBody RegistrationDTO registration){
        LOG.info("Registration required {}", registration);
        return ResponseEntity.ok().body(registration);
    }

}
