package com.ddsi.logistica.dto;

import java.util.ArrayList;
import java.util.UUID;

public class RequestDTO {
    private UUID requestId;
    private TimeWindowDTO timeWindow;
    private DireccionDTO warehouse;
    private ArrayList<EntregaDTO> deliveries;
    private ArrayList<CamionDTO> trucks;

    public RequestDTO(TimeWindowDTO timeWindow, DireccionDTO warehouse, ArrayList<EntregaDTO> deliveries, ArrayList<CamionDTO> trucks) {
        this.requestId = UUID.randomUUID();
        this.timeWindow = timeWindow;
        this.warehouse = warehouse;
        this.deliveries = deliveries;
        this.trucks = trucks;
    }

    public UUID getRequestId() {
        return requestId;
    }

    public TimeWindowDTO getTimeWindow() {
        return timeWindow;
    }

    public DireccionDTO getWarehouse() {
        return warehouse;
    }

    public ArrayList<EntregaDTO> getDeliveries() {
        return deliveries;
    }

    public ArrayList<CamionDTO> getTrucks() {
        return trucks;
    }
}
