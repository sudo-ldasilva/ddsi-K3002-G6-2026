package com.ddsi.logistica.dto;

public class CamionDTO {
    private String truckId;
    private float weightCapacityKg;
    private float volumeCapacityM3;

    public CamionDTO(String truckId, float weightCapacityKg, float volumeCapacityM3) {
        this.truckId = truckId;
        this.weightCapacityKg = weightCapacityKg;
        this.volumeCapacityM3 = volumeCapacityM3;
    }

    public String getTruckId() {
        return truckId;
    }

    public float getWeightCapacityKg() {
        return weightCapacityKg;
    }

    public float getVolumeCapacityM3() {
        return volumeCapacityM3;
    }
}
