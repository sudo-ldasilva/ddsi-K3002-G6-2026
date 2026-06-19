package com.ddsi.donaciones.domain.dto;

import com.ddsi.donaciones.domain.*;
import java.util.ArrayList;

public class DonacionesPorMailDTO {
    private String mailDonante;
    private ArrayList<DonacionIndependienteDTO> donaciones;

    public DonacionesPorMailDTO(String mailDonante, ArrayList<DonacionIndependienteDTO> donaciones) {
        this.mailDonante = mailDonante;
        this.donaciones = donaciones;
    }

    public String getMailDonante() { return mailDonante; }
    public void setMailDonante(String mailDonante) { this.mailDonante = mailDonante; }
    public ArrayList<DonacionIndependienteDTO> getDonaciones() { return donaciones; }
    public void setDonaciones(ArrayList<DonacionIndependienteDTO> donaciones) { this.donaciones = donaciones; }
}
