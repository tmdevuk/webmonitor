package uk.gov.ho.api.webmonitor.core;

import java.util.Date;

/**
 * Created by mooret on 04/08/15.
 */
public class UrlResponse {



  private int id;
  private String urlText;
  private String responseText;
  private String responseCode;
  private double responseDuration;
  private Date responseDate;

  public UrlResponse() {}

  /** create new response. */
  public UrlResponse(String urltext, String responsetext, String responsecode,
                     double responseduration, Date responsedate) {
    this.urlText = urltext;
    this.responseText = responsetext;
    this.responseCode = responsecode;
    this.responseDuration = responseduration;
    this.responseDate = responsedate;
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

  public double getResponseDuration() {
    return responseDuration;
  }

  public void setResponseDuration(double responseduration) {
    this.responseDuration = responseduration;
  }

  public Date getResponseDate() {
    return responseDate;
  }

  public void setResponseDate( Date responsedate ) {
    this.responseDate = responsedate;
  }






}
