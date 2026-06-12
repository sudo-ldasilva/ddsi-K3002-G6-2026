package com.ddsi.donaciones.controller;

import com.ddsi.donaciones.domain.CargaDeDatosDesdeCSV;
import com.ddsi.donaciones.domain.Donante;
import com.ddsi.donaciones.domain.GestorDonantes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/donantes")
public class CargaDonanteController {
 //try, catch, finally
    @PostMapping("/carga-csv")
    public ResponseEntity<String> cargarDesdeCSV(@RequestParam("archivo") MultipartFile archivo)
            throws IOException {

        if (archivo.isEmpty()) {
            return ResponseEntity.badRequest().body("El archivo está vacío.");
        }

        // Guardamos el archivo en un temporal para que pueda leerlo por ruta (FileReader).
        File temporal = File.createTempFile("donantes_", ".csv");
        try {
            archivo.transferTo(temporal);

            GestorDonantes gestor = GestorDonantes.getInstance();
            List<Donante> donantesActuales = gestor.getDonantes();

            CargaDeDatosDesdeCSV carga = new CargaDeDatosDesdeCSV();

            // validamos (detecta duplicados y los reporta por consola).
            List<Donante> validados = carga.validarDatos(donantesActuales, temporal.getAbsolutePath());

            List<Donante> resultado = carga.cargarDonantes(donantesActuales, temporal.getAbsolutePath());

            // Registramos sólo los que son nuevos
            int nuevos = 0;
            for (Donante d : resultado) {
                if (!donantesActuales.contains(d)) {
                    gestor.registrarDonante(d);
                    nuevos++;
                }
            }

            return ResponseEntity.ok(
                    String.format("Carga completada. Registros procesados: %d. Donantes nuevos: %d.",
                            resultado.size(), nuevos));

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error al procesar el CSV: " + e.getMessage());
        } finally {
            temporal.delete();
        }
    }
}