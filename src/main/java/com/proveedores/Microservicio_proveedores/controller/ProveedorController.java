package com.proveedores.Microservicio_proveedores.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proveedores.Microservicio_proveedores.DTO.ProveedorDTO;
import com.proveedores.Microservicio_proveedores.model.Proveedor;
import com.proveedores.Microservicio_proveedores.service.ProveedorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {
    
    @Autowired
    private ProveedorService proveedorService;

     @GetMapping
    public ResponseEntity<List<Proveedor>> getProveedores() {
        List<Proveedor> lista = proveedorService.listarProveedores();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> postProveedor(@Valid @RequestBody ProveedorDTO dto) {
        try {
            Proveedor nuevo = proveedorService.registrarProveedor(dto);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getProveedor(@PathVariable Long id) {
        Proveedor buscado = proveedorService.buscarPorId(id).orElse(null);
        if (buscado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(buscado, HttpStatus.OK);
    }
    
    @PutMapping("/{id}/aprobar")
    public ResponseEntity<?> aprobarProveedor(@PathVariable Long id) {
        try {
            Proveedor aprobado = proveedorService.aprobarProveedor(id).orElse(null);
            if (aprobado == null) {
                return new ResponseEntity<>("Proveedor no encontrado", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(aprobado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}/evaluar")
    public ResponseEntity<?> evaluarProveedor(
            @PathVariable Long id,
            @RequestParam BigDecimal calificacion) {
        try {
            Proveedor evaluado = proveedorService.evaluarProveedor(id, calificacion).orElse(null);
            if (evaluado == null) {
                return new ResponseEntity<>("Proveedor no encontrado", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(evaluado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
