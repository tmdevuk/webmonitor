package uk.gov.ho.api.webmonitor.resources;


import org.apache.http.client.ClientProtocolException;
//import org.apache.http.HttpResponse;
//import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//import javax.ws.rs.core.Response;

@Path("/webmonitor")
@Produces(MediaType.TEXT_PLAIN)

public class WebmonitorResource {

  //private final HttpClient httpClient;
  //String url = "http://www.google.com/search?q=httpClient";
  //HttpGet request = new HttpGet(url);
  
  //HttpClient client = HttpClientBuilder.create().build();
  CloseableHttpClient httpclient = HttpClients.createDefault();
  HttpGet httpGet = new HttpGet("http://www.google.co.uk");

  
  // add request header
  // ...

  /** sample doc. */
  //public WebmonitorResource() {
  //}
  
  
 // UserResource(UserDao dao) {
 //   this.dao = dao;
 // }
  

  

  
  public WebmonitorResource() {
    

  }
  
  /** test2. */
  //public WebmonitorResource(HttpClient httpClient) {
    
    //this.httpClient = httpClient;

  //}

 /** test.
 //  * @throws IOException test
 //  * @throws ClientProtocolException test */
  /** test.*/
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  
  
    public String poll() throws ClientProtocolException, IOException  {
    
    CloseableHttpResponse response1 = httpclient.execute(httpGet);
    try {
      System.out.println(response1.getStatusLine());
      //HttpEntity entity1 = response1.getEntity();
      // do something useful with the response body
      // and ensure it is fully consumed
      //EntityUtils.consume(entity1);
    } finally {
      response1.close();
    }
    
    
    
    //HttpResponse response = client.execute(request);

    //System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
    
    
//    String url = "http://www.google.com/search?q=httpClient";
//    
//    HttpGet request = new HttpGet(url);
// 
//    // add request header
//    request.setHeader("User-Agent", "MySuperUserAgent");
//    HttpResponse response;
//    try {
//      response = httpClient.execute(request);
//      System.out.println("Response Code : " 
//          + response.getStatusLine().getStatusCode());
//    } catch (ClientProtocolException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    } catch (IOException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
// 

      
   // return "test";
    String response = response1.getStatusLine().toString();
    return response;
  }
    

}
  
  
  
