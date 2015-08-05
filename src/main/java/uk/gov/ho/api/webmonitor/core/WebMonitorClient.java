package uk.gov.ho.api.webmonitor.core;

import uk.gov.ho.api.webmonitor.configuration.WebmonitorConfiguration;
import uk.gov.ho.api.webmonitor.util.HibernateUtil;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.hibernate.Session;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.TimerTask;


public class WebMonitorClient extends TimerTask {

  WebmonitorConfiguration configuration = new WebmonitorConfiguration();


  CloseableHttpClient httpclient = HttpClients.createDefault();
  //String myUrl = configuration.getWebmonitorTestUrl();
  String myUrl = "http://www.google.co.uk";
  HttpGet httpGet = new HttpGet("http://www.google.co.uk");
  PrintStream myOutput;


  /** web monitor class. */
  public WebMonitorClient() {
    try {
      PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
      myOutput = out;
    } catch (FileNotFoundException e) {
      System.out.println("FileNotFoundException");
    }
  }


  @Override
  public void run() {


    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    RequestLogItem myResponse;
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
  public RequestLogItem poll()  {
    RequestLogItem myRes = new RequestLogItem();
    CloseableHttpResponse response1 = null;
    try {
      response1 = httpclient.execute(httpGet);

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
    //status = response1.getStatusLine().toString();
    //Integer statuscode = (Integer)response1.getStatusLine().getStatusCode();
    return myRes;



  }

}
