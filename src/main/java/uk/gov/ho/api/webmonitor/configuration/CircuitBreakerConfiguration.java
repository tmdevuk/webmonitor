package uk.gov.ho.api.webmonitor.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class CircuitBreakerConfiguration {

  @NotNull
  @JsonProperty("maxErrors")
  private Integer maxErrors;

  @NotNull
  @JsonProperty("callTimeout")
  private Integer callTimeout;

  @NotNull
  @JsonProperty("resetTimeout")
  private Integer resetTimeout;


  public Integer getMaxErrors() {
    return maxErrors;
  }

  public Integer getCallTimeout() {
    return callTimeout;
  }


  public Integer getResetTimeout() {
    return resetTimeout;
  }
}
