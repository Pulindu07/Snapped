package com.snapped.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoogleResponse {

    @JsonProperty("name")
    private String name;

    @JsonProperty("url")
    private String url;

}
