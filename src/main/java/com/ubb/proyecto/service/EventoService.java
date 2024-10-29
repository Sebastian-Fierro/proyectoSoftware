package com.ubb.proyecto.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ubb.proyecto.model.*;
import com.ubb.proyecto.repository.RepositorioEvento;

@Service
public class EventoService {

    @Autowired
    private RepositorioEvento eventoRepository;

    public List<Evento> getAllEventos(){
        return eventoRepository.findAll();
    }

    public Optional<Evento> getEventoById(Integer id) {
        return eventoRepository.findById(id);
    }

    //Tengo dudas
    public List<Evento> getEventoByLugar(String lugar) {
        return eventoRepository.findByLugar(lugar);
    }
     //Tengo dudas
    public List<Evento> getEventoByHorario(LocalTime horario) {
        return eventoRepository.findByHorario(horario);
    }
    public Evento saveEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public void deleteEvento(Integer id) {
        eventoRepository.deleteById(id);
    }

}
