package it.joeg.inus.be.dataproviders.controllers;

import it.joeg.inus.be.dataproviders.controllers.models.PunchDTO;
import it.joeg.inus.be.domain.entities.Punch;
import it.joeg.inus.be.domain.usecases.RetrievePunchesUsecase;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * punches endpoint controller
 */
@RestController
@RequestMapping("/v1/")
@CrossOrigin("*")
public class PunchesController {

    private static final Logger LOG = LoggerFactory.getLogger(PunchesController.class);
    
    @Autowired
    RetrievePunchesUsecase punchesUsecase;

    @PostMapping(value = "/punch")
    public ResponseEntity<PunchDTO> postPunch(@RequestBody PunchDTO punch) {
        LOG.info("Punch acquired {}", punch);
        return ResponseEntity.ok().body(punch);
    }

    @PostMapping(value = "/punches")
    public ResponseEntity<List<PunchDTO>> postPunch(@RequestBody List<PunchDTO> punches) {
        LOG.info("Punchs acquired {}", punches);
        return ResponseEntity.ok().body(punches);
    }

    @GetMapping(value = "/punches/{badgeId}")
    public ResponseEntity<?> getPunches(@PathVariable("badgeId") String badgeId) {
        LOG.info("Requested Punches by user {}", badgeId);
        List<Punch> punches = punchesUsecase.retrievePunches(badgeId);
        return ResponseEntity.ok().body(punches);
    }
    
    @GetMapping(value = "/last_punch/{badgeId}")
    public ResponseEntity<?> getLastPunch(@PathVariable("badgeId") String badgeId) {
        LOG.info("Requested Punches by user {}", badgeId);
        Optional<Punch> punch = punchesUsecase.retrieveLastPunch(badgeId);
        if(punch.isPresent()){
            return ResponseEntity.ok().body(punch.get());
        } else {
            return ResponseEntity.noContent().build();
        }   
    }
}