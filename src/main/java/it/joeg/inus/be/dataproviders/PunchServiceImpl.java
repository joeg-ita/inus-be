package it.joeg.inus.be.dataproviders;

import it.joeg.inus.be.domain.entities.Punch;
import it.joeg.inus.be.domain.port.PunchService;
import java.util.List;

/**
 * Implementazione servizio timbratura
 */
public class PunchServiceImpl implements PunchService{

    @Override
    public void save(Punch punch) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Punch> retrievePunches(String badgeId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
