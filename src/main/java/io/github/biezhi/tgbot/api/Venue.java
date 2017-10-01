package io.github.biezhi.tgbot.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class Venue implements Serializable {
    private final static long serialVersionUID = 0L;

    private Location location;
    private String   title;
    private String   address;
    private String   foursquare_id;

}
