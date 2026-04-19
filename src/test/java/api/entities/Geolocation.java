package api.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Geolocation{
    public String lat;
    @JsonProperty("long")
    public String myLong;

    public Geolocation(String lat, String myLong) {
        this.lat = lat;
        this.myLong = myLong;
    }
    public Geolocation(){}

    public String getLat() {
        return lat;
    }

    public String getMyLong() {
        return myLong;
    }
}