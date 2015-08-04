package uk.gov.ho.api.webmonitor.dependencyinjection;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.name.Names;

public class AkkaGuiceActorProducer implements IndirectActorProducer {
  private final String providerName;

  private final Provider<Injector> injector;

  private AkkaGuiceActorProducer(Provider<Injector> injector, String providerName) {
    this.injector = injector;
    this.providerName = providerName;
  }

  /**
   * Produce an instance of the actor.
   *
   * @return The actor
   */
  public Actor produce() {
    return injector.get()
        .getBinding(Key.get(Actor.class, Names.named(providerName)))
        .getProvider()
        .get();
  }

  /**
   * Get an actor class from the injector.
   *
   * @return Get an actor class from the injector
   */
  @SuppressWarnings("unchecked")
  public Class<? extends Actor> actorClass() {
    return injector.get()
        .getBinding(Key.get(Class.class, Names.named(providerName)))
        .getProvider()
        .get();
  }
}
