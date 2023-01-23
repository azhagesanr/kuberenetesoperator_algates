package io.algates;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

//
@JsonIgnoreProperties(ignoreUnknown = true)
public class SampleStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    private int observedGeneration;


    public int getObservedGeneration() {
        return observedGeneration;
    }

    public void setObservedGeneration(int observedGeneration) {
        this.observedGeneration = observedGeneration;
    }
}
