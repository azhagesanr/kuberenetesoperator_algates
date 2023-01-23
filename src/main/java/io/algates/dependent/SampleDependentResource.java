package io.algates.dependent;

import io.algates.DependentTestobj;
import io.algates.ExposedApp;
import io.algates.Sample;
import io.algates.SampleSpec;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.javaoperatorsdk.operator.api.reconciler.Context;
import io.javaoperatorsdk.operator.processing.dependent.kubernetes.CRUDKubernetesDependentResource;
import io.javaoperatorsdk.operator.processing.dependent.kubernetes.KubernetesDependent;

@KubernetesDependent
public class SampleDependentResource extends CRUDKubernetesDependentResource<Sample, ExposedApp>  {

    public SampleDependentResource() {
        super(Sample.class);
    }

    @Override
    public Sample desired(ExposedApp resource, Context<ExposedApp> context) {

        Sample sample = createSample(resource);
        sample.getMetadata().setNamespace(resource.getMetadata().getNamespace());

        System.out.print("sample spec desired" + sample.toString());
        return sample;
    }

    private Sample createSample(ExposedApp resource) {
        Sample sample = new Sample();
        try {

            SampleSpec sampleSpec = new SampleSpec();
            sampleSpec.setReplica(resource.getSpec().getReplica());
            sampleSpec.setImageref(resource.getSpec().getImageRef());
            sampleSpec.setNameOverride(resource.getSpec().getNameOverride());

            DependentTestobj dependentTestobj = new DependentTestobj();

            if(resource.getSpec().getTestobj() != null) {
                dependentTestobj.setName1(resource.getSpec().getTestobj().getName1());
                dependentTestobj.setName2(resource.getSpec().getTestobj().getName2());
                sampleSpec.setDependentTestobj(dependentTestobj);
            }

            ObjectMeta objectMeta = new ObjectMeta();
            objectMeta.setName(resource.getMetadata().getName());
            objectMeta.setLabels(resource.getMetadata().getLabels());
            objectMeta.setAnnotations(resource.getMetadata().getAnnotations());
            sample.setMetadata(objectMeta);

            sample.setSpec(sampleSpec);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return  sample;
    }
}
