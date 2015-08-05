package uk.gov.ho.api.webmonitor.core;

import uk.gov.ho.api.webmonitor.configuration.WebmonitorConfiguration;
import uk.gov.ho.api.webmonitor.util.HibernateUtil;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.hibernate.Session;
import org.joda.time.Instant;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


public class WebMonitorClient extends TimerTask {

  //WebmonitorConfiguration configuration = new WebmonitorConfiguration();


  CloseableHttpClient httpclient = HttpClients.createDefault();
  //String myUrl = configuration.getWebmonitorTestUrl();
  //String myUrl = "http://www.google.co.uk";
  String myUrl = "http://flappy.purplebooth.co.uk/flappy";

  HttpGet httpGet = new HttpGet(myUrl);
  PrintStream myOutput;


  /** web monitor class. */
  public WebMonitorClient() {
    //try {
     // PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
    //  myOutput = out;
    //} catch (FileNotFoundException e) {
     // System.out.println("FileNotFoundException");
    //}
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
      //HibernateUtil.getSessionFactory().close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /** poll action. */
  public UrlResponse poll()  {
    UrlResponse myRes = new UrlResponse();
    CloseableHttpResponse response1 = null;
    double duration;
    try {

      Long t1 = System.nanoTime();
      response1 = httpclient.execute(httpGet);
      Long t2 = System.nanoTime();

      //duration = TimeUnit.NANOSECONDS.toSeconds(t1 - t2);
      //duration = ((double) t2 - (double) t1) / 1000000000;
      duration = ((double) t2 - (double) t1) / 1000000000;
    } catch (Exception e) {
      String err = e.getMessage();
      System.out.println(err);

      myRes.setResponseText(e.getMessage());
      myRes.setUrlText(myUrl);
      return myRes;

    } finally {

      if (response1 != null) {
        try {
          response1.close();
        } catch (IOException e) {
          System.out.println(e.getMessage());
        }
      }
    }

    myRes.setResponseText(response1.getStatusLine().toString());
    Integer myStatusCode = response1.getStatusLine().getStatusCode();
    myRes.setResponseCode(myStatusCode.toString());
    myRes.setUrlText(myUrl);
    myRes.setResponseDuration(duration);
    myRes.setResponseDate(new Date());
    //status = response1.getStatusLine().toString();
    //Integer statuscode = (Integer)response1.getStatusLine().getStatusCode();
    return myRes;

  }

}
