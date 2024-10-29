package com.ubb.proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubb.proyecto.model.Suscripcion;
import com.ubb.proyecto.repository.RepositorioSuscripcion;

@Service
public class SuscripcionService {

    @Autowired
    private RepositorioSuscripcion suscripcionRepository; 

    public List<Suscripcion> getAllSuscriptions() {
        return suscripcionRepository.findAll();
    }

    public Optional<Suscripcion> getSuscripcionById(Integer id){
        return suscripcionRepository.findById(id);
    }

    public Suscripcion saveSuscripcion(Suscripcion suscripcion) {
        return suscripcionRepository.save(suscripcion);
    }

   public void deleteSuscripcion(Integer id){
    suscripcionRepository.deleteById(id);
   }

}