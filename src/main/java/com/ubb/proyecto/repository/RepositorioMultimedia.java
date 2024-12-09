package com.ubb.proyecto.repository;

import com.ubb.proyecto.model.Multimedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//import java.util.Optional;


@Repository
public interface RepositorioMultimedia extends JpaRepository<Multimedia, Integer> {

}
