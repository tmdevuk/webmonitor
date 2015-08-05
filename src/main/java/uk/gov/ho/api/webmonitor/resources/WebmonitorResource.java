package uk.gov.ho.api.webmonitor.resources;


import uk.gov.ho.api.webmonitor.core.UrlResponse;
import uk.gov.ho.api.webmonitor.core.WebMonitorClient;
import uk.gov.ho.api.webmonitor.dao.UrlResponseDao;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/webmonitor")
@Produces(MediaType.APPLICATION_JSON)

public class WebmonitorResource {


  TimerTask task = new WebMonitorClient();
  Timer timer = new Timer();


  public WebmonitorResource() {

  }

  /** main rest point. */
  @Path("/start")
  @GET
  @Produces(MediaType.TEXT_PLAIN)



  public String start() {



    String started = "Started";
    timer.schedule(task, 3000, 3000);
    return started;

  }
  /** stop service. */
  @Path("/stop")
  @GET
  @Produces(MediaType.TEXT_PLAIN)

  public String stop() {
    task.cancel();
    timer.cancel();
    timer.purge();
    String stopped = "Stopped";
    return stopped;

  }



  /** get all responses. */
  @Path("/responses")
  @GET
  @Produces(MediaType.APPLICATION_JSON)

  public List<UrlResponse> getAllResponses() {

    UrlResponseDao dao = new UrlResponseDao();
    List myList = dao.getAllUrlResponses();
    return myList;

  }






}
  
  
