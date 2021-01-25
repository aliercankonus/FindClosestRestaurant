package com.bonial.challenge.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocationResource {
    private String name;
    private String type;
    private String id;
    @JsonProperty("opening-hours")
    private String openingHours;
    private String image;
    private String coordinates;
}
