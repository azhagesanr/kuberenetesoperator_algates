package io.algates;

import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.javaoperatorsdk.operator.Operator;

public class Runner {
    public static void main(String[] args) {

        KubernetesClient client = new DefaultKubernetesClient();

            Operator operator = new Operator();
            operator.register(new ExposedAppReconciler(client));
            operator.register(new SampleReconciler(client));
            operator.start();
        }
    }
