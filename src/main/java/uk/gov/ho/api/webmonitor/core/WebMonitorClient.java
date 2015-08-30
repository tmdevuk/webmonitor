package uk.gov.ho.api.webmonitor.core;

import uk.gov.ho.api.webmonitor.configuration.WebmonitorConfiguration;
import uk.gov.ho.api.webmonitor.util.HibernateUtil;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.hibernate.Session;
import org.joda.time.DateTime;
import org.joda.time.Instant;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class WebMonitorClient extends TimerTask {

  private String myUrl;
  private CloseableHttpClient httpclient = HttpClients.createDefault();

  HttpGet httpGet;

  public String getMyUrl() {
    return myUrl;
  }

  public void setMyUrl(String myUrl) {
    this.myUrl = myUrl;
    httpGet = new HttpGet(this.getMyUrl());
  }

  /**  constructor takes target url. */
  public WebMonitorClient(String url) {

    this.setMyUrl(url);

  }


  @Override
  public void run() {

    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    UrlResponse myResponse;
    try {
      myResponse = poll();
      session.beginTransaction();
      session.save(myResponse);
      session.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /** poll function. */
  public UrlResponse poll() {
    UrlResponse myResponse = new UrlResponse();
    CloseableHttpResponse response = null;
    double duration;

    try {

      Long t1 = System.nanoTime();
      response = httpclient.execute(httpGet);
      Long t2 = System.nanoTime();
      duration = ((double) t2 - (double) t1) / 1000000000;

      myResponse.setResponseText(response.getStatusLine().toString());
      myResponse.setResponseCode(response.getStatusLine().getStatusCode());
      myResponse.setUrlText(myUrl);
      myResponse.setResponseDuration(duration);
      myResponse.setResponseDate(new DateTime());

      return myResponse;


      // socketexception
      // unknownhostexception
    } catch (Exception e) {
      String err = e.getMessage();
      System.out.println(err);
      myResponse.setResponseDate(new DateTime());
      myResponse.setResponseText(e.getMessage());
      myResponse.setUrlText(myUrl);
      myResponse.setResponseDuration(10);
      myResponse.setResponseCode(0);

      return myResponse;



    } finally {
      if (response != null) {
        try {
          response.close();
        } catch (IOException e) {
          System.out.println(e.getMessage());
        }

      }


    }






  }



}

