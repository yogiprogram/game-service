package com.demo.game.api.model;

import java.util.Objects;
import com.demo.game.api.model.Game;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * PlayStatus
 */
@Validated

public class PlayStatus   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("uri")
  private String uri = null;

  @JsonProperty("status")
  @Valid
  private Map<String, String> status = null;

  public PlayStatus id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "1234", value = "")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public PlayStatus uri(String uri) {
    this.uri = uri;
    return this;
  }

  /**
   * Get uri
   * @return uri
  **/
  @ApiModelProperty(example = "http://<host>:<port>/games/1234", value = "")


  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public PlayStatus status(Map<String, String> status) {
    this.status = status;
    return this;
  }

  public PlayStatus putStatusItem(String key, String statusItem) {
    if (this.status == null) {
      this.status = new HashMap<>();
    }
    this.status.put(key, statusItem);
    return this;
  }

  /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(value = "")


  public Map<String, String> getStatus() {
    return status;
  }

  public void setStatus(Map<String, String> status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PlayStatus playStatus = (PlayStatus) o;
    return Objects.equals(this.id, playStatus.id) &&
        Objects.equals(this.uri, playStatus.uri) &&
        Objects.equals(this.status, playStatus.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uri, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PlayStatus {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    uri: ").append(toIndentedString(uri)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

