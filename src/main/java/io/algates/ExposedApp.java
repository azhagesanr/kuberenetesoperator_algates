package io.algates;

import io.fabric8.kubernetes.api.model.Namespaced;
import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.Version;

@Version("v1alpha1")
@Group("io.algates")
public class ExposedApp extends CustomResource<ExposedAppSpec, ExposedAppStatus> implements Namespaced {

    public ExposedAppStatus getStatus() {
        return this.status;
    }

    public void setSpec(ExposedAppSpec spec) {
        this.spec = spec;
    }

    public void setStatus(ExposedAppStatus status) {
        this.status = status;
    }

    public ExposedAppSpec getSpec() {
        return this.spec;
    }
}

