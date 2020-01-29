package it.joeg.inus.be.controllers;

import it.joeg.inus.be.controllers.dto.PunchDTO;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    
    @PostMapping(value = "/punch")
    public ResponseEntity<PunchDTO> postPunch(@RequestBody PunchDTO punch){
        LOG.info("Punch acquired {}", punch);
        return ResponseEntity.ok().body(punch);
    }
    
    @PostMapping(value = "/punches")
    public ResponseEntity<List<PunchDTO>> postPunch(@RequestBody List<PunchDTO> punches){
        LOG.info("Punchs acquired {}", punches);
        return ResponseEntity.ok().body(punches);
    }
}
