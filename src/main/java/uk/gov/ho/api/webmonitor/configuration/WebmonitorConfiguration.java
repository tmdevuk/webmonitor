package uk.gov.ho.api.webmonitor.configuration;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.HttpClientConfiguration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/**
 * Represents the format configuration yml file to be read and provides access.
 */
public class WebmonitorConfiguration extends Configuration {

  @NotNull
  private HttpClientConfiguration httpClient = new HttpClientConfiguration();

  @JsonProperty("httpClient")
  public HttpClientConfiguration getHttpClientConfiguration() {
    return httpClient;
  }

  @JsonProperty
  public String getWebmonitorTestUrl() {
    return webmonitorTestUrl;
  }

  @JsonProperty
  public void setWebmonitorTestUrl(String webmonitorTestUrl) {
    this.webmonitorTestUrl = webmonitorTestUrl;
  }

  @NotEmpty
  @JsonProperty("webmonitorTestUrl")
  private String webmonitorTestUrl;


}
