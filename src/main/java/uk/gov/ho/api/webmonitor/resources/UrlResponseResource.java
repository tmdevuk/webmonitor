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

@Path("/responses")
@Produces(MediaType.APPLICATION_JSON)

public class UrlResponseResource {

  /** get all responses. (max limit set in dao) */

  @GET

  public List<UrlResponse> getAllResponses() {
    UrlResponseDao dao = new UrlResponseDao();
    return dao.getAllUrlResponses();

  }




}


