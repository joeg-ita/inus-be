package it.joeg.inus.be;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    public InusController() {

    }

    @GetMapping(value = "/causes")
    public ResponseEntity<List<CauseDTO>> getCauses() {

        LOG.info("Request causes");
        List<CauseDTO> causes = new ArrayList<>();
        causes.add(new CauseDTO("0", "Default", "Default for In and Out punching"));
        causes.add(new CauseDTO("1", "Lunch", ""));
        causes.add(new CauseDTO("2", "Permit", ""));

        return ResponseEntity.ok().body(causes);
    }
    
    @PostMapping(value = "/transit")
    public ResponseEntity<TransitDTO> postTransit(TransitDTO transit){
        LOG.info("Transit acquired {}", transit);
        return ResponseEntity.ok().body(transit);
    }

}
