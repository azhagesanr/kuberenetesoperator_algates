package io.algates;

import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;
import io.fabric8.kubernetes.api.model.ServiceBuilder;
import io.fabric8.kubernetes.api.model.apps.DeploymentBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.javaoperatorsdk.operator.api.reconciler.Constants;
import io.javaoperatorsdk.operator.api.reconciler.Context;
import io.javaoperatorsdk.operator.api.reconciler.ControllerConfiguration;
import io.javaoperatorsdk.operator.api.reconciler.Reconciler;
import io.javaoperatorsdk.operator.api.reconciler.UpdateControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
@ControllerConfiguration(namespaces = Constants.WATCH_CURRENT_NAMESPACE, name = "sample")
public class SampleReconciler implements Reconciler<Sample> {

    static final String APP_LABEL = "app.kubernetes.io/name";

    private final KubernetesClient client;
    static final Logger log = LoggerFactory.getLogger(ExposedAppReconciler.class);

    public SampleReconciler(KubernetesClient client) {

        this.client = client;
    }


    @Override
    public UpdateControl<Sample> reconcile(Sample sample, Context<Sample> context) throws Exception {
        // TODO: fill in logic

        System.out.println("sample reconcile is calling");

        final var labels = Map.of(APP_LABEL, sample.getMetadata().getName());
        final var name = sample.getMetadata().getName();

        final var spec = sample.getSpec();
        final var imageRef = spec.getImageRef();
        System.out.println("sample replica" + spec.getReplica());
        final var metadata = createMetadata(sample, labels);


        final var deployment = new DeploymentBuilder()
         .withMetadata(createMetadata(sample, labels))
         .withNewSpec()
         .withNewSelector().withMatchLabels(labels).endSelector()
         .withNewTemplate()
         .withNewMetadata().withLabels(labels).endMetadata()
         .withNewSpec()
         .addNewContainer()
         .withName(name).withImage(imageRef)
         .addNewPort().withName("http").withProtocol("TCP").withContainerPort(8080).endPort()
         .endContainer()
         .endSpec()
         .endTemplate()
         .endSpec()
         .build();
         client.apps().deployments().createOrReplace(deployment);


         client.services().createOrReplace(new ServiceBuilder()
         .withMetadata(createMetadata(sample,labels))
         .withNewSpec()
         .addNewPort()
         .withName("http")
         .withPort(8080)
         .withNewTargetPort().endTargetPort()
         .endPort()
         .withSelector(labels)
         .withType("ClusterIP")
         .endSpec()
         .build());


        UpdateControl up = UpdateControl.updateStatus(sample);

        return up;
    }

    private ObjectMeta createMetadata(Sample resource, Map<String, String> labels) {
        final var metadata = resource.getMetadata();
        return new ObjectMetaBuilder()
            .withName(metadata.getName())
            .addNewOwnerReference()
            .withUid(metadata.getUid())
            .withApiVersion(resource.getApiVersion())
            .withName(metadata.getName())
            .withKind(resource.getKind())
            .endOwnerReference()
            .withLabels(labels)
            .build();
    }


}
