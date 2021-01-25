package com.bonial.challenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {
    private String name;
    private String type;
    private String id;
    @JsonProperty("opening-hours")
    private String openingHours;
    private String image;
    private int radius;
    private String coordinates;
}
