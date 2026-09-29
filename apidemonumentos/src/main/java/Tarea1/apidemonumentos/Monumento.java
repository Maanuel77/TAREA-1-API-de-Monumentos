package Tarea1.apidemonumentos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Monumento {

    @Id
    @GeneratedValue
    private Long id;

    private String codigoPais;
    private String nombrePais;
    private String nombreCiudad;
    private Double latitud;
    private Double longitud;
    private String nombreMonumento;
    private String descripcion;
    private String urlFoto;

}
