package io.algates;

import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;

@Version("v1alpha1")
@Group("io.algates")
public class Sample extends CustomResource<SampleSpec, SampleStatus> implements Namespaced {
    public SampleStatus getStatus() {
        return this.status;
    }

    public void setSpec(SampleSpec spec) {
        this.spec = spec;
    }

    public void setStatus(SampleStatus status) {
        this.status = status;
    }

    public SampleSpec getSpec() {
        return this.spec;
    }
}
