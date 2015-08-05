package uk.gov.ho.api.webmonitor.core;

/**
 * Created by mooret on 04/08/15.
 */
public class RequestLogItem {



  private int id;
  private String urlText;
  private String responseText;
  private String responseCode;
  public RequestLogItem() {}

  public RequestLogItem (String urltext, String responsetext, String responsecode) {
    this.urlText = urltext;
    this.responseText = responsetext;
    this.responseCode = responsecode;
  }


  public int getId() {
    return id;
  }

  public void setId( int id ) {
    this.id = id;
  }

  public String getUrlText() {
    return urlText;
  }

  public void setUrlText( String urltext ) {
    this.urlText = urltext;
  }

  public String getResponseText() {
    return responseText;
  }

  public void setResponseText( String responsetext ) {
    this.responseText = responsetext;
  }

  public String getResponseCode() {
    return responseCode;
  }

  public void setResponseCode( String codetext ) {
    this.responseCode = codetext;
  }

}
