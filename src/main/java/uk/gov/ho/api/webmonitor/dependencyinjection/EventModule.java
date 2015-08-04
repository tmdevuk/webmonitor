package uk.gov.ho.api.webmonitor.dependencyinjection;

import static uk.gov.ho.api.webmonitor.dependencyinjection.AkkaGuiceExtension.AkkaGuiceExtProvider;

import uk.gov.ho.api.webmonitor.configuration.WebmonitorConfiguration;

/**
import uk.gov.ho.api.lev.configuration.LevConfiguration;
import uk.gov.ho.api.lev.factory.birth.BirthEventFactory;
import uk.gov.ho.api.lev.factory.birth.BirthEventFactoryImpl;
import uk.gov.ho.api.lev.factory.birth.ChildFactory;
import uk.gov.ho.api.lev.factory.birth.ChildFactoryImpl;
import uk.gov.ho.api.lev.factory.birth.FatherFactory;
import uk.gov.ho.api.lev.factory.birth.FatherFactoryImpl;
import uk.gov.ho.api.lev.factory.birth.LocationFactory;
import uk.gov.ho.api.lev.factory.birth.LocationFactoryImpl;
import uk.gov.ho.api.lev.factory.birth.MotherFactory;
import uk.gov.ho.api.lev.factory.birth.MotherFactoryImpl;
import uk.gov.ho.api.lev.factory.birth.OrdsDateStringParser;
import uk.gov.ho.api.lev.factory.birth.OrdsDateStringParserImpl;
import uk.gov.ho.api.lev.factory.birth.StatusFactory;
import uk.gov.ho.api.lev.factory.birth.StatusFactoryImpl;
import uk.gov.ho.api.lev.factory.birth.SubjectsFactory;
import uk.gov.ho.api.lev.factory.birth.SubjectsFactoryImpl;
import uk.gov.ho.api.lev.ordsclient.HttpUrlConnectionFactory;
import uk.gov.ho.api.lev.ordsclient.HttpUrlConnectionFactoryHttpImpl;
import uk.gov.ho.api.lev.ordsclient.HttpUrlConnectionFactoryHttpsImpl;
import uk.gov.ho.api.lev.ordsclient.OrdsClient;
import uk.gov.ho.api.lev.ordsclient.OrdsClientImpl;
import uk.gov.ho.api.lev.service.birth.BirthEventService;
import uk.gov.ho.api.lev.service.birth.BirthEventServiceImpl;
import uk.gov.ho.api.lev.service.birth.actors.OrdsClientActor;
import uk.gov.ho.api.lev.service.birth.actors.PoleBuilderActor;
*/

//import akka.actor.Actor;

import akka.actor.ActorSystem;
import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
//import com.google.inject.name.Names;

//import java.net.MalformedURLException;

import java.net.URI;
import java.net.URISyntaxException;




public class EventModule extends AbstractModule {
  /**
   * Custom configurations for which classes should be injected where.
   */
  @Override
  protected void configure() {
/**
    bind(OrdsClient.class).to(OrdsClientImpl.class).in(Singleton.class);
    bind(BirthEventFactory.class).to(BirthEventFactoryImpl.class).in(Singleton.class);
    bind(ChildFactory.class).to(ChildFactoryImpl.class).in(Singleton.class);
    bind(FatherFactory.class).to(FatherFactoryImpl.class).in(Singleton.class);
    bind(LocationFactory.class).to(LocationFactoryImpl.class).in(Singleton.class);
    bind(MotherFactory.class).to(MotherFactoryImpl.class).in(Singleton.class);
    bind(OrdsDateStringParser.class).to(OrdsDateStringParserImpl.class).in(Singleton.class);
    bind(StatusFactory.class).to(StatusFactoryImpl.class).in(Singleton.class);
    bind(BirthEventService.class).to(BirthEventServiceImpl.class).in(Singleton.class);
    bind(SubjectsFactory.class).to(SubjectsFactoryImpl.class).in(Singleton.class);

    bind(Actor.class).annotatedWith(Names.named("ordsClientActor")).to(OrdsClientActor.class);
    bind(Class.class).annotatedWith(Names.named("ordsClientActor"))
        .toInstance(OrdsClientActor.class);
    bind(Actor.class).annotatedWith(Names.named("poleBuilderActor")).to(PoleBuilderActor.class);
    bind(Class.class).annotatedWith(Names.named("poleBuilderActor"))
        .toInstance(PoleBuilderActor.class);
   */
  }


  @Provides
  @Named("uiUri")
  public URI providesUiUri(WebmonitorConfiguration configuration) throws URISyntaxException {
    return new URI(configuration.getUiUri());
  }

  /**
   * Get the ORDs endpoint.
   *
   * @param configuration The configuration
   *
   * @return The URI
   *
   * @throws URISyntaxException If the URL isn't a valid URI
   */
  @Provides
  @Named("ordsEndpoint")
  public URI providesOrdsEndpoint(WebmonitorConfiguration configuration) throws URISyntaxException {
    return new URI(configuration.getOrdsEndpoint());
  }

  @Provides
  @Named("circuitBreakerMaxErrors")
  public Integer providesCircuitBreakerMaxErrors(WebmonitorConfiguration configuration) {
    return configuration.getCircuitBreaker().getMaxErrors();
  }

  @Provides
  @Named("circuitBreakerCallTimeout")
  public Integer providesCircuitBreakerCallTimeout(WebmonitorConfiguration configuration) {
    return configuration.getCircuitBreaker().getCallTimeout();
  }

  @Provides
  @Named("circuitBreakerResetTimeout")
  public Integer providesCircuitBreakerResetTimeout(WebmonitorConfiguration configuration) {
    return configuration.getCircuitBreaker().getResetTimeout();
  }

  /**
   * Provide Akka.
   *
   * @param injector The injector
   *
   * @return Akka system
   */
  @Provides
  @Singleton
  public ActorSystem providesAkka(Provider<Injector> injector) {
    ActorSystem system = ActorSystem.create("birthEventService");
    AkkaGuiceExtProvider.get(system).initialize(injector);

    return system;
  }

  @Provides
  @Named("keyStorePassword")
  public String providesKeyStorePassword(WebmonitorConfiguration configuration) {
    return configuration.getKeyStorePassword();
  }

  @Provides
  @Named("keyStorePath")
  public String providesKeyStorePath(WebmonitorConfiguration configuration) {
    return configuration.getKeyStorePath();
  }

  /**
   * Get a factory that builds connections.
   *
   * @param url              The Ords Endpoint URL
   * @param keyStorePassword The password for the keystore
   * @param keyStorePath     The path to the keystore
   *
   * @return HttpUrlConnectionFactoryHttpsImpl if the ORDs endpoint is https.
   */
  

 // @Provides
 // public HttpUrlConnectionFactory providesUrlConnectionFactory(
 //     @Named("ordsEndpoint") Provider<URI> url,
 //     @Named("keyStorePassword") Provider<String> keyStorePassword,
 //     @Named("keyStorePath") Provider<String> keyStorePath) throws MalformedURLException {
 //   if (url.get().toURL().getProtocol().toUpperCase().equals("HTTPS")) {
 //     return new HttpUrlConnectionFactoryHttpsImpl(keyStorePassword, keyStorePath);
 //   } else {
 //     return new HttpUrlConnectionFactoryHttpImpl();
 //   }
 // }

}
