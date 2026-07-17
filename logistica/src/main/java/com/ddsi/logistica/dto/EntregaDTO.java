package com.ddsi.logistica.dto;

public class EntregaDTO {
    private String deliveryCode;
    private float latitude;
    private float longitude;
    private String address;
    private float WeightKg;
    private float VolumeM3;

    public EntregaDTO(String deliveryCode, float latitude, float longitude, String address, float weightKg, float volumeM3) {
        this.deliveryCode = deliveryCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        WeightKg = weightKg;
        VolumeM3 = volumeM3;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public float getWeightKg() {
        return WeightKg;
    }

    public float getVolumeM3() {
        return VolumeM3;
    }
}
