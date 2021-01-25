package com.bonial.challenge.resource;

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
public class RestaurantResource {
    private String id;
    private String name;
    private String coordinates;
    private Double distance;
}
