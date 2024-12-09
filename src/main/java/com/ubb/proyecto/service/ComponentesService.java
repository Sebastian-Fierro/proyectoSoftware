package com.ubb.proyecto.service;

import com.ubb.proyecto.model.Componentes;
import com.ubb.proyecto.model.Rol;
import com.ubb.proyecto.repository.RepositorioComponentes;
import com.ubb.proyecto.repository.RepositorioRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComponentesService {
    @Autowired
    private RepositorioComponentes componentesRepository;

    public List<Componentes> getAllComponentes(){
        return componentesRepository.findAll();
    }

    public Optional<Componentes> getComponentesById(Integer id){
        return componentesRepository.findById(id);
    }

    public Componentes saveComponentes(Componentes componentes) {
        return componentesRepository.save(componentes);
    }

    public Componentes updateComponentes(Integer id, Componentes componentesDetails) {
        Componentes componentes = componentesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Departamento no disponible"));

        componentes.setNombre(componentesDetails.getNombre());

        return componentesRepository.save(componentes);
    }

    public void deleteComponentes(Integer id){
        componentesRepository.deleteById(id);
    }
}
