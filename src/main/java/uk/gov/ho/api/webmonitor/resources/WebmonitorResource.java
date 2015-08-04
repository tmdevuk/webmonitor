package uk.gov.ho.api.webmonitor.resources;


import uk.gov.ho.api.webmonitor.core.WebMonitorClient;

import java.util.Timer;
import java.util.TimerTask;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;




//import javax.ws.rs.core.Response;



@Path("/webmonitor")
@Produces(MediaType.TEXT_PLAIN)

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

}
  
  
