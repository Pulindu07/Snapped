package com.snapped.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    @JsonProperty("SnappedError")
    private SnappedError snapperError;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SnappedError{
        @JsonProperty("StatusCode")
        private String statusCode;

        @JsonProperty("StatusMessage")
        private String statusMessage;

        @JsonProperty("Date")
        private Date date;
    }
}
