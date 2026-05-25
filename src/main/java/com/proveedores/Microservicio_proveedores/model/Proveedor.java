package com.proveedores.Microservicio_proveedores.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProveedor;

    @Column(length = 150, nullable = false)
    private String razonSocial;

    @Column(length = 20, nullable = false, unique = true)
    private String rutNif;

    @Column(length = 100)
    private String contacto;

    @ElementCollection
    private List<String> catalogoProductos;

    @Enumerated(EnumType.STRING)
    private EstadoAprobacion estadoAprobacion;

     private BigDecimal calificacion;

    @Column(length = 255)
    private String condicionesPago;

    public enum EstadoAprobacion {
        PENDIENTE, APROBADO, RECHAZADO
    }
}
