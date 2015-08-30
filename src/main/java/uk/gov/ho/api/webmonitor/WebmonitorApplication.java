package uk.gov.ho.api.webmonitor;


import uk.gov.ho.api.webmonitor.configuration.WebmonitorConfiguration;
//import uk.gov.ho.api.webmonitor.core.WebMonitorClient;
//import uk.gov.ho.api.webmonitor.dependencyinjection.EventModule;
import uk.gov.ho.api.webmonitor.resources.UrlResponseResource;
import uk.gov.ho.api.webmonitor.resources.WebmonitorResource;

import com.hubspot.dropwizard.guice.GuiceBundle;

import de.thomaskrille.dropwizard_template_config.TemplateConfigBundle;
import io.dropwizard.Application;
//import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class WebmonitorApplication extends Application<WebmonitorConfiguration> {


  public static void main(String[] args) throws Exception {
    new WebmonitorApplication().run(args);

  }
  

  @Override
  public String getName() {
    return "webmonitor";
  }

  @Override
  public void initialize(Bootstrap<WebmonitorConfiguration> bootstrap) {
    bootstrap.addBundle(new AssetsBundle("/assets", "/", "index.html"));

  }
  
  @Override
  public void run(WebmonitorConfiguration configuration, Environment environment) {

    environment.jersey().register(new WebmonitorResource(configuration));
    environment.jersey().register(new UrlResponseResource());

  }


    






  
    
    
}
