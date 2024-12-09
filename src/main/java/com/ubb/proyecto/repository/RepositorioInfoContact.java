package com.ubb.proyecto.repository;

import com.ubb.proyecto.model.InfoContact;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioInfoContact extends JpaRepository<InfoContact, Integer> {

    Optional<InfoContact> findFirstByOrderByIdContactAsc();
}

