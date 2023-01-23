package io.algates;

public class ExposedAppSpec {

    private String nameOverride;

    private String imageRef;

    private Testobj testobj;

    private int replica;

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

    public Testobj getTestobj() {
        return testobj;
    }

    public void setTestobj(Testobj testobj) {
        this.testobj = testobj;
    }
}
