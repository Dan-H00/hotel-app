package com.example.hotel_app.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Root {
    public String ip_address;
    public Object city;
    public Object city_geoname_id;
    public Object region;
    public Object region_iso_code;
    public Object region_geoname_id;
    public Object postal_code;
    public String country;
    public String country_code;
    public int country_geoname_id;
    public boolean country_is_eu;
    public String continent;
    public String continent_code;
    public int continent_geoname_id;
    public double longitude;
    public double latitude;
    public Security security;
    public Timezone timezone;
    public Flag flag;
    public Currency currency;
    public Connection connection;
}
