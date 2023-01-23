package io.algates;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class SampleSpec {

    private String nameOverride;

    private String imageRef;

    private int replica;

    private DependentTestobj dependentTestobj;

    public String getNameOverride() {
        return nameOverride;
    }

    public void setNameOverride(String nameOverride) {
        this.nameOverride = nameOverride;
    }


    public int getReplica() {
        return replica;
    }

    public void setReplica(int replica) {
        this.replica = replica;
    }


    public String getImageRef() {
        return imageRef;
    }

    public void setImageref(String imageRef) {
        this.imageRef = imageRef;
    }

    public DependentTestobj getDependentTestobj() {
        return dependentTestobj;
    }

    public void setDependentTestobj(DependentTestobj dependentTestobj) {
        this.dependentTestobj = dependentTestobj;
    }
}