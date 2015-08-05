package uk.gov.ho.api.webmonitor.resources;

import uk.gov.ho.api.webmonitor.core.UrlResponse;

import uk.gov.ho.api.webmonitor.dao.UrlResponseDao;
import uk.gov.ho.api.webmonitor.util.HibernateUtil;

import org.hibernate.Session;

import java.util.List;

import javax.ws.rs.GET;

import javax.ws.rs.Path;

import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;

/**
 * Created by mooret on 04/08/15.
 */

@Path("/response")
@Produces(MediaType.APPLICATION_JSON)


public class UrlResponseResource {



  /** sample doc. */
  public UrlResponseResource() {

  }



  /** get all responses. */
  @GET
  @Produces(MediaType.APPLICATION_JSON)

  public List getAllResponses() {

    UrlResponseDao dao = new UrlResponseDao();
    return dao.getAllUrlResponses();


  }

  //public String addUser(@FormParam("firstname") String fname,
  //@FormParam("lastname") String lname) {



  //@GET
  //@Produces(MediaType.TEXT_PLAIN)
  //public String getNamedGreeting() {
  //  return "Hello";
  //}
}


