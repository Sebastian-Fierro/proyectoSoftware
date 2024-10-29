package com.ubb.proyecto.repository;

import com.ubb.proyecto.model.Evento;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioEvento extends JpaRepository<Evento, Integer> {

    List<Evento> findByLugar(String lugar);

    List<Evento> findByHorario(LocalTime horario);
}
