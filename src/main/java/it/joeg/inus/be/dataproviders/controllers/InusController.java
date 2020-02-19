package it.joeg.inus.be.dataproviders.controllers;

import it.joeg.inus.be.dataproviders.controllers.models.PunchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 */
@RestController
@RequestMapping("/v1/")
public class InusController {

    private static final Logger LOG = LoggerFactory.getLogger(InusController.class);
    
    @PostMapping(value = "/transit")
    public ResponseEntity<PunchDTO> postPunch(@RequestBody PunchDTO punch){
        LOG.info("Punch acquired {}", punch);
        return ResponseEntity.ok().body(punch);
    }

}
