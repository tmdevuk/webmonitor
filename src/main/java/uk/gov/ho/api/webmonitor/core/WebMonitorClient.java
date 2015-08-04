package uk.gov.ho.api.webmonitor.core;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.TimerTask;




public class WebMonitorClient extends TimerTask {

  //WebmonitorConfiguration configuration = new WebmonitorConfiguration();
  //String testurl = configuration.getWebmonitorTestURL();
  CloseableHttpClient httpclient = HttpClients.createDefault();
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
    try {
      String mystring = poll();
      System.out.println(mystring);

      myOutput.append(mystring);
      myOutput.println();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /** poll action. */
  public String poll()  {
    String status;
    CloseableHttpResponse response1 = null;
    try {
      response1 = httpclient.execute(httpGet);
    } catch (Exception e) {
      status = e.getMessage();
      System.out.println(status);
      return status;
    } finally {

      if (response1 != null) {
        try {
          response1.close();
        } catch (IOException e) {
          System.out.println(e.getMessage());
        }
      }

    }
    status = response1.getStatusLine().toString();
    Integer statuscode = response1.getStatusLine().getStatusCode();
    return status;



  }

}
