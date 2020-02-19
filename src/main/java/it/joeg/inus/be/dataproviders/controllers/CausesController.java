package it.joeg.inus.be.dataproviders.controllers;

import it.joeg.inus.be.domain.entities.Causes;
import it.joeg.inus.be.domain.usecases.RetrieveCausesUsecase;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/api/v1")
public class CausesController {
    
    private static final Logger LOG = LoggerFactory.getLogger(CausesController.class);
    
    @Autowired
    private RetrieveCausesUsecase causesUsecase;
    
    @GetMapping(value = "/causes")
    public ResponseEntity<List<?>> getCauses() {

        LOG.info("Request causes");
        List<Causes> causes = causesUsecase.getCauses(Optional.empty());

        return ResponseEntity.ok().body(causes); 
    }
    
    @GetMapping(value = "/causes/{venueId}")
    public ResponseEntity<List<?>> getCausesByVenueId(@PathVariable("venueId") String venueId) {

        LOG.info("Request causes for venueId {}", venueId);
        List<Causes> causes = causesUsecase.getCauses(Optional.of(venueId));

        return ResponseEntity.ok().body(causes); 
    }

}
