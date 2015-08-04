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
  
  @NotEmpty
  @JsonProperty("uiUri")
  private String uiUri;

  @NotEmpty
  @JsonProperty("ordsEndpoint")
  private String ordsEndpoint;

  @NotNull
  @JsonProperty("swagger")
  private SwaggerBundleConfiguration swagger;

  @JsonProperty(value = "keyStorePath", required = false) private String keyStorePath;

  @Nullable
  @JsonProperty(value = "keyStorePassword", required = false)
  private String keyStorePassword;
  @NotNull
  @JsonProperty("circuitBreaker")
  private CircuitBreakerConfiguration circuitBreaker;

  @NotEmpty
  @JsonProperty("webmonitorTestUrl")
  private String webmonitorTestUrl;



  /**
   * Get the swagger resource package.
   * <p/>
   *
   * @return The swagger resource package
   */
  
  public SwaggerBundleConfiguration swaggerBundleConfiguration() {
    return swagger;
  }

  public String getUiUri() {
    return uiUri;
  }

  public String getOrdsEndpoint() {
    return ordsEndpoint;
  }

  public String getKeyStorePath() {
    return keyStorePath;
  }

  public String getKeyStorePassword() {
    return keyStorePassword;
  }

  public CircuitBreakerConfiguration getCircuitBreaker() {
    return circuitBreaker;
  }

  public String getWebmonitorTestUrl()  {
    return webmonitorTestUrl;
  }
  
}
