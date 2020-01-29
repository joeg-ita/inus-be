package it.joeg.inus.be.domain.entities;

import lombok.Data;

/**
 *
 */
@Data
public class Causes {

    private String code;
    private String shortDescription;
    private String description;
    private String regulation;
    private CausesType type;
    
}
