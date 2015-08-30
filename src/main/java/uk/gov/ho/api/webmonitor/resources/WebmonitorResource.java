package uk.gov.ho.api.webmonitor.resources;


import uk.gov.ho.api.webmonitor.WebmonitorApplication;
import uk.gov.ho.api.webmonitor.configuration.WebmonitorConfiguration;
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

  private Timer timer;
  private TimerTask task;
  private  String url;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public WebmonitorResource(WebmonitorConfiguration configuration) {
    this.setUrl(configuration.getWebmonitorTestUrl());
  }

  /** start poll. */
  @Path("/start")
  @GET
  @Produces(MediaType.TEXT_PLAIN)

  public String start() {


    timer = new Timer();
    task = new WebMonitorClient(getUrl());
    String started = "Started";
    timer.schedule(task, 0, 3000);
    return started;

  }
  /** stop poll. */
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
  
  
