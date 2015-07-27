package uk.gov.ho.api.webmonitor;

//import org.apache.http.impl.client.HttpClientBuilder;


import uk.gov.ho.api.webmonitor.configuration.WebmonitorConfiguration;
import uk.gov.ho.api.webmonitor.dependencyinjection.EventModule;
import uk.gov.ho.api.webmonitor.resources.WebmonitorResource;

import com.hubspot.dropwizard.guice.GuiceBundle;
import de.thomaskrille.dropwizard_template_config.TemplateConfigBundle;

import io.dropwizard.Application;
//import io.dropwizard.client.HttpClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

//import org.apache.http.client.HttpClient;

//import com.example.dwtest5.configuration.CustomSwaggerBundleConfiguration;





//import org.apache.http.client.HttpClient;




//import com.example.dwtest5.configuration.SwaggerDateModelConverter;
//import com.example.dwtest5.dao.UserDao;
//import com.example.dwtest5.dependencyinjection.EventModule;
//import com.example.dwtest5.entities.source.User;
//import com.example.dwtest5.resources.UserResource;
//import com.example.dwtest5.util.HibernateUtil;




//import com.wordnik.swagger.converter.ModelConverters;







public class WebmonitorApplication extends Application<WebmonitorConfiguration> {

  /**
   * Run the application.
   *
   * @param args Command line arguments
   *
   * @throws Exception Last ditch failure
   */
  public static void main(String[] args) throws Exception {
    new WebmonitorApplication().run(args);

  }
  

  /**
   * Name of the application for output.
   *
   * @return name of the application for logs
   */
  @Override
  public String getName() {
    return "webmonitor";
  }
  
  /**
   * Do init things like connect to DBs etc.
   *
   * @param bootstrap The bootstrap
   */
  @Override
  public void initialize(Bootstrap<WebmonitorConfiguration> bootstrap) {
    bootstrap.addBundle(new TemplateConfigBundle());
    GuiceBundle<WebmonitorConfiguration> guiceBundle = 
        GuiceBundle.<WebmonitorConfiguration>newBuilder()
        .addModule(new EventModule())
        .enableAutoConfig(getClass().getPackage().getName())
        .setConfigClass(WebmonitorConfiguration.class)
        .build();
    bootstrap.addBundle(guiceBundle);
    //bootstrap.addBundle(new CustomSwaggerBundleConfiguration());
  }

  /**
   * Run the web server. Adds endpoints.
   *
   * @param configuration Loaded configuration object
   * @param environment   Environment container
   */
  
  
  @Override
  
  
  
  public void run(WebmonitorConfiguration configuration, Environment environment) {
    
   // final HttpClient httpClient = new HttpClientBuilder(environment).using(
    //    configuration.getHttpClientConfiguration()).build("httpClient");
    

    //environment.jersey().register(new WebmonitorResource(httpClient));
    environment.jersey().register(new WebmonitorResource());
    
    //ModelConverters.getInstance().addConverter(new SwaggerDateModelConverter());
  //  final UserDao dao = new UserDao();
    //final UserResource resource = new UserResource();
    //environment.jersey().register(resource);
   // environment.jersey().register(new UserResource(dao));
    
    // hibernate session setup
  
//    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//    session.beginTransaction();
//    User myPerson = new User("john","smith");
//    session.save(myPerson);
//    session.getTransaction().commit();
//    HibernateUtil.getSessionFactory().close();
    
    
    //System.out.println("Complete");
    
    // register the user service
  }





  
    
    
}
