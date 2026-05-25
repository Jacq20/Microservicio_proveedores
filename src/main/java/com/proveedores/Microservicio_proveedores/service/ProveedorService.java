package com.proveedores.Microservicio_proveedores.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proveedores.Microservicio_proveedores.DTO.ProveedorDTO;
import com.proveedores.Microservicio_proveedores.model.Proveedor;
import com.proveedores.Microservicio_proveedores.repository.ProveedorRepository;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public Proveedor registrarProveedor(ProveedorDTO dto) {
        try {
            if (proveedorRepository.existsByRutNif(dto.getRutNif())) {
                throw new RuntimeException("Ya existe un proveedor con ese RUT");
            } 
             Proveedor proveedor = new Proveedor();
            proveedor.setRazonSocial(dto.getRazonSocial());
            proveedor.setRutNif(dto.getRutNif());
            proveedor.setContacto(dto.getContacto());
            proveedor.setCatalogoProductos(dto.getCatalogoProductos());
            proveedor.setCalificacion(dto.getCalificacion());
            proveedor.setCondicionesPago(dto.getCondicionesPago());
            proveedor.setEstadoAprobacion(Proveedor.EstadoAprobacion.PENDIENTE);
            return proveedorRepository.save(proveedor);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public List<Proveedor> listarProveedores() {
        return proveedorRepository.findAll();
    }

    public Optional<Proveedor> buscarPorId(Long id) {
        return proveedorRepository.findById(id);
    }

    public Optional<Proveedor> aprobarProveedor(Long id) {
        try {
            Optional<Proveedor> proveedor = proveedorRepository.findById(id);
            proveedor.ifPresent(p -> {
                p.setEstadoAprobacion(Proveedor.EstadoAprobacion.APROBADO);
                proveedorRepository.save(p);
            });
            return proveedor;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public Optional<Proveedor> evaluarProveedor(Long id, BigDecimal calificacion) {
        try {
            Optional<Proveedor> proveedor = proveedorRepository.findById(id);
            proveedor.ifPresent(p -> {
                p.setCalificacion(calificacion);
                proveedorRepository.save(p);
            });
            return proveedor;
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
