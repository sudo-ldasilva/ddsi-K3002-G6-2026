package com.ddsi.donaciones.domain;

import com.ddsi.donaciones.domain.dto.CampaniaNecesidadDTO;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CampaniaNecesidadExtraordinaria extends CampaniaNecesidad {
    private String situacionExcepcional;

    public CampaniaNecesidadExtraordinaria(CampaniaNecesidadDTO campaniaDto, EntidadBeneficiaria eb) {
        this(
            eb,
            campaniaDto.getDescripcion(),
            null,
            campaniaDto.getSituacionExcepcional()
        );
        this.necesidades = campaniaDto.getNecesidades().stream().map(n -> new NecesidadIndividual(n, this)).collect(Collectors.toCollection(ArrayList::new));
    }

    public CampaniaNecesidadExtraordinaria(EntidadBeneficiaria entidadBeneficiaria, String descripcion, ArrayList<NecesidadIndividual> necesidades, String situacionExcepcional) {
        super(entidadBeneficiaria, descripcion, necesidades);
        this.situacionExcepcional = situacionExcepcional;
    }

    public String getSituacionExcepcional() {
        return situacionExcepcional;
    }

    public void setSituacionExcepcional(String situacionExcepcional) {
        this.situacionExcepcional = situacionExcepcional;
    }

    @Override
    public CampaniaNecesidadDTO toDTO() {
        return new CampaniaNecesidadDTO(getUuid(),"Extraordinaria",necesidades.stream().map( n -> n.toDTO() ).collect(Collectors.toCollection(ArrayList::new)),descripcion,null,situacionExcepcional);
    }
}
