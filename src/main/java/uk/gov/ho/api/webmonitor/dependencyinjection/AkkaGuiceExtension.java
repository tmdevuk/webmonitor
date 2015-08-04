package uk.gov.ho.api.webmonitor.dependencyinjection;

import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;
import akka.actor.Extension;
import akka.actor.Props;
import com.google.inject.Injector;
import com.google.inject.Provider;

/**
 * An Akka Extension to provide access to Spring managed Actor Beans.
 */
public class AkkaGuiceExtension extends AbstractExtensionId<AkkaGuiceExtension.GuiceExt> {

  /**
   * The identifier used to access the AkkaGuiceExtension.
   */
  public static final AkkaGuiceExtension AkkaGuiceExtProvider = new AkkaGuiceExtension();

  /**
   * Is used by Akka to instantiate the Extension identified by this ExtensionId, internal use
   * only.
   */
  public GuiceExt createExtension(ExtendedActorSystem system) {
    return new GuiceExt();
  }

  /**
   * The Extension implementation.
   */
  public static class GuiceExt implements Extension {
    private volatile Provider<Injector> injector;

    /**
     * Used to initialize the dependency injector for the extension.
     *
     * @param injector The injector
     */
    public void initialize(Provider<Injector> injector) {
      this.injector = injector;
    }

    /**
     * Create a Props for the specified namedActorProvider using the SpringActorProducer class.
     *
     * @param namedActorProvider The name of the actor provider to create Props for
     *
     * @return a Props that will create the named actor bean using Spring
     */
    public Props props(String namedActorProvider) {
      return Props.create(AkkaGuiceActorProducer.class, injector, namedActorProvider);
    }
  }
}
