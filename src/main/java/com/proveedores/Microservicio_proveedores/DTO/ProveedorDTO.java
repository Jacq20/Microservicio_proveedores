package com.proveedores.Microservicio_proveedores.DTO;

import java.math.BigDecimal;
import java.util.List;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorDTO {
    
    @NotBlank(message = "La razón social es obligatoria")
    @Size(max = 150, message = "Máximo 150 caracteres")
    private String razonSocial;

    @NotBlank(message = "El RUT es obligatorio")
    @Size(max = 20, message = "Máximo 20 caracteres")
    private String rutNif;

    @Size(max = 100, message = "Máximo 100 caracteres")
    private String contacto;

    private List<String> catalogoProductos;

    @DecimalMin(value = "0.0", message = "Mínimo 0.0")
    @DecimalMax(value = "5.0", message = "Máximo 5.0")
    private BigDecimal calificacion;

    @Size(max = 255, message = "Máximo 255 caracteres")
    private String condicionesPago;
}
