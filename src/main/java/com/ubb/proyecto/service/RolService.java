package com.ubb.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubb.proyecto.model.Rol;
import com.ubb.proyecto.repository.RepositorioRol;

@Service
public class RolService {

    @Autowired
    private RepositorioRol rolRepository;

    public List<Rol> getAllRol(){
    return rolRepository.findAll();
    }

    public Optional<Rol> getRolById(Integer id){
        return rolRepository.findById(id);
    }

    public Rol updateRol(Integer id, Rol rolDetails) {
        Rol rol = rolRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Rol no disponible"));

    rol.setNombre(rolDetails.getNombre());

    return rolRepository.save(rol);
    }

    public void deleteRol(Integer id){
        rolRepository.deleteById(id);
    }

}
