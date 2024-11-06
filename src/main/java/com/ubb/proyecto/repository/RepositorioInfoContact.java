package com.ubb.proyecto.repository;

import com.ubb.proyecto.model.InfoContact;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioInfoContact extends JpaRepository<InfoContact, Integer> {

    //Optional<InfoContact> findBycorreo(String correo);

    //InfoContact save(String correo);
}
