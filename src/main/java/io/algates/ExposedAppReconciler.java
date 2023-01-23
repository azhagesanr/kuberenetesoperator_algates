package io.algates;

import io.algates.dependent.SampleDependentResource;

import io.fabric8.kubernetes.api.model.*;

import io.fabric8.kubernetes.api.model.apiextensions.v1.CustomResourceDefinition;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.Resource;
import io.fabric8.kubernetes.client.dsl.base.CustomResourceDefinitionContext;
import io.fabric8.kubernetes.client.dsl.base.ResourceDefinitionContext;
import io.javaoperatorsdk.operator.api.config.informer.InformerConfiguration;
import io.javaoperatorsdk.operator.api.reconciler.*;

import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import io.javaoperatorsdk.operator.api.reconciler.Context;
import io.javaoperatorsdk.operator.api.reconciler.dependent.Dependent;
import io.javaoperatorsdk.operator.processing.event.ResourceID;
import io.javaoperatorsdk.operator.processing.event.source.EventSource;
import io.javaoperatorsdk.operator.processing.event.source.SecondaryToPrimaryMapper;
import io.javaoperatorsdk.operator.processing.event.source.informer.InformerEventSource;
import io.javaoperatorsdk.operator.processing.event.source.informer.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ControllerConfiguration(namespaces = Constants.WATCH_CURRENT_NAMESPACE, name = "exposedapp",
    dependents = {
        @Dependent(type = SampleDependentResource.class),
    })
public class ExposedAppReconciler implements Reconciler<ExposedApp> {
  private final KubernetesClient client;

  static final String APP_LABEL = "app.kubernetes.io/name";


  public ExposedAppReconciler(KubernetesClient client) {

    this.client = client;
  }

  // TODO Fill in the rest of the reconciler

  //@Override
  public UpdateControl<ExposedApp> reconcile(ExposedApp exposedApp, Context<ExposedApp> context) throws InterruptedException {
    Optional<Sample> sampleResource = context.getSecondaryResource(Sample.class);
    if(sampleResource.isPresent()) {
      System.out.println("sampleResource is present currnet" + sampleResource.get().toString());

    }

    return UpdateControl.noUpdate();

  }
}

