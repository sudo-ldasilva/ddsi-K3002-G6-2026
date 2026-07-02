package com.ddsi.donaciones.domain;

public class Documento {
    TipoDocumento tipoDocumento;
    String documento;

    public Documento(TipoDocumento tipoDocumento, String documento) {
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
    }

    public String getDocumento() {
        return documento;
    }

    public TipoDocumento getTipoDocumento() { return tipoDocumento; }
}
