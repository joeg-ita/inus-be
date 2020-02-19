package it.joeg.inus.be.dataproviders.controllers;

import it.joeg.inus.be.domain.entities.Punch;
import it.joeg.inus.be.domain.exceptions.InvalidApplicationIdException;
import it.joeg.inus.be.domain.usecases.InsertPunchUsecase;
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
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class PunchesController {

    private static final Logger LOG = LoggerFactory.getLogger(PunchesController.class);

    @Autowired
    RetrievePunchesUsecase punchesUsecase;

    @Autowired
    InsertPunchUsecase insertPunchUsecase;

    @PostMapping(value = "/punches/punch")
    public ResponseEntity<?> postPunch(@RequestBody Punch punch) {
        LOG.info("Punch acquired {}", punch);
        try {
            insertPunchUsecase.insertOnePunch(punch);
            return ResponseEntity.ok().build();
        } catch (InvalidApplicationIdException ex) {
            return ResponseEntity.badRequest().body(ex.getLocalizedMessage());
        }
    }

    @PostMapping(value = "/punches/punches")
    public ResponseEntity<List<Punch>> postPunch(@RequestBody List<Punch> punches) {
        LOG.info("Punchs acquired {}", punches);
        try {
            insertPunchUsecase.insertPunches(punches);
            return ResponseEntity.ok().body(punches);
        } catch (InvalidApplicationIdException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "/punches/{badgeId}")
    public ResponseEntity<?> getPunches(@PathVariable("badgeId") String badgeId) {
        LOG.info("Requested Punches by user {}", badgeId);
        List<Punch> punches = punchesUsecase.retrievePunches(badgeId);
        return ResponseEntity.ok().body(punches);
    }

    @GetMapping(value = "/punches/last_punch/{badgeId}")
    public ResponseEntity<?> getLastPunch(@PathVariable("badgeId") String badgeId) {
        LOG.info("Requested Punches by user {}", badgeId);
        Optional<Punch> punch = punchesUsecase.retrieveLastPunch(badgeId);
        if (punch.isPresent()) {
            return ResponseEntity.ok().body(punch.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
