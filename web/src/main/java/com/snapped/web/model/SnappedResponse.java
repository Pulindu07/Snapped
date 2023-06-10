package com.snapped.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SnappedResponse {

    @JsonProperty("SnappedResponse")
    private List<PhotoDetails> photoDetails;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PhotoDetails{

        @JsonProperty("Id")
        private Long id;

        @JsonProperty("Title")
        private String title;

        @JsonProperty("Category")
        private String category;

        @JsonProperty("Description")
        private String description;

        @JsonProperty("Link")
        private String link;

        @JsonProperty("Orientation")
        private String orientation;

    }


}
