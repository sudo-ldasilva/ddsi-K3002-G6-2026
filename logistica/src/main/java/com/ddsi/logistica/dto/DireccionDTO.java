package com.ddsi.logistica.dto;

public class DireccionDTO {
    private String address;
    private float latitude;
    private float longitude;

    public DireccionDTO(String address, float latitude, float longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }
}
