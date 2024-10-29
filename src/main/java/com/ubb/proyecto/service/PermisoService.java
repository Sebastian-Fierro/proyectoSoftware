package com.ubb.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubb.proyecto.model.Permiso;
import com.ubb.proyecto.repository.RepositorioPermiso;

@Service

public class PermisoService {
    @Autowired
    private RepositorioPermiso permisoRepository;

    public List<Permiso> getAllPermiso() {
        return permisoRepository.findAll();
    }

    public Optional<Permiso> getPermisoById(Integer id) {
        return permisoRepository.findById(id);
    }

    public Permiso savePermiso(Permiso permiso) {
        return permisoRepository.save(permiso);
    }

    public Permiso updatePermiso(Integer id, Permiso permisoDetails) {
        Permiso permiso = permisoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permiso no encontrado"));

        permiso.setNombre(permisoDetails.getNombre());

        return permisoRepository.save(permiso);
    }

    public void deletePermiso(Integer id) {
        permisoRepository.deleteById(id);
    }
}
